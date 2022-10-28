package com.pablichj.study.compose.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pablichj.study.compose.router.Node

@Composable
fun AccountPage(
    modifier: Modifier = Modifier,
    accountState: IAccountState,
    onResult: () -> Unit
) {

    val accountName = remember { mutableStateOf(accountState.accountName) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(40.dp))
        Button(
            onClick = onResult
        ) {
            Text(text = "Home State = ${accountName.value}")
        }
        Spacer(Modifier.size(80.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Yellow)
            ) {}
            Row(
                modifier = Modifier
                    .offset(x = 40.dp, y = 40.dp)
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Blue)
            ) {}
            Row(
                modifier = Modifier
                    .offset(x = 80.dp, y = 80.dp)
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Black)
            ) {}
            Column(
                modifier = Modifier
                    .offset(x = 120.dp, y = 120.dp)
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Cyan)
            ) {}
            Column(
                modifier = Modifier
                    .offset(x = 160.dp, y = 160.dp)
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Red)
            ) {}
            Box(
                modifier = Modifier
                    .offset(x = 200.dp, y = 200.dp)
                    .size(width = 80.dp, height = 80.dp)
                    .background(Color.Black)
            ) {}
            Box(
                modifier = Modifier
                    .offset(x = 240.dp, y = 240.dp)
                    .size(width = 80.dp, height = 80.dp)
            ) {
                Text("Add")
            }
            Text(
                modifier = Modifier
                    .offset(x = 280.dp, y = 280.dp)
                    .size(width = 80.dp, height = 80.dp),
                text = "Pablo test",
            )
        }
    }
}