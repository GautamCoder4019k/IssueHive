package com.example.core.ui.components

import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppTopBar(title: Int) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                fontWeight = FontWeight.Medium,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        backgroundColor = Color(0xFF0d1117),
    )
}