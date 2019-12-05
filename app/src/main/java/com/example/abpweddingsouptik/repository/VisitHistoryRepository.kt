///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.repository

import com.example.abpweddingsouptik.data.model.MainResponse
import com.example.abpweddingsouptik.data.network.VisitsApi
import com.example.abpweddingsouptik.util.ApiException

class VisitHistoryRepository(private val api: VisitsApi)  {

    suspend fun getVisitHistory(year:Int,month: Int): MainResponse{
        val response = api.getResponse(year = year, month = month  )
        if(response.isSuccessful){
            return response.body()!!
        }else{
            throw ApiException(response.errorBody().toString())
        }
    }


}