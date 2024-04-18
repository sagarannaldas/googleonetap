package com.sagarannaldas.googleonetap.data.remote

import com.sagarannaldas.googleonetap.domain.model.ApiRequest
import com.sagarannaldas.googleonetap.domain.model.ApiResponse
import com.sagarannaldas.googleonetap.domain.model.UserUpdate
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface KtorApi {
    @POST("/token_verification")
    suspend fun verifyTokenBackend(
        @Body request: ApiRequest
    ): ApiResponse

    @GET("/get_user")
    suspend fun getUserInfo(): ApiResponse

    @PUT("/update_user")
    suspend fun updateUserInfo(
        @Body userUpdate: UserUpdate
    ): ApiResponse
    @DELETE("/delete_user")
    suspend fun deleteUser(): ApiResponse

    @GET("/sign_out")
    suspend fun clearSession(): ApiResponse

}