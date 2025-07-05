---

# 🛍️ Bazar API - Gestión de Stock y Ventas

API REST desarrollada con **Spring Boot** para gestionar **productos, clientes y ventas de un bazar**, facilitando el registro de operaciones y el control de stock para su dueña, permitiendo el consumo desde una **aplicación web**.

Este proyecto forma parte de mi **práctica personal y portafolio como desarrollador backend**, integrando **Swagger, MySQL y Docker**.

---

## 🚀 Tecnologías utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **MySQL**
* **Docker**
* **Swagger/OpenAPI para documentación**

---

## 📌 Funcionalidades principales

✅ **CRUD completo de:**

* Productos
* Clientes
* Ventas

✅ **Consultas específicas:**

* Productos con stock menor a la cantidad ingresada
* Listado de productos de una venta
* Total y cantidad de ventas de un día específico
* Venta con el monto más alto (con DTO)

✅ **BONUS implementado:**

* Actualización de stock al realizar una venta de forma automática.

---

## ⚙️ Instalación y ejecución local

1️⃣ **Clonar el repositorio:**

```bash
git clone https://github.com/fariasgustavo313/bazar_api.git
cd bazar_api
```

2️⃣ **Configurar base de datos:**

* Crear una base de datos en **MySQL** llamada `bazar_db` o la que prefieras.
* Configurar `application.properties` o `application.yml` con tus credenciales.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3️⃣ **Ejecutar con Maven:**

```bash
./mvnw spring-boot:run
```

o correr la aplicación desde tu IDE.

4️⃣ **Opcional: ejecutar con Docker**

* Construir la imagen:

```bash
docker build -t bazar_api .
```

* Ejecutar el contenedor:

```bash
docker run -p 8080:8080 bazar_api
```

---

## 📓 Uso de la API

Accede a la **documentación Swagger**:

```
http://localhost:8080/swagger-ui/index.html
```

Desde allí podrás probar todos los endpoints de forma interactiva.

---

## 🪪 Autor

👨‍💻 **Gustavo Farías** – [LinkedIn](https://www.linkedin.com/in/gustavoef)

---
