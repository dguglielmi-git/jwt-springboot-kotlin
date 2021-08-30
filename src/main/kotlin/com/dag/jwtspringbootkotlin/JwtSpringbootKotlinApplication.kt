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
