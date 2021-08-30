package com.dag.jwtspringbootkotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    var name: String,
    val username: String,
    var password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    var roles: MutableList<Role?> = mutableListOf()
)