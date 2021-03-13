package com.ozgursakizli.simplechat.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _data = MutableLiveData<Boolean>()
    val data: LiveData<Boolean> = _data

    /**
     * we can check from api if nickname is not taken
     * @param nickname value should be at least 2 characters
     * @return Boolean
     */
    fun checkNickname(nickname: String) {
        viewModelScope.launch {
            _data.postValue(nickname.trim().length >= 2)
        }
    }

}