package com.example.findissues.ui.home

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.findissues.R

@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home),
                fontWeight = FontWeight.Medium,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        backgroundColor = Color(0xFF0d1117),
        
    )
}

@Preview( showBackground = true)
@Composable
fun HomeAppBarPreview() {
    HomeAppBar()
}