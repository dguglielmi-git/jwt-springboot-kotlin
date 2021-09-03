package com.dag.jwtspringbootkotlin.auth

import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class CustomAuthenticationFilter(
        authenticationManager: AuthenticationManager,
        private val tokenUtils: TokenUtils
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val username: String = request.getParameter("username")
        val password: String = request.getParameter("password")
        println("$username - $password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain,
            authentication: Authentication,
    ) {
        tokenUtils.sendTokens(request, response, authentication)
    }
}