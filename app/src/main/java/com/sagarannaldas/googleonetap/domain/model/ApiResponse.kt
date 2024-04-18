package com.sagarannaldas.googleonetap.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null
)
