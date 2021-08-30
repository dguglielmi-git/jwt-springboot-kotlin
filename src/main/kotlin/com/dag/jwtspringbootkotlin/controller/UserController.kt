package com.dag.jwtspringbootkotlin.controller

import com.dag.jwtspringbootkotlin.auth.TokenUtils
import com.dag.jwtspringbootkotlin.dto.UserDTO
import com.dag.jwtspringbootkotlin.model.User
import com.dag.jwtspringbootkotlin.service.UserService
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import java.net.URI
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class UserController(
        private val userService: UserService,
) {
    private val tokenUtils = TokenUtils()

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<User>> = ResponseEntity.ok().body(userService.findAll())

    @PostMapping("/user/save")
    fun saveUser(@RequestBody user: User): ResponseEntity<User> {
        val uri: URI =
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/user/save").toUriString())
        return ResponseEntity.created(uri).body(userService.save(user))
    }

    @PostMapping("/user/register")
    fun registerUser(@RequestBody user: User): ResponseEntity<UserDTO> {
        val uri: URI =
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/user/register").toUriString())
        return ResponseEntity.created(uri).body(userService.register(user))
    }

    @Throws(IOException::class)
    @GetMapping("/token/refresh")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        val authorizationHeader: String? = request.getHeader(AUTHORIZATION)
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                val user: User? = userService.findByUsername(tokenUtils.getUsername(authorizationHeader))
                tokenUtils.updateTokenWithRefresh(response,request, user, authorizationHeader)
            } catch (ex: Exception) {
                tokenUtils.sendErrorResponse(response, ex.message)
            }
        } else {
            throw RuntimeException("Refresh token is missing")
        }
    }

}