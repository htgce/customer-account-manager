# customer-account-manager
Rest Api Service Responsible For Customer Account Management

### Explore Rest APIs

<table style="width:100%">
  <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Description</th>
      <th>Request Body</th>
      <th>Header</th>
      <th>Valid Path Variable</th>
      <th>Request Param</th>
      <th>No Path Variable</th>
  </tr>
  <tr>
      <td>POST</td>
      <td>/register</td>
      <td>Register a new user</td>
      <td>RegisterationRequest request</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
  <tr>
      <td>POST</td>
      <td>/logon</td>
      <td>Logon user </td>
      <td>LogonRequest request</td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>

  <tr>
    <td>POST</td>
    <td>/account/overview</td>
    <td>Get user info</td>
    <td>Authorization header with Bearer token</td>
    <td></td>
    <td></td>
  </tr>
</table>


---
### Technologies


- Java 17
- Spring Boot 3.0
- Spring Security
- JWT
- Restful API
- Lombok
- Maven
- Junit5
- Mockito
- Integration Tests
- Docker
- Docker Compose
- CI/CD (Github Actions)
- Postman
- Actuator
- Open Api (Swagger 3)

---
### Postman

```
Import postman collection under postman_collection folder
```

---
### Open Api (Swagger 3)

```
http://localhost:1222/swagger-ui/index.html
http://localhost:1222/v3/api-docs
```


- Maven or Docker
---
### Docker Run
The application can be built and run by the `Docker` engine. The `Dockerfile` has multistage build, so you do not need to build and run separately.

Please follow directions shown below in order to build and run the application with Docker Compose file;

```sh
$ cd customer-accunt-manager
$ docker-compose up -d
```

If you change anything in the project and run it on Docker, you can also use this command shown below

```sh
$ cd customer-account-manager
$ docker-compose up --build
```

---
### Maven Run
To build and run the application with `Maven`, please follow the directions shown below;

```sh
$ cd customer-account-manager
$ mvn clean install
$ mvn spring-boot:run
```
---
### Open Api (Swagger 3)

```
http://localhost:1222/swagger-ui/index.html
```

---
### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 11.52.02.png">
    <p> Figure 2 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 18.40.08.png">
    <p> Figure 3 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 18.40.12.png">
    <p> Figure 4 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 18.40.16.png">
    <p> Figure 5 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 18.40.19.png">
    <p> Figure 6 </p>
    <img src ="screenshots/Screenshot 2024-06-14 at 18.40.01.png">
</details>

