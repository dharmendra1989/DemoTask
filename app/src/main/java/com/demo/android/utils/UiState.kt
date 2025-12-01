package com.demo.android.utils
import com.demo.android.model.ApiResponse
import com.demo.android.model.UserHolding

data class UiState(
    val isLoading: Boolean = false,
    val data: ApiResponse? = null,
    val error: String? = null
)