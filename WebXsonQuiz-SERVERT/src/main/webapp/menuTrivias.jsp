<%-- 
    Document   : menuTrivias
    Created on : 14 sep 2024, 9:05:41 p.m.
    Author     : drymnz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="stely_menu.css.css">
    </head>
<body>
    <div class="container">
        <header>
            <h1>Sistema de Creación de Trivias</h1>
        </header>
        <nav>
            <ul>
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Mis Trivias</a></li>
                <li><a href="#">Crear Trivia</a></li>
                <li><a href="#">Reportes</a></li>
                <li><a href="#">Cerrar Sesión</a></li>
            </ul>
        </nav>
        <div class="main-content">
            <div class="editor">
                <h2>Editor XSON</h2>
                <textarea placeholder="Escribe tu código XSON aquí..."></textarea>
                <button>Ejecutar</button>
                <h2>Consultas SQLKV</h2>
                <textarea placeholder="Escribe tu consulta SQLKV aquí..."></textarea>
                <button>Ejecutar Consulta</button>
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
