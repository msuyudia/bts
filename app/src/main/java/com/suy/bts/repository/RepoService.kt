package com.suy.bts.repository

import com.suy.bts.model.data.ListChecklistModel
import com.suy.bts.model.data.LoginModel
import com.suy.bts.model.data.SavedChecklistModel
import com.suy.bts.model.request.CreateChecklistRequest
import com.suy.bts.model.request.LoginRequest
import com.suy.bts.model.request.RegisterRequest
import com.suy.bts.model.response.ListResponse
import com.suy.bts.model.response.ObjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RepoService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<ObjectResponse<LoginModel>>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ObjectResponse<Void>>

    @GET("checklist")
    suspend fun getChecklist(): Response<ListResponse<ListChecklistModel>>

    @POST("checklist")
    suspend fun saveChecklist(createChecklistRequest: CreateChecklistRequest): Response<ObjectResponse<SavedChecklistModel>>
}