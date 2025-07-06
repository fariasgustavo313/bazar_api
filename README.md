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

* Productos (con Soft Delete implementado)
* Clientes (con Soft Delete implementado)
* Ventas

✅ **Consultas y operaciones específicas:**

* Obtener productos con stock menor a la cantidad ingresada por el usuario
  `GET /productos/falta_stock/{cantidad}`

* Buscar productos por marca o nombre (parámetro query `?q=`)
  `GET /productos/buscar?q=texto`

* Obtener lista de productos de una venta específica
  `GET /ventas/productos/{id_venta}`

* Obtener resumen de ventas (total monto y cantidad) para un día específico
  `GET /ventas/resumen/{fecha_venta}` (formato `YYYY-MM-DD`)

* Obtener venta con el monto más alto (con DTO que incluye código de venta, total, cantidad de productos, nombre y apellido del cliente)
  `GET /ventas/mayor_venta`

* Obtener ventas realizadas por un cliente según su DNI
  `GET /ventas/cliente/{dni}`

* Exportar todas las ventas a CSV
  `GET /ventas/exportar_csv`

✅ **Bonus implementados:**

* Soft Delete para productos y clientes (no se eliminan físicamente, solo se marcan como eliminados, y las consultas traen solo activos)
* Actualización automática del stock al realizar una ventaGracias por el detalle, eso ayuda a ajustar el README a lo que realmente tenés implementado.

Como no tenés búsqueda de clientes por apellido, voy a eliminar esa parte del README y dejar solo los endpoints que usás actualmente.

Te dejo el README actualizado, con los endpoints organizados y las funcionalidades reflejadas exactamente según lo que mostraste:

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

* Productos (con Soft Delete implementado)
* Clientes (con Soft Delete implementado)
* Ventas

✅ **Consultas y operaciones específicas:**

* Obtener productos con stock menor a la cantidad ingresada por el usuario
  `GET /productos/falta_stock/{cantidad}`

* Buscar productos por marca o nombre (parámetro query `?q=`)
  `GET /productos/buscar?q=texto`

* Obtener lista de productos de una venta específica
  `GET /ventas/productos/{id_venta}`

* Obtener resumen de ventas (total monto y cantidad) para un día específico
  `GET /ventas/resumen/{fecha_venta}` (formato `YYYY-MM-DD`)

* Obtener venta con el monto más alto (con DTO que incluye código de venta, total, cantidad de productos, nombre y apellido del cliente)
  `GET /ventas/mayor_venta`

* Obtener ventas realizadas por un cliente según su DNI
  `GET /ventas/cliente/{dni}`

* Exportar todas las ventas a CSV
  `GET /ventas/exportar_csv`

✅ **Bonus implementados:**

* Soft Delete para productos y clientes (no se eliminan físicamente, solo se marcan como eliminados, y las consultas traen solo activos)
* Actualización automática del stock al realizar una venta

---

## ⚙️ Instalación y ejecución local

1️⃣ **Clonar el repositorio:**

```bash
git clone https://github.com/fariasgustavo313/bazar_api.git
cd bazar_api
```

2️⃣ **Configurar base de datos:**

* Crear una base de datos en **MySQL** llamada `bazar_db` o la que prefieras.
* Configurar `application.properties` o `application.yml` con tus credenciales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3️⃣ **Ejecutar con Maven:**

```bash
./mvnw spring-boot:run
```

o correr la aplicación desde tu IDE favorito.

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

Accede a la **documentación Swagger** para explorar y probar los endpoints de forma interactiva:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 Notas importantes

* Al crear una venta, solo se permiten productos y clientes existentes previamente.
* Los productos y clientes eliminados (soft delete) no aparecen en las listas ni búsquedas activas.
* El campo `cantidad_disponible` de productos se actualiza automáticamente al realizar una venta.

---

## 🪪 Autor

👨‍💻 **Gustavo Farías** – [LinkedIn](https://www.linkedin.com/in/gustavoef)

---

## ⚙️ Instalación y ejecución local

1️⃣ **Clonar el repositorio:**

```bash
git clone https://github.com/fariasgustavo313/bazar_api.git
cd bazar_api
```

2️⃣ **Configurar base de datos:**

* Crear una base de datos en **MySQL** llamada `bazar_db` o la que prefieras.
* Configurar `application.properties` o `application.yml` con tus credenciales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3️⃣ **Ejecutar con Maven:**

```bash
./mvnw spring-boot:run
```

o correr la aplicación desde tu IDE favorito.

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

Accede a la **documentación Swagger** para explorar y probar los endpoints de forma interactiva:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 Notas importantes

* Al crear una venta, solo se permiten productos y clientes existentes previamente.
* Los productos y clientes eliminados (soft delete) no aparecen en las listas ni búsquedas activas.

---

## 🪪 Autor

👨‍💻 **Gustavo Farías** – [LinkedIn](https://www.linkedin.com/in/gustavoef)

---
