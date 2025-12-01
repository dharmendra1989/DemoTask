package com.demo.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.repository.MyRepository
import com.demo.android.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.postValue(
            UiState(error = exception.localizedMessage ?: "Unknown error")
        )
    }

    init {
        loadData()
    }

    fun loadData() {
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch(Dispatchers.IO + handler) {
            try {
                val response = repository.getData()
                _uiState.postValue(UiState(data = response))
            } catch (e: Exception) {
                _uiState.postValue(
                    UiState(error = e.localizedMessage ?: "Unknown error")
                )
            }
        }
    }
}
