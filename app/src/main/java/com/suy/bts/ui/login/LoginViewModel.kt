package com.suy.bts.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suy.bts.model.request.LoginRequest
import com.suy.bts.model.type.ResponseType
import com.suy.bts.repository.Repository
import com.suy.bts.sharedpreference.PrefManager
import com.suy.bts.utils.Resource
import com.suy.bts.utils.message
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel : ViewModel() {
    private val mutableLiveData by lazy { MutableLiveData<Resource<String?>>() }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            mutableLiveData.postValue(Resource.loading())
            try {
                val loginRequest = LoginRequest(password, username)
                val response = Repository.apiLoginRegister.login(loginRequest)
                when (response.code()) {
                    ResponseType.SUCCESS.code -> {
                        PrefManager.saveToken(response.body()?.data?.token ?: "")
                        mutableLiveData.postValue(Resource.success(response.body()?.message))
                    }
                    else -> mutableLiveData.postValue(Resource.error(response.errorBody()?.message()))
                }
            } catch (e: IOException) {
                mutableLiveData.postValue(Resource.error("Problem connection, please check your connection!"))
            }
        }
    }

    fun liveData(): LiveData<Resource<String?>> = mutableLiveData
}
