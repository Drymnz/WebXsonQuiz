<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple IDE</title>
    <link rel="stylesheet" href="stely.css">
</head>
<body>
    <div class="ide">
        <header>
            <div class="ide-title">Web Xson Quiz</div>
        </header>
        <!-- Formulario para enviar el usuario al servlet SvUsers por POST -->
        <form action="Login" method="POST" style="flex-grow: 1; display: flex; flex-direction: column;">
            <div class="editor">
                <textarea name="user" class="code-input" placeholder="Ingrese el usuario"></textarea>
            </div>
            <div class="console">
                <div class="console-header">
                    <div class="console-title">Console</div>
                    <!-- Botón para enviar el formulario -->
                    <button type="submit" class="execute-btn">Login</button>
                </div>
            </div>
        </form>
        <div class="console-output">
            <% 
                String result = (String) request.getAttribute("result");
                if (result != null) {
            %>
                <div><%= result %></div>
            <% 
                } else { 
            %>
                No output yet...
            <% } %>
        </div>
    </div>
</body>
</html>
