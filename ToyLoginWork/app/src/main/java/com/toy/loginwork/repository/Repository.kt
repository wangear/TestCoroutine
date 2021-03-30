package com.toy.loginwork.repository

import com.toy.loginwork.repository.data.*
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val webservice: ApiInterface) {
    companion object {
        val PER = 20
    }

    //    1. 가입 : /users [POST]
    suspend fun requestSign(userData: UserData): Response<ResponseResult> {
        return webservice.requestSign(userData)
    }

    //    2. 로그인 : /users/sign_in [POST]
    suspend fun requestLogin(userData: UserWithoutIns): Response<ResponseResult> {
        return webservice.requestLogin(userData)
    }

    suspend fun requestHomeData(): Response<HomeData> {
        return webservice.requestHomeData()
    }

    //    4. 사진피드 데이터 가져오기 : /cards [GET]
    suspend fun requestPictures(page: Int, per: Int): Response<PictureData> {
        return webservice.requestCards(page = page, per = per)
    }

    //    5. 사진상세 데이터 가져오기 : /cards/{id} [GET]
    suspend fun requestCardDetail(id: String): Response<CardDetailData> {
        return webservice.requestCardDetail(id)
    }

    //    6. 유저상세 데이터 가져오기 : /users/{id} [GET]
    suspend fun requestUserDetail(userId: String): Response<UserDetailData> {
        return webservice.requestUserDetail(userId)
    }
}