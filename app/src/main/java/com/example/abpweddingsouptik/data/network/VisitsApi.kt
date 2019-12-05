///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.data.network

import com.example.abpweddingsouptik.data.model.MainResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface VisitsApi {
    //https://sof.abpweddings.com/mats/activity/read/2180746/<year>/<month>/0.json

    @GET("{year}/{month}/0.json")
    suspend fun getResponse(@Path("year")  year:Int, @Path("month") month:Int) : Response<MainResponse>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : VisitsApi {

            val okHttpClient:OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://sof.abpweddings.com/mats/activity/read/2180746/")
                .build()
                .create(VisitsApi::class.java)
        }
    }
}