<%-- 
    Document   : report
    Created on : 15 sep 2024, 12:19:39 a.m.
    Author     : drymnz
--%>
<%@page import="reports.ReportErrorInterpreter"%>
<%@page import="LexicalAndSyntacticAnalyzer.analyzer.Token"%>
<%@page import="com.cunoc.webxsonquiz.data.servert.QuizAttempt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cunoc.webxsonquiz.data.servert.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="stely_menu.css">
        <%
            // Obtener el objeto Usuario almacenado en la sesión
            User usuario = (User) session.getAttribute("usuario");
            if (usuario == null) {
                // Si no hay usuario en sesión, redirigir a la página de inicio de sesión
                response.sendRedirect("login.jsp");
                return; // Finalizar el procesamiento de la página actual
            }
            ArrayList<QuizAttempt> listQuizAttempt = (ArrayList<QuizAttempt>) request.getAttribute("listQuizAttempt");
            String textArea = (String) request.getAttribute("resultsText");
            ArrayList<ReportErrorInterpreter> listError = (ArrayList<ReportErrorInterpreter>) request.getAttribute("listErrores");
        %>
    </head>
    <body>
        <div class="container">
            <header>
                <h1>Sistema de Creación de Trivias</h1>
                <%  if (usuario != null) {%>
                Nombre: <%= usuario.getId()%><br>
                <%
                    }
                %>
            </header>
            <%@ include file="navigation_bar.jsp" %>
            <div class="main-content">
                <div class="editor">
                    <h2>Consultas SQLKV</h2>
                    <form action="${pageContext.request.contextPath}/ServletSQLKV" method="GET" style="flex-grow: 1; display: flex; flex-direction: column;">
                        <%  if ((textArea != null) && !textArea.isEmpty()) {%>
                        <textarea name="textArea" id="codeInput" class="code-input" placeholder="Ingrese el comandos SQLKV">
                            <%=textArea%>
                        </textarea>
                        <%} else {%>
                        <textarea name="textArea" id="codeInput" class="code-input" placeholder="Ingrese el comandos SQLKV"></textarea>
                        <%}%>
                        <div id="position">Fila: 1, Columna: 1</div>
                        <button type="submit" class="execute-btn">Ejecutar Consulta</button>
                    </form>
                </div>
                <%  if (listError != null) {  %>
                <div class="results">
                    <h2>Resultados de Errores Lexico y Sintactico</h2>
                    <table>
                        <tr>
                            <th>Linea</th>
                            <th>Columna</th>
                            <th>Lexema</th>
                            <th>Tipo de error</th>
                            <th>Descripcion</th>
                        </tr>
                        <% for (ReportErrorInterpreter element : listError) {%>
                        <tr>
                            <%Token token = element.getToke();%>
                            <td><%= token.getLine()%></td>
                            <td><%= token.getColumna()%></td>
                            <td><%= token.getLexeme()%></td>
                            <td><%= element.getType().toString()%></td>
                            <td><%= element.getDescription()%></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
                <%}%>
                <%  if (listQuizAttempt != null) {  %>
                <div class="results">
                    <h2>Resultados</h2>
                    <table>
                        <tr>
                            <th>ID DEL USUARIO</th>
                            <th>ID TRIVIA</th>
                            <th>TIEMPO DE RESPUESTA</th>
                            <th>PUNTEO</th>
                        </tr>
                        <% for (QuizAttempt element : listQuizAttempt) {%>
                        <tr>
                            <td><%= element.getUser()%></td>
                            <td><%= element.getQuizId()%></td>
                            <td><%= element.getResponseTime()%></td>
                            <td><%= element.getScore()%></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
                <%}%>
            </div>
        </div>
        <!-- Enlace al archivo JavaScript -->
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var textarea = document.getElementById('codeInput');
                var positionDisplay = document.getElementById('position');

                function updateCursorPosition() {
                    var cursorPosition = textarea.selectionStart;
                    var textBeforeCursor = textarea.value.substring(0, cursorPosition);
                    var lines = textBeforeCursor.split('\n');
                    var currentLine = lines.length;
                    var currentColumn = lines[lines.length - 1].length + 1;

                    positionDisplay.textContent = 'Fila: ' + currentLine + ', Columna: ' + currentColumn;
                }

                textarea.addEventListener('input', updateCursorPosition);
                textarea.addEventListener('click', updateCursorPosition);
                textarea.addEventListener('keyup', updateCursorPosition);

                // Inicializar la posición
                updateCursorPosition();
            });
        </script>
    </body>
</html>
