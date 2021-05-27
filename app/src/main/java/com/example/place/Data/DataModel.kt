package com.example.place.Data
import com.squareup.moshi.Json


class DataModel : ArrayList<DataModel.DataModelItem>(){
    data class DataModelItem(
        @Json(name = "author")
        val author: String?,
        @Json(name = "download_url")
        val downloadUrl: String?,
        @Json(name = "height")
        val height: Int?,
        @Json(name = "id")
        val id: String?,
        @Json(name = "url")
        val url: String?,
        @Json(name = "width")
        val width: Int?
    )
}