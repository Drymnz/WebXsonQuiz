<%@page import="LexicalAndSyntacticAnalyzer.analyzer.Token"%>
<%@page import="reports.ReportErrorInterpreter"%>
<%@page import="com.cunoc.webxsonquiz.data.servert.Trivia"%>
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
            String error = (String) request.getAttribute("mensaje");
            ArrayList<Trivia> listTrivia = (ArrayList<Trivia>) request.getAttribute("listTrivia");
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
                    <form action="${pageContext.request.contextPath}/SvUsers" method="GET" style="flex-grow: 1; display: flex; flex-direction: column;">
                        <input type="hidden" name="userId" value="<%= usuario != null ? usuario.getId() : ""%>">
                        <button class="execute-btn" >Actualizar</button> 
                    </form>
                    <form action="${pageContext.request.contextPath}/ExportImport" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="userId" value="<%= usuario != null ? usuario.getId() : ""%>">
                        <div class="file-input-wrapper">
                            <input type="file" name="file" id="fileInput" accept=".xtriv" required>
                            <label for="fileInput" class="file-label">Seleccionar archivo</label>
                        </div>
                        <span class="file-name" id="fileName"></span>
                        <button class="execute-btn" type="submit">Importar</button>
                    </form>
                    <%  if (error != null) {%>
                    <p>Mensaje: <%= error%></p> <br>
                    <%
                        }
                    %>
                    <%  if (listTrivia != null) {  %>
                    <div class="results">
                        <h2>Resultados</h2>
                        <table>
                            <tr>
                                <th>ID TRIVIA</th>
                                <th>NOMBRE</th>
                                <th>TEMA</th>
                                <th>TIEMPO DE RESPUESTA</th>
                                <th>CREADOR</th>
                                <th>FECHA CREADO</th>
                                <th>Exportar</th>
                            </tr>
                            <% for (Trivia element : listTrivia) {%>
                            <tr>
                                <td><%= element.getId()%></td>
                                <td><%= element.getName()%></td>
                                <td><%= element.getTheme()%></td>
                                <td><%= element.getTime()%></td>
                                <td><%= element.getIdUser()%></td>
                                <td><%= element.getDate()%></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/ExportImport" method="GET">
                                        <input type="hidden" name="triviaId" value="<%= element.getId()%>">
                                        <button class="execute-btn"  type="submit">Exportar</button>
                                    </form>
                                </td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                    <%}%>
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
        </div>
        <script>
            document.getElementById('fileInput').addEventListener('change', function () {
                var fileName = this.files[0].name;
                document.getElementById('fileName').textContent = fileName;
            });
        </script>
    </body>
</html>
