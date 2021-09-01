
# Kotlin Backend with JSON Web Token

This is a basic template for JSON Web Token Project. It manages User and Roles.

## Getting Started 🚀

In order to start using this project, after downloading it or have it cloned from the repository, you should configure the database access.
In this sample project, we are using a MySQL DataBase, but in case you need to use some other engine, you should provide all the configuration details into the DataSource.

### Connection
* [Connecting utilizing DataSourceConfig Bean](#)

You can start modifying the DataSourceConfig located into configuration folder within the Project, and change all the information regarding DB access. 

* [Connecting utilizing application.properties](#)

Go to JwtSpringbootKotlinApplication.kt and remove the line related to DataSourceAutoConfiguration:class within exclude clause declared in @SpringBootApplication annotation.

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

### Token Secret Key
Another important point to take into account, is the secret key for the token code.
Below you will find a snippet of code where the project is generating the token code utilizing a secret key, that in this case and of course, because this is a template, we are using "secret", that for obvious reasons, I suggest and strongly recommend to you, changing it for something stronger.

```
# path of this code: com.dag.jwtspringbootkotlin.auth

class TokenUtils {
    private lateinit var request: HttpServletRequest
    private val algorithm: Algorithm = Algorithm.HMAC256("secret".toByteArray())
    
    
    ... rest of the TokenUtils class code ...
}
```

It is up to you to choose a great secret key for the token code generator, but anyway, I will give you some options that you can use to create something good:
* [KeyGen.io](https://keygen.io/)
* [RandomKeyGen](https://randomkeygen.com/)

Utilizing a key generator is the first step, then you could decide how to apply it. 
In the example you will see that it is being used as hard-code string, that is fair enough for educational purposes, but I would not recommend it for production projects.

### Suggestions for Secret key
You could use an environment variable and read it from the system by calling System.getenv("NAME_OF_VARIABLE")

Defining an environment variable you will be able to get it straight forward from the system where the project is running.
Therefore, you won't have to worry about sharing this key in your repositories or any other version control system that you may use. 
Just be careful and remember setting your environment variable in order to avoid the error messages when you initiate this project, and it is missing this important key.

```
# Example of SECRET KEY stored as environment variable
SECRET_JWTKOTLIN=G73rRnBODY5T9QMAX80hVvwN16HtKEIb
```

* Code snippet of How to get the key from environment variable:
```
# path of this code: com.dag.jwtspringbootkotlin.auth

class TokenUtils {
private lateinit var request: HttpServletRequest
private val algorithm: Algorithm.HMAC256(System.getenv("SECRET_JWTKOTLIN").toByteArray())


    ... rest of the TokenUtils class code ...
}
```




## Pre-requisites 📋

To use this example you must have installed a MySQL Engine, or at least, connection data to a MySQL Database. Otherwise, you must adapt this project to connect it to another engine you like.


## How to Install 🔧
 * This project is already configured to start using it as soon as you download it.

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

We used [SemVer](http://semver.org/) for versioning. Check out the whole version list available in [tagsRepo](https://github.com/dguglielmi-git/jwt-springboot-kotlin/tags).


## Author ✒️

**Daniel Guglielmi** - *Designer, Architect, Front-End, Backend, Documentation, Translator* - [dguglielmi-git](https://github.com/dguglielmi-git)
\
I am a Software Developer who enjoys creating software by combining technologies and solving problems for real life.


## License 📄

There is no license.

## Thanks 🎁

I hope you can use this template for your future projects.



---
⌨️ with ❤️ by [dguglielmi-git](https://github.com/dguglielmi-git) 😊 - Happy Coding!