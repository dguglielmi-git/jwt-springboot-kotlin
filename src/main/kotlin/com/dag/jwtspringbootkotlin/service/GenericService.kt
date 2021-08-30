package com.dag.jwtspringbootkotlin.service

import com.dag.jwtspringbootkotlin.model.Role
import com.dag.jwtspringbootkotlin.model.User

interface GenericService<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(t: T): T
    fun update(t: T): T
    fun deleteById(id: ID): T
}