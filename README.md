# posts-comments-app

Aplicación creada como respuesta a una prueba técnica. Permite manejar “posts” y sus “comments” asociados. El objetivo de este proyecto es demostrar capacidades de diseño, organización y construcción de una aplicación desde cero.  

## 📘 Descripción

- Backend escrito en Kotlin (Gradle), conforme a la estructura actual del repositorio.  
- Proporciona funcionalidades para gestionar entradas (“posts”) y sus comentarios (“comments”).  
- Ideal como ejercicio técnico, ejemplo educativo o punto de partida para extender funcionalidades (autenticación, interfaz, persistencia, etc.).  

## 🚀 Comenzando

Estas instrucciones te permitirán obtener una copia del proyecto funcionando en tu máquina local para desarrollo y pruebas.

### Requisitos previos

- JDK compatible con Kotlin (por ejemplo OpenJDK 11 o superior).  
- Gradle (o usar los *wrappers* incluidos).  

### Instalación y ejecución

```bash
# Clonar el repositorio
git clone https://github.com/jeyko55/posts-comments-app.git
cd posts-comments-app

# Usar Gradle wrapper para construir
./gradlew build      # en Unix/macOS
gradlew.bat build    # en Windows

# Ejecutar la aplicación (si hay un task run definido)
./gradlew run        # (o el task que corresponda)
