package com.toy.loginwork.repository

import com.toy.loginwork.repository.data.*
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    //    1. 가입 : /users [POST]
    @POST("/users")
    suspend fun requestSign(@Body user: UserData): Response<ResponseResult>

    //    2. 로그인 : /users/sign_in [POST]
    @POST("/users/sign_in")
    suspend fun requestLogin(@Body user: UserWithoutIns): Response<ResponseResult>

    //    3. 홈 데이터 가져오기 : /home [GET]
    @GET("/home")
    suspend fun requestHomeData(): Response<HomeData>

    //    4. 사진피드 데이터 가져오기 : /cards [GET]
    @GET("/cards")
    suspend fun requestCards(
        @Query(value = "page", encoded = true) page: Int,
        @Query(value = "per", encoded = true) per: Int
    ): Response<PictureData>

    //    5. 사진상세 데이터 가져오기 : /cards/{id} [GET]
    @GET("/cards/{id}")
    suspend fun requestCardDetail(@Path("id") id: String): Response<CardDetailData>

    //    6. 유저상세 데이터 가져오기 : /users/{id} [GET]
    @GET("/users/{id}")
    suspend fun requestUserDetail(@Path("id") id: String): Response<UserDetailData>
}