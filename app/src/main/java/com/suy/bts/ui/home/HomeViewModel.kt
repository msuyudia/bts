package com.suy.bts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suy.bts.model.data.ListChecklistModel
import com.suy.bts.model.type.ResponseType
import com.suy.bts.repository.Repository
import com.suy.bts.utils.Resource
import com.suy.bts.utils.message
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel : ViewModel() {
    private val mutableLiveData by lazy { MutableLiveData<Resource<List<ListChecklistModel>?>>() }

    fun getChecklist() {
        viewModelScope.launch {
            mutableLiveData.postValue(Resource.loading())
            try {
                val response = Repository.api.getChecklist()
                when (response.code()) {
                    ResponseType.SUCCESS.code -> mutableLiveData.postValue(
                        Resource.success(
                            response.body()?.data ?: mutableListOf()
                        )
                    )
                    else -> mutableLiveData.postValue(Resource.error(response.errorBody()?.message()))
                }
            } catch (e: IOException) {
                mutableLiveData.postValue(Resource.error("Problem connection, please check your connection!"))
            }
        }
    }

    fun liveData(): LiveData<Resource<List<ListChecklistModel>?>> = mutableLiveData
}
