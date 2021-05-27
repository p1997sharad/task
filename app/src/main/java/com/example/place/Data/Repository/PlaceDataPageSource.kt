package com.example.place.Data.Repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.place.Data.DataModel
import com.example.place.Data.Network.ApiService
import retrofit2.HttpException
import java.io.IOException

class PlaceDataPageSource
constructor(private val apiService: ApiService) : PagingSource<Int, DataModel.DataModelItem>() {

    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataModel.DataModelItem> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService.getAllPlaceData(page.toString())
            LoadResult.Page(
                response,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )
        } catch (exception:IOException){
            LoadResult.Error(exception)
        } catch (exception:HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataModel.DataModelItem>): Int? {
     return null
    }
}
