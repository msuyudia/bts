package com.suy.bts.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suy.bts.model.request.RegisterRequest
import com.suy.bts.model.type.ResponseType
import com.suy.bts.repository.Repository
import com.suy.bts.utils.Resource
import com.suy.bts.utils.message
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel : ViewModel() {
    private val mutableLiveData by lazy { MutableLiveData<Resource<String?>>() }

    fun register(email: String, username: String, password: String) {
        viewModelScope.launch {
            mutableLiveData.postValue(Resource.loading())
            try {
                val registerRequest = RegisterRequest(email, username, password)
                val response = Repository.apiLoginRegister.register(registerRequest)
                when (response.code()) {
                    ResponseType.SUCCESS.code, ResponseType.CREATED.code ->
                        mutableLiveData.postValue(Resource.success(response.body()?.message))
                    else ->
                        mutableLiveData.postValue(
                            Resource.error(response.errorBody()?.message())
                        )
                }
            } catch (e: IOException) {
                mutableLiveData.postValue(Resource.error("Problem connection, please check your connection!"))
            }
        }
    }

    fun liveData(): LiveData<Resource<String?>> = mutableLiveData
}
