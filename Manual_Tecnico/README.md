## WebXsonQuiz

Bienvenido al Sistema de Trivias, una plataforma diseñada para crear, gestionar y responder trivias de manera eficiente. Este manual le guiará a través de las principales funciones del sistema, tanto en su versión web como en la aplicación móvil.

### 1. Distribución de Roles de Usuario

Para la programación de este aplicativo se definieron 2 tipos de usuarios o roles donde cada uno interactúa con el sistema de diferentes modos.

#### Descripción de Funciones de Usuarios

**Usuario del Sistema Web**
- **Iniciar sesión:** Acceder al sistema utilizando sus credenciales (nombre de usuario y contraseña).
- **Cerrar sesión:** Salir del sistema de manera segura.
- **Modificar usuario:** Actualizar la información de su perfil, como contraseña, datos personales, etc.
- **Crear trivias:** Diseñar y configurar nuevas trivias con preguntas, opciones y respuestas.
- **Modificar trivias:** Editar trivias existentes, cambiar preguntas, opciones o configuraciones.
- **Crear usuarios:** Registrar nuevos usuarios en el sistema.
- **Eliminar trivias:** Remover trivias que ya no sean necesarias.
- **Ver lista de trivias creadas:** Acceder a un listado de todas las trivias que ha creado.
- **Importar trivias:** Cargar trivias desde archivos externos (formato .xtriv).
- **Exportar trivias:** Guardar trivias en archivos externos (formato .xtriv) para su posterior uso o compartir.
- **Ejecutar consultas SQLKV:** Realizar consultas personalizadas sobre los datos de las trivias utilizando el lenguaje SQLKV.

**Usuario de la App Móvil**
- **Iniciar sesión:** Acceder a la aplicación móvil con sus credenciales.
- **Ver lista de trivias disponibles:** Visualizar las trivias que están disponibles para responder.
- **Seleccionar y responder trivias:** Elegir una trivia de la lista y contestar sus preguntas.

### 2. Organización del Proyecto

**Aplicación Android:**
```
── AndroidManifest.xml
├── java
│   └── com
│       ├── cunoc
│       │   └── webxsonquiz
│       │       ├── data
│       │       │   ├── ConectionServert.kt
│       │       │   ├── ResultComponentTrivia.kt
│       │       │   ├── servert
│       │       │   │   ├── ClassComponent.kt
│       │       │   │   ├── ComponentTrivia.kt
│       │       │   │   ├── QuizAttempt.kt
│       │       │   │   ├── Trivia.kt
│       │       │   │   └── User.kt
│       │       │   └── ServertRepositoy.kt
│       │       ├── servert
│       │       └── ui
│       │           ├── ActivityListTrivias.kt
│       │           ├── ActivityTrivia.kt
│       │           ├── CustomAdapterListTrivia.kt
│       │           ├── MainActivity.kt
│       │           ├── MainUIState.kt
│       │           └── MainViewModel.kt
└── example
    └── res
        ├── drawable
        │   ├── ic_launcher_background.xml
        │   └── ic_launcher_foreground.xml
        ├── layout
        │   ├── activity_main.xml
        │   ├── activity_trivias.xml
        │   ├── activity_trivia.xml
        │   ├── layout_element_list_trivia.xml
        │   ├── layout_info_trivia.xml
        │   ├── layout_trivia_component_question.xml
        │   └── layout_user.xml
        ...
```

### 3. Analizadores

Para este proyecto se realizaron varios analizadores léxico y sintáctico.

#### 3.1 Analizador Léxico

Para el archivo XSON se usaron dos analizadores: uno para analizar solo el login y otro para analizar todas las solicitudes.

**XSON Tabla de Expresiones Regulares**

| Nombre         | Expresión Regular                                         | Descripción                                           |
|----------------|----------------------------------------------------------|-------------------------------------------------------|
| INSTITUCION    | `\"I""N""S""T""I""U""C""I""O""N""`                      | Representa la palabra clave "INSTITUCION" entre comillas. |
| DIGIT          | `[0-9]`                                                 | Representa un solo dígito numérico.                   |
| WHOLE          | `{DIGIT}+`                                             | Representa un número entero compuesto por uno o más dígitos. |
| DECIMAL        | `{WHOLE}[.]{WHOLE}`                                    | Representa un número decimal con parte entera y fraccionaria. |
| REAL_NUMEBERS  | `{DECIMAL} | {WHOLE}`                                 | Representa un número real.                             |
| DATE           | `\"{DIGIT}{DIGIT}{DIGIT}{DIGIT}-...\"`                | Representa una fecha en el formato "YYYY-MM-DD".      |
| STRING         | `"([^"$$ \.)*"`                                        | Representa una cadena con caracteres escapados.      |

**XSON Tabla de Palabras Claves**

| Palabra Clave         | Descripción                                           |
|-----------------------|------------------------------------------------------|
| `""`                  | Comentario, ignora todo el contenido entre estas marcas. |
| `"<"`                 | Símbolo de apertura para elementos XML/JSON.        |
| `">"`                 | Símbolo de cierre para elementos XML/JSON.          |
| `"xson"`              | Palabra clave para representar el tipo "xson".      |
| `"xml"`               | Palabra clave para identificar archivos XML.         |
| `"version"`           | Palabra clave que indica la versión de un documento XML. |

**SQLKV Tabla de Expresiones Regulares**

| Nombre         | Expresión Regular                                         | Descripción                                           |
|----------------|----------------------------------------------------------|-------------------------------------------------------|
| INSTITUCION    | `""""I""N""S""T""I""T""U""C""I""O""N""""`              | Cadena específica que representa la palabra "INSTITUCION". |
| DIGIT          | `[0-9]`                                                 | Un solo dígito.                                       |
| WHOLE          | `{DIGIT}+`                                             | Número entero, una o más repeticiones de dígitos.    |

**SQLKV Tabla de Palabras Claves**

| Palabra Clave         | Descripción                                           |
|-----------------------|------------------------------------------------------|
| SELECCIONAR           | Palabra clave para seleccionar datos en el lenguaje SQL. |
| REPORTE               | Palabra clave para generar un reporte en el sistema.|
| FILTRAR               | Palabra clave para aplicar un filtro a los datos.   |

---

Este archivo README.md proporciona una descripción general del proyecto y sus componentes principales, incluyendo roles de usuario, organización del proyecto y detalles sobre los analizadores léxico y sintáctico utilizados en el desarrollo del sistema WebXsonQuiz.
