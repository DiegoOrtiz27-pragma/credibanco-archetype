# 🧱 Arquetipo de Microservicios Hexagonal - Credibanco

Este documento describe el arquetipo base para la nueva generación de microservicios en **Credibanco**. Su objetivo es proporcionar una base de código estandarizada, robusta y mantenible, que permita un desarrollo rápido y consistente siguiendo las mejores prácticas de la industria.

---

## 📚 Tabla de Contenidos

1. [Principios Fundamentales](#1-principios-fundamentales)
2. [Stack Tecnológico](#2-stack-tecnológico)
3. [Estructura del Proyecto](#3-estructura-del-proyecto)
4. [La Arquitectura Hexagonal en Detalle](#4-la-arquitectura-hexagonal-en-detalle)
5. [Guía de Inicio Rápido](#5-guía-de-inicio-rápido)
6. [Cómo Añadir una Nueva Funcionalidad](#6-cómo-añadir-una-nueva-funcionalidad)
7. [Petición de Retroalimentación](#7-petición-de-retroalimentación)

---

## 1. Principios Fundamentales

Este arquetipo se sustenta en dos principios clave:

- **Arquitectura Hexagonal (Puertos y Adaptadores):**  
  Invertimos el flujo de dependencias tradicional. La lógica de negocio no depende de la tecnología; la tecnología depende de la lógica de negocio. Esto brinda una flexibilidad y capacidad de prueba excepcionales.

- **Domain-Driven Design (DDD):**  
  El enfoque se centra en el **Dominio**, el corazón del software, donde residen las reglas de negocio puras y libres de detalles técnicos.

![ArquitecturaHexagonal](./images/ArquitecturaHexagonal.png)
---

## 2. Stack Tecnológico

- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.3.1
- **Gestión de Proyecto:** Apache Maven
- **Base de Datos (Ejemplo):** PostgreSQL
- **Logging:** SLF4J + Logback
- **Documentación API:** Springdoc OpenAPI 3

---

## 3. Estructura del Proyecto

El proyecto se estructura como un **Maven Multi-módulo** para reflejar una clara separación de responsabilidades:

```scss
credibanco-archetype-parent/
├── domain/ # Dominio: Lógica de negocio pura. No depende de Spring.
├── application/ # Aplicación: Orquesta los casos de uso.
├── infrastructure/ # Infraestructura: Implementaciones tecnológicas.
│ ├── entry-point/ # Adaptadores de entrada (e.g. API REST).
│ │ └── rest/
│ ├── driven-adapter/ # Adaptadores de salida (e.g. BD, servicios externos).
│ │ ├── jpa-adapter/
│ │ ├── logger/
│ │ └── rest-client/
│ └── infrastructure-runner/ # Ejecutable principal con Spring Boot.
```


---

## 4. La Arquitectura Hexagonal en Detalle

### 🧠 El Núcleo (domain & application)

Contiene la lógica central de negocio. Está completamente desacoplado de frameworks y tecnologías externas.

- **Dominio (domain):**
    - Modelos de negocio (`Object.java`)
    - Reglas de negocio (`ObjectService.java`)
    - Interfaces de puertos (`IObjectRepositoryPort`)

- **Aplicación (application):**
    - Casos de uso que implementan los puertos de entrada

### 🔌 Los Puertos

Son interfaces que definen contratos:

- **Puerto de Salida (out):**  
  Lo que el dominio necesita del exterior.  
  Ej: `IObjectRepositoryPort` con métodos como `save()` o `findById()`.

- **Puerto de Entrada (in):**  
  Lo que el dominio ofrece al mundo exterior (casos de uso).

### 🧩 Los Adaptadores

- **Driven Adapter:**  
  Implementa un puerto de salida. Ej:
    - `jpa-adapter` → usa JPA + PostgreSQL
    - `logger` → envía logs a consola o servicio externo

- **Driving Adapter:**  
  Invoca un puerto de entrada. Ej:
    - `rest` → expone los casos de uso como endpoints HTTP

---

## 5. Guía de Inicio Rápido

### ✅ Prerrequisitos

- JDK 21 o superior
- Apache Maven 3.6+
- Conexión a internet para descarga de dependencias

### 🛠️ Compilación

Desde la raíz del proyecto, ejecuta:

```bash
mvn clean install
```

### ▶️ Ejecución
Desde la raíz del proyecto, ejecuta:
```bash
mvn spring-boot:run -pl infrastructure/infrastructure-runner
```

La aplicación iniciará en el puerto 8080.
Puedes probar el endpoint de ejemplo en:
👉 http://localhost:8080/api/v1/test/hello

## 6. Cómo Añadir una Nueva Funcionalidad

### 🧩 Paso a paso

1. Define el Dominio:

- Nuevo modelo: domain/model

- Nuevo puerto: domain/port/in o port/out

2. Crea el Caso de Uso:

- Define la lógica del caso de uso en application/

- Utiliza los puertos del dominio

3. Implementa los Adaptadores:

- Crea o extiende un driven-adapter si usas tecnología externa

- Agrega un nuevo endpoint en el driving-adapter (e.g., controlador REST)