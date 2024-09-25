

<textarea name="user" id="codeInput" class="code-input" placeholder="Ingrese el usuario"></textarea>
<div id="position">Fila: 1, Columna: 1</div>

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
