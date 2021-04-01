package com.suy.bts.ui.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suy.bts.model.data.SavedChecklistModel
import com.suy.bts.repository.Repository
import com.suy.bts.utils.Resource
import kotlinx.coroutines.launch

class CreateChecklistViewModel : ViewModel() {
    private val mutableLiveData by lazy { MutableLiveData<Resource<SavedChecklistModel>>() }

    //fun saveChecklist(name: String) {
    //    viewModelScope.launch {
    //        mutableLiveData.postValue(Resource.loading())
    //        try {
    //            val response = Repository.api.saveChecklist(name)
    //        }
    //    }
    //}
}
