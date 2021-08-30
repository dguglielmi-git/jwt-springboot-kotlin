package com.dag.jwtspringbootkotlin

import com.dag.jwtspringbootkotlin.model.Role
import com.dag.jwtspringbootkotlin.model.User
import com.dag.jwtspringbootkotlin.service.UserService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(
        private val userService: UserService,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
    }
}