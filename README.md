# 🛒 Bazar API

API REST desarrollada en **Java con Spring Boot** para gestionar las ventas, productos y clientes de un bazar.

Este proyecto es el **trabajo práctico integrador final** del curso *"Desarrollo de APIs en Java con Spring Boot"* de **TodoCode Academy**. El objetivo es validar los conocimientos de desarrollo de APIs: la misma API es consumida por dos clientes distintos (una aplicación web y una aplicación móvil), por lo que se implementa como backend único expuesto mediante endpoints REST.

## ✨ Funcionalidades

- **CRUD completo de Productos**, **Clientes** y **Ventas** (GET, POST, PUT, DELETE).
- **Control de stock**: al registrar una venta se descuenta automáticamente la cantidad disponible de cada producto y se valida que haya stock (bonus).
- **Reporte de productos con bajo stock** (cantidad disponible menor a 5).
- **Consulta de productos de una venta** determinada.
- **Resumen diario**: monto total y cantidad de ventas de un día específico.
- **Venta más cara**: codigo, total de productos y datos del cliente, aplicando el **patrón DTO**.

## 🛠️ Tecnologías

| Tecnología | Detalle |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.1 |
| Spring Data JPA | Persistencia |
| MySQL | Base de datos principal |
| H2 | Base de datos de soporte (runtime) |
| Lombok | Reducción de código boilerplate |
| Maven | Gestión de dependencias y build |

## 📐 Modelo de datos

| Entidad | Atributos |
|---|---|
| **Producto** | `codigo_producto`, `nombre`, `marca`, `coste`, `cantidad_disponible` |
| **Venta** | `codigo_venta`, `fecha_venta`, `total`, `listaProductos`, `unCliente` |
| **Cliente** | `id_cliente`, `nombre`, `apellido`, `dni` |

Relaciones:

- Una **Venta** posee una **lista de Productos** (`@ManyToMany`).
- Una **Venta** tiene **uno y solo un Cliente** asociado (`@ManyToOne`).

> 📊 Podés ver el diagrama de clases en [DiagramaClases.drawio.png](DiagramaClases.drawio.png).

## 📡 Endpoints

### Productos

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/productos` | Lista todos los productos |
| `GET` | `/productos/{codigo_producto}` | Trae un producto por su código |
| `POST` | `/productos/crear` | Crea un producto |
| `PUT` | `/productos/editar/{codigo_producto}` | Edita un producto |
| `DELETE` | `/productos/eliminar/{codigo_producto}` | Elimina un producto |
| `GET` | `/productos/falta_stock` | Productos con stock menor a 5 |

### Clientes

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/clientes` | Lista todos los clientes |
| `GET` | `/clientes/{id_cliente}` | Trae un cliente por su id |
| `POST` | `/clientes/crear` | Crea un cliente |
| `PUT` | `/clientes/editar/{id_cliente}` | Edita un cliente |
| `DELETE` | `/clientes/eliminar/{id_cliente}` | Elimina un cliente |

### Ventas

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/ventas` | Lista todas las ventas |
| `GET` | `/ventas/{id_venta}` | Trae una venta por su código |
| `POST` | `/ventas/crear` | Crea una venta (descuenta stock) |
| `PUT` | `/ventas/editar/{id_venta}` | Edita una venta |
| `DELETE` | `/ventas/eliminar/{id_venta}` | Elimina una venta |
| `GET` | `/ventas/productos/{codigo_venta}` | Lista los productos de una venta |
| `GET` | `/ventas/montoyventa/{fecha_venta}` | Monto total y cantidad de ventas de un día (`AAAA-MM-DD`) |
| `GET` | `/ventas/mayor_venta` | Venta con el monto más alto (DTO) |

### Ejemplos de request

**Crear producto**

```json
{
  "nombre": "Vela aromática",
  "marca": "Aroma",
  "coste": 2500.0,
  "cantidad_disponible": 20.0
}
```

**Crear venta**

```json
{
  "fecha_venta": "2025-01-15",
  "listaProductos": [
    { "codigo_producto": 1 },
    { "codigo_producto": 2 }
  ],
  "unCliente": { "id_cliente": 1 }
}
```

**Respuesta de `/ventas/mayor_venta`**

```json
{
  "codigo_venta": 3,
  "cantidad_productos": 5,
  "nombre_cliente": "María",
  "apellido_cliente": "González"
}
```

## 🚀 Puesta en marcha

### Requisitos previos

- JDK 21
- Maven 3.6+
- MySQL (en ejecución)

### Configuración

Configurá la conexión a la base de datos en `BazarApi/src/main/resources/application.properties`:

```properties
spring.application.name=BazarApi
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3307/bazar?seSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

### Ejecución

```bash
cd BazarApi
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

> 💡 La consola de H2 puede habilitarse ajustando la configuración del datasource.

## 📬 Colección de Postman

Incluida en el repositorio: [`Pruebas Postman Bazar Api.postman_collection.json`](Pruebas%20Postman%20Bazar%20Api.postman_collection.json). Importala en [Postman](https://www.postman.com/downloads/) para probar todos los endpoints.

## 🧩 Estructura del proyecto

```
BazarApi/
├── src/main/java/com/example/BazarApi/
│   ├── controller/    # Capa de controladores REST
│   ├── service/       # Lógica de negocio
│   ├── repository/    # Acceso a datos (Spring Data JPA)
│   ├── model/         # Entidades JPA
│   ├── dto/           # Objetos de transferencia de datos
│   ├── exception/     # Manejo de excepciones
│   └── BazarApiApplication.java
└── src/main/resources/application.properties
```

## 👤 Autor

**César Frade** — [CesaarFrade](https://github.com/CesaarFrade)
