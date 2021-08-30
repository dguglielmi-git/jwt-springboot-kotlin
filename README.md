
# Kotlin Backend with JSON Web Token

This is a basic template for JSON Web Token Project. It manages User and Roles.

## Getting Started 🚀

In order to start using this project, after downloading or have it cloned from the repository, you should configure the database access.
In this project, we are using a MySQL DataBase, but in case you need to use another one, you should provide all these configuration into the DataSource.

* [Connecting utilizing DataSourceConfig Bean](#)

You can start modifying the DataSourceConfig located into configuration folder within the Project, and change all the information regarding DB access. 

* [Connecting utilizing application.properties](#)

Go to JwtSpringbootKotlinApplication.kt and modify it removing the line related to DataSourceAutoConfiguration:class

* Example as you will find it:
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class, DataSourceAutoConfiguration::class])


* After removing it:
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])

Then you can comment or delete the DataSourceConfig, and modify application.properties file by adding the following lines:
```#spring.datasource.url=jdbc:mysql://localhost:3306/labs
# Example of configuration utilizing MySQL
spring.datasource.username=labs
spring.datasource.password=labspassword
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Pre-requisites 📋

To use this example you must have installed a MySQL Engine, or at least, connection data to a MySQL Database. Otherwise, you must adapt this project to connect it to another engine you may like.



### How to Install 🔧
 * This project is already configure to start using as soon as you download it.

## Deploy 📦
 * Depends on the final user of this project.


## Built with 🛠️


* [<b>Spring Data JPA</b>](https://spring.io/projects/spring-data-jpa) - Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
* [<b>Spring WEB</b>](https://spring.io/guides/gs/spring-boot/) - Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default
  embedded container.
* [<b>Spring Security</b>](https://spring.io/projects/spring-security) - Highly customizable authentication and access-control framework for Spring applications.
* [<b>Spring Boot DevTools</b>](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html) - Provides fast application restarts, LiveReload, and configurations for enhanced
  development experience.
* [<b>MySQL Driver</b>](https://spring.io/guides/gs/accessing-data-mysql/) - MySQL JDBC and R2DBC driver.
* [<b>auth0-Java-jwt</b>](https://mvnrepository.com/artifact/com.auth0/java-jwt/3.18.1) - Java implementation of JSON Web Token (JWT)


## Version 📌

We used [SemVer](http://semver.org/) for versioning. Check out the whole version list available in [tagsRepo](https://github.com/dguglielmi-git/snappycloud-backend/tags).


## Author ✒️

**Daniel Guglielmi** - *Designer, Architect, Front-End, Backend, Documentation, Translator* - [dguglielmi-git](https://github.com/dguglielmi-git)
\
I am a Software Developer who enjoys creating software by combining technologies and solving problems for real life.


## License 📄

There is no license.

## Thanks 🎁

I hope you can use this solution and if you want to contribute with it, you are very welcome to join our Snappy team.



---
⌨️ with ❤️ by [dguglielmi-git](https://github.com/dguglielmi-git) 😊