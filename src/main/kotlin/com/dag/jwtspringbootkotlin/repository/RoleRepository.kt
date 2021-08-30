package com.dag.jwtspringbootkotlin.repository

import com.dag.jwtspringbootkotlin.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(role: String): Role?
}