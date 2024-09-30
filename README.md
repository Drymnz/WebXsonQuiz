# WebXsonQuiz
<img src='/recursos-git/img_001.png'>

Bienvenido al Sistema de Trivias, una plataforma diseñada para crear, gestionar y responder trivias de manera eficiente. Este README le guiará a través de las principales funciones del sistema, tanto en su versión web como en la aplicación móvil.

<img src='/recursos-git/img_002.jpg'>

## Características

### Arquitectura General
- Sistema cliente-servidor
- Aplicación web para la creación y gestión de trivias
- Aplicación móvil Android para responder trivias
- Servidor que gestiona la comunicación entre aplicaciones y almacena datos


<img src='/recursos-git/img_003.png'>

### Comunicación y Protocolos
- Uso de sockets para la comunicación entre el servidor y las aplicaciones
- Protocolo HTTP para la aplicación web
- Lenguaje XSON (híbrido entre XML y JSON) para estructurar solicitudes y respuestas

### Gestión de Usuarios
- Registro de nuevos usuarios
- Autenticación de usuarios (login)
- Modificación de perfiles de usuario
- Eliminación de usuarios

### Creación y Gestión de Trivias (Aplicación Web)
- Creación de nuevas trivias con identificador único
- Configuración de parámetros de trivia (tiempo por pregunta, tema, etc.)
- Adición de componentes a las trivias (preguntas de diferentes tipos)
- Modificación de trivias existentes
- Eliminación de trivias
- Importación de trivias desde archivos `.xtriv`
- Exportación de trivias a archivos `.xtriv`

### Tipos de Componentes de Trivia
- Campo de texto
- Área de texto
- Checkbox
- Radio buttons
- Combo box

### Aplicación Móvil
- Visualización de trivias disponibles
- Selección y respuesta de trivias
- Cálculo de puntaje basado en respuestas correctas y tiempo

### Sistema de Reportes
- Lenguaje de consulta SQLKV para generar reportes
- Filtrado de información por diversos parámetros (usuario, tiempo, etc.)

### Almacenamiento de Datos
- Estructura de almacenamiento basada en JSON para trivias y respuestas de usuarios
- Capacidad de reiniciar la aplicación manteniendo los datos almacenados

### Tecnologías Utilizadas
- Java SE, JSP, HTML, CSS, JavaScript para la aplicación web
- Kotlin para la aplicación Android
- JFlex y Cup para análisis léxico y sintáctico
- Servidores web (Tomcat, Apache, Nginx, o JBoss) para la aplicación web

### Características Adicionales
- Manejo de errores léxicos y sintácticos
- Interfaz de usuario amigable y estilizada
- Capacidad de crear trivias rápidas y ágiles sin complicaciones

## Instalación

### Requisitos del Sistema

#### Linux
- Distribuciones compatibles: Ubuntu, Fedora, Debian (cualquier distro que soporte JDK).
- RAM: 4 GB mínimo (8 GB recomendado).
- Espacio en disco: 2 GB para Android Studio, 4 GB para Android SDK y emulador.

#### Windows
- Sistema operativo: Windows 10 (64-bit recomendado).
- RAM: 4 GB mínimo (8 GB recomendado).
- Espacio en disco: 2 GB para Android Studio, 4 GB para Android SDK y emulador.

### Instalación y configuración

#### Instalación de Java
Para ejecutar el proyecto, asegúrate de tener Java instalado. Instrucciones para Arch Linux y derivadas:
```bash
sudo pacman -Syu jdk21-openjdk
```

#### Instalación de Git
Para clonar el repositorio, instala git. Instrucciones para Arch Linux y derivadas:
```bash
sudo pacman -Syu git
```

#### Instalación de Maven
Maven es necesario para compilar el proyecto. Instrucciones para Arch Linux y derivadas:
```bash
sudo pacman -Syu maven
```

### Compilación del proyecto
1. Navega al directorio del proyecto:
```bash
cd InterpreteFiguras
```
2. Cambia al directorio de código fuente:
```bash
cd Code
```
3. Limpia y compila el proyecto utilizando Maven:
```bash
mvn clean package
```

4. Ubica el archivo WAR o la carpeta `WebXsonQuiz-1.0-SNAPSHOT`:
```
target
├── WebXsonQuiz-1.0-SNAPSHOT
└── WebXsonQuiz-1.0-SNAPSHOT.war
```

#### Despliegue en Apache Tomcat
Asegúrate de tener Apache Tomcat instalado. Verifica que Tomcat esté ejecutándose. Para iniciar Tomcat:

En Linux/Unix:
```bash
./catalina.sh start
```

En Windows:
```cmd
catalina.bat start
```

#### Instalación de Android Studio y Generación de APK
Visita el sitio oficial de Android Studio: https://developer.android.com/studio.

#### Para Linux
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

#### Para Windows
Sigue el asistente del instalador.

### Registro e Inicio de Sesión
```xson
<!realizar_solicitud: "LOGIN_USUARIO" >
{ "DATOS_USUARIO":[{
"USUARIO": "juanito619",
"PASSWORD": "12345678"
}]}
<fin_solicitud_realizada!>
```

### Gestión de Usuarios
```xson
<!realizar_solicitud: "MODIFICAR_USUARIO" >
{ "DATOS_USUARIO":[{
"USUARIO_ANTIGUO": "juanito619",
"USUARIO_NUEVO": "juanito619lopez",
"NUEVO_PASSWORD": "12345678910"
}]}
<fin_solicitud_realizada!>
```

### Creación y Gestión de Trivias
```xson
<!realizar_solicitud: "NUEVA_TRIVIA" >
{ "PARAMETROS_TRIVIA":[{
"ID_TRIVIA": "$trivia1",
"TIEMPO_PREGUNTA": 45,
"NOMBRE": "Cultura de Guatemala",
"TEMA": "cultura"
}]}
<fin_solicitud_realizada!>
```
# Importación y Exportación de Trivias

## 5.3. Importación y Exportación de Trivias

- **Para importar:** Seleccione "Importar Trivia" y cargue un archivo `.xtriv`.
- **Para exportar:** Seleccione la trivia deseada y haga clic en "Exportar Trivia".

## 5.4. Ejecución de Consultas SQLKV

### Para obtener reportes:

1. Acceda a la sección de reportes.
2. Escriba su consulta SQLKV. Ejemplo:
   ```
   SELECCIONAR REPORTE $trivia FILTRAR POR TIEMPO < 100
   ```

# Aplicación Móvil

## 6. Vista de la aplicación móvil.

### 6.1. Acceso a Trivias

1. Inicie sesión en la aplicación móvil.
2. Ingrese los datos del socket, que son el IP y el PUERTO.

   **Ejemplo de solicitud:**
   ```json
   <!realizar_solicitud: "LOGIN_USUARIO" >
   { "DATOS_USUARIO":[{
   "USUARIO": "juanito619",
   "PASSWORD": "12345678"
   }
   ]}
   <fin_solicitud_realizada!>
   ```

   Visualizará la conexión correcta y una lista de trivias disponibles.

### 6.2. Responder Trivias

1. Seleccione una trivia de la lista.
2. Lea cada pregunta cuidadosamente.
3. Responda dentro del tiempo establecido.

   Al finalizar, verá su puntuación basada en respuestas correctas y tiempo.

# Glosario de Términos

- **Trivia:** Conjunto de preguntas sobre un tema específico.
- **Componente:** Elemento individual de una trivia (pregunta, opciones de respuesta, etc.).
- **SQLKV:** Lenguaje de consulta para generar reportes en el sistema.
- **.xtriv:** Formato de archivo para importar y exportar trivias.