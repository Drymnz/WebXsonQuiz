<%-- 
    Document   : menuTrivias
    Created on : 14 sep 2024, 9:05:41 p.m.
    Author     : drymnz
--%>

<%@page import="LexicalAndSyntacticAnalyzer.analyzer.Token"%>
<%@page import="reports.ReportErrorInterpreter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cunoc.webxsonquiz.data.servert.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administador de Usuarios/Trivias</title>
        <link rel="stylesheet" href="stely_menu.css">
        <%
            // Obtener el objeto Usuario almacenado en la sesión
            User usuario = (User) session.getAttribute("usuario");
            if (usuario == null) {
                // Si no hay usuario en sesión, redirigir a la página de inicio de sesión
                response.sendRedirect("login.jsp");
                return; // Finalizar el procesamiento de la página actual
            }
            String textArea = (String) request.getAttribute("resultsText");
            ArrayList<ReportErrorInterpreter> listError = (ArrayList<ReportErrorInterpreter>) request.getAttribute("listErrores");
        %>
    </head>
    <body>
        <div class="container">
            <header>
                <h1>Bienvenido a WebXsonQuiz</h1>
                <%  if (usuario != null) {%>
                Nombre: <%= usuario.getId()%><br>
                <%
                    }
                %>
            </header>
            <%@ include file="navigation_bar.jsp" %>
            <div class="main-content">
                <div class="editor">
                    <h2>Editor XSON</h2>
                    <form action="${pageContext.request.contextPath}/SvUsers" method="POST" style="flex-grow: 1; display: flex; flex-direction: column;">
                        <input type="hidden" name="userId" value="<%= usuario != null ? usuario.getId() : ""%>">
                        <%  if ((textArea != null) && !textArea.isEmpty()) {%>
                        <textarea name="textArea" id="codeInput" class="code-input" placeholder="Ingrese el usuario">
                            <%=textArea%>
                        </textarea>
                        <%} else {%>
                        <textarea name="textArea" id="codeInput" class="code-input" placeholder="Ingrese el usuario"></textarea>
                        <%}%>
                        <div id="position">Fila: 1, Columna: 1</div>
                        <button type="submit" class="execute-btn">Login</button>
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
            </div>
        </div>
    </body>
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
</html>
