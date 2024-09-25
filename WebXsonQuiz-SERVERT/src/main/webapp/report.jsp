<%-- 
    Document   : report
    Created on : 15 sep 2024, 12:19:39 a.m.
    Author     : drymnz
--%>
<%@page import="com.cunoc.webxsonquiz.data.servert.QuizAttempt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cunoc.webxsonquiz.data.servert.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="stely_menu.css.css">
        <%
            // Obtener el objeto Usuario almacenado en la sesión
            User usuario = (User) session.getAttribute("usuario");
            ArrayList<QuizAttempt> listQuizAttempt = (ArrayList<QuizAttempt>) request.getAttribute("listQuizAttempt");
            String textArea = (String) request.getAttribute("resultsText");
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
            <nav>
                <ul>
                    <li><a href="user_manager.jsp">Gestor de Usuarios</a></li>
                    <li><a href="report.jsp">Reportes</a></li>
                    <li><a href="#">Cerrar Sesión</a></li>
                </ul>
            </nav>
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
