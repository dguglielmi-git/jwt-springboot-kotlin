package com.dag.jwtspringbootkotlin.auth

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class CustomAuthorizationFilter : OncePerRequestFilter() {

    private val tokenUtils = TokenUtils()
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain,
    ) {
        if ((request.servletPath == "/api/login") || (request.servletPath == "/api/token/refresh")) {
            filterChain.doFilter(request, response)
        } else {
            val authorizationHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    val username: String = tokenUtils.getUsername(authorizationHeader)
                    val authorities = tokenUtils.getAuthorities(authorizationHeader)

                    SecurityContextHolder.getContext().authentication =
                            UsernamePasswordAuthenticationToken(username, null, authorities)

                    filterChain.doFilter(request, response)
                } catch (ex: Exception) {
                    tokenUtils.sendErrorResponse(response, ex.message)
                }
            } else {
                filterChain.doFilter(request, response)
            }
        }
    }

}