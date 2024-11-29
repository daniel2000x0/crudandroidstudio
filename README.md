# Proyecto CRUD Android Studio con Firebase y SQLite

Este es un proyecto de **Android Studio** que implementa una aplicación CRUD (Crear, Leer, Actualizar, Eliminar) utilizando **Firebase** y **SQLite** como bases de datos locales y en la nube. La aplicación permite gestionar datos de manera eficiente, tanto de manera local en el dispositivo como en la nube a través de Firebase.

## Características del Proyecto

- **Base de Datos Local (SQLite)**: Almacena datos de manera local en el dispositivo.
- **Firebase**: Sincroniza datos entre el dispositivo y la nube, permitiendo la gestión remota.
- **Interfaz de Usuario (UI)**: Interfaz amigable para interactuar con las funcionalidades CRUD.
- **CRUD Completo**: Permite crear, leer, actualizar y eliminar registros.
  
## Requisitos

- **Android Studio**: La última versión de Android Studio.
- **Firebase**: Cuenta de Firebase y configuración del proyecto en la consola de Firebase.
- **SDK de Firebase**: Incluido en el archivo `build.gradle` del proyecto.
- **Dependencias**:
  - Firebase Realtime Database
  - Firebase Authentication (si es necesario)
  - SQLite (base de datos local)
  
## Tecnologías Utilizadas

- **Android Studio** (Java/Kotlin)
- **Firebase**: Realtime Database, Authentication
- **SQLite**: Para la base de datos local en el dispositivo.

## Instalación

Sigue estos pasos para configurar el proyecto en tu máquina local:

1. **Clona este repositorio**:

    ```bash
    git clone https://github.com/tuusuario/tu-repositorio.git
    ```

2. **Abre el proyecto en Android Studio**:
    - Abre Android Studio.
    - Selecciona "Abrir un proyecto existente".
    - Navega hasta la carpeta donde clonaste el repositorio y selecciónalo.

3. **Configura Firebase**:
    - Crea un proyecto en [Firebase Console](https://console.firebase.google.com/).
    - Configura Firebase en tu aplicación siguiendo las instrucciones de la [documentación oficial](https://firebase.google.com/docs/android/setup).
    - Añade el archivo `google-services.json` a la carpeta `app` de tu proyecto Android Studio.
    - Habilita Firebase Realtime Database en la consola de Firebase.

4. **Agrega las dependencias necesarias**:
    Asegúrate de que tu archivo `build.gradle` (nivel de aplicación) tenga las siguientes dependencias:

    ```gradle
    dependencies {
        implementation 'com.google.firebase:firebase-database:20.0.5'
        implementation 'com.google.firebase:firebase-auth:21.0.3'
        implementation 'androidx.sqlite:sqlite:2.2.0'
        // Otras dependencias que necesites
    }
    ```

5. **Sincroniza el proyecto**:
    Haz clic en "Sync Now" para sincronizar las dependencias.

## Uso

1. **Pantalla Principal**: Muestra una lista de elementos almacenados tanto en Firebase como en la base de datos local SQLite.
2. **Agregar Nuevo Registro**: Permite crear un nuevo registro en Firebase y en SQLite.
3. **Actualizar Registro**: Permite modificar un registro existente.
4. **Eliminar Registro**: Permite eliminar un registro de Firebase y SQLite.

## Estructura de la Base de Datos

- **Firebase Realtime Database**:
  - La estructura de la base de datos en Firebase puede ser algo como esto:

    ```json
    {
      "usuarios": {
        "usuario_id_1": {
          "nombre": "Juan Pérez",
          "email": "juan@example.com"
        },
        "usuario_id_2": {
          "nombre": "Ana Gómez",
          "email": "ana@example.com"
        }
      }
    }
    ```

- **SQLite**:
  - La estructura local en SQLite se puede definir con tablas como `usuarios`:

    ```sql
    CREATE TABLE usuarios (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      nombre TEXT NOT NULL,
      email TEXT NOT NULL
    );
    ```

## Contribuciones

Si deseas contribuir al proyecto, por favor sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agregada nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

¡Gracias por usar este proyecto! Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue.
