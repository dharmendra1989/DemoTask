package com.demo.android.utils

import java.text.DecimalFormat

object Constants {
    fun formatDoubleAmount(amount: Double?): String {
        val format = DecimalFormat("#.##")
        return format.format(amount)
    }
}