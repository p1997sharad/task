package com.example.place.Data.Network

import com.example.place.Data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASE_URL = "https://picsum.photos/v2/"
    }

    @GET("list?page=")
    suspend fun getAllPlaceData(
            @Query("page") page: String
    ):List<DataModel.DataModelItem>
}