package com.example.myapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalcButton(
    input: Any,
    viewModel: CalcViewModel
) {
    Card(
        modifier = Modifier
            .size(72.dp)
            .clickable { viewModel.onButtonClick(input) },
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = input.toString(),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun ButtonRow(
    rowList: List<Any>,
    viewModel: CalcViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        rowList.forEach {
            CalcButton(input = it, viewModel)
        }
    }
}

@Composable
fun CalcBoard(modifier: Modifier, viewModel: CalcViewModel) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ButtonRow(rowList = rowOne, viewModel)
        ButtonRow(rowList = rowTwo, viewModel)
        ButtonRow(rowList = rowThree, viewModel)
        ButtonRow(rowList = rowFour, viewModel)
        ButtonRow(rowList = rowFive, viewModel)
    }
}

@Composable
fun DisplayArea(
    currentValue: String,
    calcHistory: String,
    modifier: Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = calcHistory, style = MaterialTheme.typography.labelLarge)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = currentValue, style = MaterialTheme.typography.displayLarge)
        }
    }
}

//@Preview
//@Composable
//fun CalcButtonPrev() {
//    Surface(
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(horizontal = 24.dp)
//        ) {
//            DisplayArea(
//                currentValue = "673",
//                calcHistory = "100 + 49 + 7 + 451",
//                modifier = Modifier.weight(0.8f)
//            )
//            CalcBoard(modifier = Modifier.weight(1f), onClick = {} )
//        }
//    }
//}

object ButtonValues {
    const val one = '1'
    const val two = '2'
    const val three = '3'
    const val four = '4'
    const val five = '5'
    const val six = '6'
    const val seven = '7'
    const val eight = '8'
    const val nine = '9'
    const val zero = '0'
    const val dot = '.'
    const val space = ' '
    const val equals = '='
    const val plus = '+'
    const val minus = '-'
    const val times = '*'
    const val divide = '/'
    const val percent = '%'
    const val clear = "AC"
    const val delete = "del"
}

val rowOne =
    listOf(ButtonValues.clear, ButtonValues.delete, ButtonValues.percent, ButtonValues.divide)
val rowTwo = listOf(ButtonValues.seven, ButtonValues.eight, ButtonValues.nine, ButtonValues.times)
val rowThree = listOf(ButtonValues.four, ButtonValues.five, ButtonValues.six, ButtonValues.minus)
val rowFour = listOf(ButtonValues.one, ButtonValues.two, ButtonValues.three, ButtonValues.plus)
val rowFive = listOf(ButtonValues.space, ButtonValues.zero, ButtonValues.dot, ButtonValues.equals)