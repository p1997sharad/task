package com.example.place.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.place.Data.DataModel
import com.example.place.Data.Network.ApiService
import com.example.place.Data.Repository.PlaceDataPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel()
{
    val getAllDogs:Flow<PagingData<DataModel.DataModelItem>> = Pager(config = PagingConfig(20,enablePlaceholders = false)){
        PlaceDataPageSource(apiService)
    }.flow.cachedIn(viewModelScope)


}
