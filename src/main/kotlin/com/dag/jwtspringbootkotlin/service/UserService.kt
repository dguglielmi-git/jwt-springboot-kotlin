package com.dag.jwtspringbootkotlin.service

import com.dag.jwtspringbootkotlin.dto.UserDTO
import com.dag.jwtspringbootkotlin.model.User
import com.dag.jwtspringbootkotlin.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
        val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(),
) : GenericService<User, Long>, UserDetailsService {

    fun findByUsername(username: String): User? =
            this.userRepository.findByUsername(username)

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userRepository.findByUsername(username)
        val authorities = mutableListOf<SimpleGrantedAuthority>()

        if (user != null) {
            user.roles.forEach { role -> authorities.add(SimpleGrantedAuthority(role!!.name)) }
            return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
        } else throw UsernameNotFoundException("User not found in the database")
    }

    override fun findAll(): List<User> = this.userRepository.findAll()

    override fun findById(id: Long): User? = this.userRepository.findByIdOrNull(id)

    override fun save(t: User): User {
        t.password = passwordEncoder.encode(t.password)
        return this.userRepository.save(t)
    }

    fun register(user: User): UserDTO {
        user.password = passwordEncoder.encode(user.password)
        val result = this.userRepository.save(user)
        return UserDTO(result.id, result.name, result.username)
    }

    override fun update(t: User): User {
        return if (this.userRepository.existsById(t.id))
            this.userRepository.save(t)
        else throw EntityNotFoundException("${t.id} does not exist")
    }

    override fun deleteById(id: Long): User {
        return this.findById(id).apply {
            this@UserService.userRepository.deleteById(id)
        } ?: throw  EntityNotFoundException("$id doesn exist")
    }
}