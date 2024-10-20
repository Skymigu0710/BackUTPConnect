# BackUTPConnect
This is the back end for the UTPConnect platform, developed with Spring Boot. The service provides authentication and authorization functionalities based on JWT (JSON Web Tokens), user management and communication through RabbitMQ to integrate the backend with other microservices of the platform.

Collaborators 
Maricielo Alata Roman User: Skymigu0710 o MaryXD
Joel Raul Palomino Mendoza User:Joel-Palomino
Cris Nataly Quispe Sapa User: CrisNataly 
Jolaus Andy Meza Pérez User: Andybetmp

FUNCIONALIDADES IMPLEMENTADAS:
- Microservicio de autenticacion y usuarios: Autenticación de usuarios y generación de Token (JWT) (falta generar token con bearer).
- Microservicio de autenticacion y usuarios: Registro de usuarios y generación de Token (JWT) (falta generar token con bearer).
- Microservicio de grupos: Registro de grupos (Falta imagenes).
- Microservicio de búsqueda implementado con redis.
  
TECNOLOGÍAS IMPLEMENTADAS y NECESARIAS:
- Erlang + RabbitMQ (Broker)
- Redis
- MySQL Workbench
- Implementación First code
  
ACCIONES A TOMAR
- Crear base de datos para la relación entre los microservicios de autenticacion y usuarios: users y securitydb
- Cada microservicio se encuentra en un puerto diferente y es modificable en el archivo .properties
- Levantar los microservicios de autenticacion y usuarios para la comunicacion entre ellos.
- Probar con Postman
    Register
     ejm:
       http://localhost:8083/auth/register
        {
          "username": "jeanpierrr",
          "password": "3456"
        }
      envía un token
    Login
      ejm:
       http://localhost:8083/auth/login
        {
          "username": "jeanpierrr",
          "password": "3456"
        }
      envía un token 
  - Cabe resaltar que en esta ocasión La autenticacion (login) obtiene datos y los confirma mediante API REST
    y el registro(register) ingresa datos mediante el Broker, es decir se crea una cola.
    
