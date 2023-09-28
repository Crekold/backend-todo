# Backend de la Aplicación de Lista de Tareas

Este es el README del backend de la aplicación de lista de tareas. El backend está desarrollado utilizando Spring Boot y proporciona una API RESTful para la gestión de tareas en la base de datos MySQL.

## Requisitos

Asegúrate de tener instalados los siguientes requisitos antes de ejecutar el backend:

* Java 17
* Maven
* MySQL

## Instalación y Configuración

Sigue estos pasos para configurar y ejecutar el backend de la aplicación:

1. Asegúrate de tener Java 17 y Maven instalados en tu sistema.

2. Crea una base de datos MySQL llamada `todo-list`.

3. Importa el esquema de la base de datos ejecutando el script SQL proporcionado en el archivo `todo-list_create.sql`. Esto creará la estructura de tabla necesaria para almacenar las tareas.

4. Clona este repositorio en tu computadora y navega hasta la carpeta del proyecto.

5. Abre el archivo de configuración `application.properties` en la carpeta `src/main/resources` y ajusta la configuración de la base de datos según sea necesario (por ejemplo, configurar el nombre de usuario y contraseña de MySQL).

6. Ejecuta el siguiente comando para compilar y construir el proyecto:

   ```bash
   mvn clean install
   ```

## Ejecución

Para ejecutar el backend de la aplicación de lista de tareas, utiliza el siguiente comando:

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en el puerto predeterminado 8080.

## API RESTful

El backend proporciona una API RESTful que admite las siguientes operaciones:

* Crear una tarea
* Obtener una lista de todas las tareas
* Obtener una tarea por su ID
* Actualizar una tarea
* Eliminar una tarea
* Creacion, obtencion, modificacion y eliminacion de etiquetas
* Sistema de usuarios con login

Puedes interactuar con la API utilizando herramientas como Postman o cURL.

## Autor

Este proyecto de backend fue desarrollado por Ezequiel Gomez.

## Licencia

Este proyecto está licenciado bajo la licencia MIT.
