package com.dag.jwtspringbootkotlin.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenUtils {
    private lateinit var request: HttpServletRequest
    private val algorithm: Algorithm = Algorithm.HMAC256("secret".toByteArray())
    private val timeoutToken = 10 * 60 * 1000
    private val refreshTimeOutToken = 30 * 60 * 1000

    fun sendTokens(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authentication: Authentication) {
        this.request = request
        val user: User = authentication.principal as User
        val accessToken = getTokenFromUserDetails(user)
        val refreshToken = getRefreshToken(user)

        val tokens = mutableMapOf<String, String>()
        tokens["access_token"] = accessToken
        tokens["refresh_token"] = refreshToken
        sendResponse(response, tokens)
    }

    fun updateTokenWithRefresh(
            response: HttpServletResponse,
            request: HttpServletRequest,
            user: com.dag.jwtspringbootkotlin.model.User?,
            authorizationHeader: String) {
        this.request = request
        val refreshToken = getBearer(authorizationHeader)
        val accessToken: String = getTokenFromUserModel(user)

        val tokens = mutableMapOf<String, String>()
        tokens["access_token"] = accessToken
        tokens["refresh_token"] = refreshToken
        sendResponse(response, tokens)
    }

    fun getUsername(authorizationHeader: String): String = getDecodedJWT(authorizationHeader).subject

    fun getAuthorities(authorizationHeader: String): MutableList<SimpleGrantedAuthority> {
        val authorities = mutableListOf<SimpleGrantedAuthority>()
        val decodedJWT = getDecodedJWT(authorizationHeader)
        val roles = decodedJWT.getClaim("roles").asArray(String::class.java).toMutableList()
        roles.forEach { role -> authorities.add(SimpleGrantedAuthority(role)) }
        return authorities
    }

    fun sendErrorResponse(response: HttpServletResponse, msg: String?) {
        response.setHeader("error", msg)
        response.status = HttpStatus.FORBIDDEN.value()
        val error = mutableMapOf<String, String?>()
        error["error_message"] = msg
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        ObjectMapper().writeValue(response.outputStream, error)
    }

    private fun sendResponse(response: HttpServletResponse, obj: Any) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        ObjectMapper().writeValue(response.outputStream, obj)
    }

    private fun getBearer(authorizationHeader: String): String =
            authorizationHeader.substring("Bearer ".length)

    private fun getDecodedJWT(authorizationHeader: String): DecodedJWT {
        val token: String = getBearer(authorizationHeader)
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    private fun getTokenFromUserDetails(user: User) = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + timeoutToken))
            .withIssuer(request.requestURL.toString())
            .withClaim("roles",
                    user.authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
            .sign(algorithm)

    private fun getTokenFromUserModel(user: com.dag.jwtspringbootkotlin.model.User?) = JWT.create()
            .withSubject(user?.username)
            .withExpiresAt(Date(System.currentTimeMillis() + timeoutToken))
            .withIssuer(request.requestURL.toString())
            .withClaim("roles", user?.roles)
            .sign(algorithm)

    private fun getRefreshToken(user: User) = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + refreshTimeOutToken))
            .withIssuer(request.requestURL.toString())
            .sign(algorithm)
}