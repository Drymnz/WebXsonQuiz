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
            <form action="${pageContext.request.contextPath}/login" method="POST" style="flex-grow: 1; display: flex; flex-direction: column;">
                <div class="editor">
                    <textarea name="user" id="codeInput" class="code-input" placeholder="Ingrese el usuario"></textarea>
                    <div id="position">Fila: 1, Columna: 1</div>
                </div>
                <div class="console">
                    <div class="console-header">
                        <div class="console-title">Console</div>
                        <button type="submit" class="execute-btn">Login</button>
                    </div>
                </div>
            </form>
            <div class="console-output">
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null && !errorMessage.isEmpty()) {
                %>
                <div style="color: red;"><%= errorMessage%></div>
                <%
                } else {
                %>
                <div>No output yet...</div>
                <% }%>
            </div>
        </div>
    </body>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
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