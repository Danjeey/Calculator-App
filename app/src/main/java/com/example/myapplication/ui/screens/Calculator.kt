package com.example.myapplication.ui.screens

import com.example.myapplication.ui.data.DEFAULT_CALCULATION_RESULT

class Calculator {
    private fun add(num1: Double, num2: Double): Double {
        return num1 + num2
    }
    private fun sub(num1: Double, num2: Double): Double {
        return num1 - num2
    }
    private fun divide(num1: Double, num2: Double): Double {
        return num1 / num2
    }
    private fun multi(num1: Double, num2: Double): Double {
        return num1 * num2
    }

    fun calculate(num1: Double,num2: Double, op: String): String {
        val result = when (op) {
            "+" -> add(num1, num2)
            "-" -> sub(num1, num2)
            "/" -> divide(num1, num2)
            "*" -> multi(num1, num2)
            else -> DEFAULT_CALCULATION_RESULT
        }
        return result.toString()
    }
}