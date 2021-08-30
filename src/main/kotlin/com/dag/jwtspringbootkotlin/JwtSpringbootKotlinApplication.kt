package com.dag.jwtspringbootkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class, DataSourceAutoConfiguration::class])
class JwtSpringbootKotlinApplication

fun main(args: Array<String>) {
	runApplication<JwtSpringbootKotlinApplication>(*args)
}

@Bean
fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


/*
@Bean
fun run(userService: GenericService) = CommandLineRunner {
		userService.saveRole(Role(0, "ROLE_USER"))
		userService.saveRole(Role(0, "ROLE_MANAGER"))
		userService.saveRole(Role(0, "ROLE_ADMIN"))
		userService.saveRole(Role(0, "ROLE_ROOT"))

		userService.saveUser(User(0, "John Travolta", "john", "1234", mutableListOf()))
		userService.saveUser(User(0, "Will Smith", "will", "1234", mutableListOf()))
		userService.saveUser(User(0, "Jim Carrey", "jim", "1234", mutableListOf()))
		userService.saveUser(User(0, "Arnold Schwarzenegger", "arnold", "1234", mutableListOf()))

		userService.addRoleToUser("john", "ROLE_USER")
		userService.addRoleToUser("john", "ROLE_MANAGER")
		userService.addRoleToUser("will", "ROLE_MANAGER")
		userService.addRoleToUser("jim", "ROLE_ADMIN")
		userService.addRoleToUser("arnold", "ROLE_ROOT")
		userService.addRoleToUser("arnold", "ROLE_ADMIN")
		userService.addRoleToUser("arnold", "ROLE_USER")
}
*/