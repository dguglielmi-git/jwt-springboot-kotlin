package com.dag.jwtspringbootkotlin.security

import com.dag.jwtspringbootkotlin.auth.CustomAuthenticationFilter
import com.dag.jwtspringbootkotlin.auth.CustomAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
        val userDetailsService: UserDetailsService,
) : WebSecurityConfigurerAdapter() {

    private val bCryptPasswordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Throws
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Throws
    override fun configure(http: HttpSecurity) {
        val customAuthenticationFilter =
                CustomAuthenticationFilter(authenticationManagerBean())
        customAuthenticationFilter.setFilterProcessesUrl("/api/login")
        http.csrf().disable()
        http.sessionManagement().sessionCreationPolicy(STATELESS)
        http.authorizeRequests().antMatchers(
                "/api/login/**",
                "/api/token/refresh",
                "/api/user/register/**"
        )
                .permitAll()
        http.authorizeRequests().antMatchers(GET, "/api/users/**")
                .hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers(POST, "/api/user/save/**")
                .hasAnyAuthority("ROLE_ADMIN")
        http.authorizeRequests().anyRequest().authenticated()
        http.addFilter(customAuthenticationFilter)
        http.addFilterBefore(CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager =
            super.authenticationManagerBean()
}