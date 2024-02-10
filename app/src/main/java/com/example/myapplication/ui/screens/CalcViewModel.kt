package com.example.myapplication.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.data.CalcUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalcViewModel(
    calcUiState: CalcUiState,
    private val calculator: Calculator = Calculator()
) : ViewModel() {
    private val _uiState = MutableStateFlow(calcUiState)
    val uiState = _uiState.asStateFlow()

    private val numbers = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    private val ops = listOf("/", "*", "-", "+")

    private fun onClear() {
        _uiState.value = CalcUiState()
    }

    fun onButtonClick(input: Any) {
        val num1 = _uiState.value.num1
        val op = _uiState.value.op
        val num2 = _uiState.value.num2
        val inputString = input.toString()


        fun regularButtons() {

            if (op.isNotBlank()) {
                if (numbers.contains(inputString) || inputString == ".") {
                    if (inputString == "." && num2.contains(".")) return
                    else {
                        _uiState.value = _uiState.value.copy(
                            num2 = _uiState.value.num2.plus(inputString),
                            currentValue = _uiState.value.currentValue.plus(inputString)
                        )
                    }
                } else return
            } else if (op.isBlank() && num1.isNotBlank()) {
                if (ops.contains(inputString)) {
                    _uiState.value = _uiState.value.copy(
                        op = _uiState.value.op.plus(inputString),
                        currentValue = _uiState.value.currentValue.plus(inputString)
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        num1 = _uiState.value.num1.plus(inputString),
                        currentValue = _uiState.value.currentValue.plus(inputString)
                    )
                }
            } else {
                if (numbers.contains(inputString) || inputString == ".") {
                    if (inputString == "." && num1.contains(".")) return
                    else {
                        _uiState.value = _uiState.value.copy(
                            num1 = _uiState.value.num1.plus(inputString),
                            currentValue = _uiState.value.currentValue.plus(inputString)
                        )
                    }
                } else return
            }
        }

        fun onDel() {
            _uiState.value =
                _uiState.value.copy(currentValue = _uiState.value.currentValue.dropLast(1))

            if (num2.isNotBlank()) {
                _uiState.value = _uiState.value.copy(
                    num2 = _uiState.value.num2.dropLast(1)
                )
            } else if (op.isNotBlank()) {
                _uiState.value = _uiState.value.copy(
                    op = _uiState.value.op.drop(1)
                )
            } else if (num1.isNotBlank()) {
                _uiState.value = _uiState.value.copy(
                    num1 = _uiState.value.num1.dropLast(1)
                )
            } else if (num1.isBlank()) {
                _uiState.value = _uiState.value.copy(
                    num1 = uiState.value.currentValue
                )
            } else return
        }

        fun onDecimalInput() {
            if (num2.isNotBlank() && !num2.contains('.')) {
                _uiState.value = _uiState.value.copy(
                    num2 = _uiState.value.num2.plus("."),
                    currentValue = _uiState.value.currentValue.plus(".")
                )
            } else if (num1.isNotBlank() && !num1.contains('.')) {
                _uiState.value = _uiState.value.copy(
                    num1 = _uiState.value.num1.plus("."),
                    currentValue = _uiState.value.currentValue.plus(".")
                )
            } else return
        }

        fun onCalculate() {
            val result = calculator.calculate(num1.toDouble(), num2.toDouble(), op)
            if (num1.isBlank() || num2.isBlank() || op.isBlank()) return
            else _uiState.value = CalcUiState(currentValue = result, num1 = result)
        }

        viewModelScope.launch {
            when (inputString) {
                "AC" -> onClear()
                "." -> onDecimalInput()
                " " -> return@launch
                "%" -> return@launch
                "=" -> onCalculate()
                "del" -> onDel()
                else -> regularButtons()
            }
        }

    }
}