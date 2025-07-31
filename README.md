# 🧠 ForoHub API

API REST construida con **Spring Boot 3**, que permite gestionar usuarios, autenticación con JWT, cursos, tópicos y respuestas en un foro académico. Proyecto realizado como parte del programa de formación de **Alura Latam - ONE**.

---

## 🚀 Tecnologías usadas

- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT (JSON Web Tokens)  
- JPA / Hibernate  
- MySQL  
- Maven  

---

## 🔐 Autenticación

La API utiliza **autenticación con JWT**. Para acceder a los endpoints protegidos:

1. Envía una solicitud `POST /login` con correo y contraseña.
2. Recibirás un token JWT.
3. Usa el token en los headers de tus peticiones:

```http
Authorization: Bearer TU_TOKEN
```
## 🔓 Autenticación

POST /login,

Body JSON:
```http
{
  "correo": "correo0@ejemplo.com",
  "contrasena": "12345678"
}
```
<img width="689" height="255" alt="image" src="https://github.com/user-attachments/assets/bbd067d9-11d0-4b13-8219-f99b1dcc86eb" />


<img width="696" height="263" alt="image" src="https://github.com/user-attachments/assets/2af1da60-bb85-4d69-9fc5-71b1d46d4505" />


Ejemplo para crear un tópico:
```http
{
  "titulo": "¿Cómo funciona Spring Security?",
  "mensaje": "No entiendo el flujo de autenticación con JWT",
  "autorId": 1,
  "cursoId": 1
}

```
## ⚙️ Configuración de entorno
Archivo application.properties:
```http
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update

api.security.token.secret=tu_clave_secreta_segura

```

## 🧑‍💻 Desarrolladora
Silvia Hernández Márquez

