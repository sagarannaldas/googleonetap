package com.sagarannaldas.googleonetap.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val emailAddress: String,
    val profilePhoto: String
)
