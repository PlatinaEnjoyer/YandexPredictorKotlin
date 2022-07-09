package com.example.yandexpredictorkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NextWordApi {

    //https://umorili.herokuapp.com/api/get?site=anekdot.ru&num=5&name=new%20anekdot
    //https://predictor.yandex.net/api/v1/predict.json/complete?key=API-ключ&q=hello+wo&lang=en

    @GET("/api/v1/predict.json/complete?")
    fun getDataAnekdot(
        @Query("key") kye: String?,
        @Query("q") word: String?,
        @Query("lang") lang: String?,
        @Query("limit") limit: Int?
    ): Call<NextWord?>?


}