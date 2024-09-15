<%-- 
    Document   : triviea_manager
    Created on : 15 sep 2024, 12:19:09 a.m.
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
        %>
    </head>
<body>
    <div class="container">
        <header>
            <h1>Sistema de Creación de Trivias</h1>
             <%  if (usuario != null) {  %>
            Nombre: <%= usuario.getId()  %><br>
        <% 
            } 
        %>
        </header>
        <nav>
            <ul>
                <li><a href="user_manager.jsp">Gestor de Usuarios</a></li>
                <li><a href="triviea_manager.jsp">Gestor de Trivias</a></li>
                <li><a href="report.jsp">Reportes</a></li>
                <li><a href="#">Cerrar Sesión</a></li>
            </ul>
        </nav>
        <div class="main-content">
            <div class="editor">
                <h2>Editor XSON</h2>
                <textarea placeholder="Escribe tu código XSON aquí..."></textarea>
                <button>Ejecutar</button>
            </div>
            <div class="results">
                <h2>Resultados</h2>
                <table>
                    <tr>
                        <th>ID Trivia</th>
                        <th>Nombre</th>
                        <th>Tema</th>
                    </tr>
                    <tr>
                        <td>$trivia1</td>
                        <td>Cultura General</td>
                        <td>Cultura</td>
                    </tr>
                    <tr>
                        <td>$trivia2</td>
                        <td>Matemáticas Básicas</td>
                        <td>Matemáticas</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
