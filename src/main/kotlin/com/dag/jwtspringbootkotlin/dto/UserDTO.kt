package com.dag.jwtspringbootkotlin.dto

import java.io.Serializable

class UserDTO(
        val id: Long,
        val name: String,
        val username: String,
): Serializable