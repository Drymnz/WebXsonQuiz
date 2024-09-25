<%-- 
    Document   : menuTrivias
    Created on : 14 sep 2024, 9:05:41 p.m.
    Author     : drymnz
--%>

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
                <div class="results">
                    <h2>Resultados</h2>
                    <table>
                        <tr>
                            <th>ID User</th>
                            <th>Nombre</th>
                            <th>Institucion</th>
                            <th>Fecha de creacion</th>
                        </tr>
                    </table>
                </div>
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
