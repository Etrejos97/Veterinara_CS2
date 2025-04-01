Veterinaria CS2
===============

Descripción del proyecto
------------------------
Veterinaria CS2 es un aplicativo de administración diseñado para veterinarias especializadas en especies pequeñas, como perros, gatos, peces y aves. El sistema permite gestionar roles de usuarios (administradores, médicos veterinarios, dueños de mascotas y vendedores) y facilita las siguientes funcionalidades:
- Registro y gestión de mascotas.
- Creación y consulta de historias clínicas.
- Generación y administración de órdenes de medicamentos.
- Venta de productos y medicamentos.
- Facturación automatizada.

El objetivo principal del proyecto es optimizar los procesos administrativos y médicos de una veterinaria, mejorando la experiencia tanto para los usuarios internos como para los clientes.

Características principales
---------------------------
- **Gestión de mascotas:** Registro y actualización de información de mascotas.
- **Historias clínicas:** Creación y consulta de registros médicos asociados a las mascotas.
- **Órdenes médicas:** Registro de órdenes de medicamentos y su seguimiento.
- **Venta de productos:** Gestión de ventas de productos genéricos y medicamentos.
- **Facturación:** Generación automática de facturas para ventas y servicios.
- **Roles de usuario:** Soporte para diferentes roles, como administradores, veterinarios y vendedores.

Requisitos del sistema
----------------------
- **Lenguaje:** Java 21
- **Framework:** Spring Boot
- **Base de datos:** MySQL (o MariaDB)
- **Gestor de dependencias:** Maven
- **Entorno de desarrollo:** IntelliJ IDEA o Visual Studio Code
- **Herramientas adicionales:**
  - Lombok (para anotaciones como `@Getter`, `@Setter`, etc.)
  - Hibernate (para ORM)

Instrucciones de instalación
----------------------------
1. **Clonar el repositorio:**
- git clone https://github.com/Etrejos97/Veterinara_CS2.git

3. **Configurar la base de datos:**
- importala base de datos llamada `veterinaria20251` en MySQL.

3. **Configurar el archivo `application.properties`:**
- Edita el archivo `src/main/resources/application.properties` con los datos de tu base de datos:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/veterinaria20251
  =spring.datasource.usernametu_usuario
  spring.datasource.password=tu_contraseña
  spring.jpa.hibernate.ddl-auto=update
  ```

4. **Compilar y ejecutar el proyecto:**
- Compila el proyecto con Maven:
  ```
  mvn clean install
  ```
- Ejecuta la aplicación:
  ```
  mvn spring-boot:run
  ```

Estructura del proyecto
-----------------------
El proyecto está organizado de la siguiente manera:
- **`app/src/main/java`:** Código fuente principal.
  - **`adapters`:** Contiene los adaptadores para interactuar con la base de datos y otros servicios.
  - **`domains`:** Contiene los modelos y servicios principales del dominio.
  - **`ports`:** Define los puertos para la arquitectura hexagonal.
- **`app/src/test/java`:** Pruebas unitarias y de integración.
- **`app/src/main/resources`:** Archivos de configuración, como `application.properties`.

Autores
-------
- **Edison Trejos**
- **Felipe Marin**

Licencia
--------
Este proyecto está bajo la licencia MIT. Puedes consultar el archivo `LICENSE` para más detalles.

