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
            <div class="ide-title">My IDE</div>
        </header>
        <div class="editor">
            <textarea class="code-input" placeholder="Type your code here..."></textarea>
        </div>
        <div class="console">
            <div class="console-title">Console</div>
            <div class="console-output" id="consoleOutput">No output yet...</div>
        </div>
    </div>
    <!-- <script>
        const codeInput = document.querySelector('.code-input');
        const consoleOutput = document.getElementById('consoleOutput');
        
        codeInput.addEventListener('input', () => {
            consoleOutput.textContent = "Running code...";
            
            // Simulate a delay and send the code to the server via AJAX
            setTimeout(() => {
                const code = codeInput.value;

                // Fetching output from the server
                fetch('processCode.jsp', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'code=' + encodeURIComponent(code)
                })
                .then(response => response.text())
                .then(data => {
                    consoleOutput.textContent = data;
                })
                .catch(error => {
                    consoleOutput.textContent = "Error: " + error;
                });
            }, 1000);
        });
    </script> -->
</body>
</html>
