package com.example.myapplication.ui.data

data class CalcUiState(
    val num1: String = "",
    val num2: String = "",
    var op: String = "",
    var currentValue: String = "",
    var history: String = ""
)

const val DEFAULT_CALCULATION_RESULT = 0
