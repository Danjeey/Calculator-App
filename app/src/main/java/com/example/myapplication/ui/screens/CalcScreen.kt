package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalcScreen(viewModel: CalcViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
    ) {
        it
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                DisplayArea(
                    currentValue = uiState.currentValue,
                    calcHistory = "num1 = ${uiState.num1} + num2 = ${uiState.num2} + op = ${uiState.op}",
                    modifier = Modifier.weight(0.8f)
                )
                CalcBoard(modifier = Modifier.weight(1f), viewModel)
            }
        }
    }
}

@Preview
@Composable
fun CalScreenPreview() {
    CalcScreen()
}