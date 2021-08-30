package com.dag.jwtspringbootkotlin.dto

import com.dag.jwtspringbootkotlin.model.Role
import java.io.Serializable

class UserDTO(
        val id: Long,
        val name: String,
        val username: String,
): Serializable