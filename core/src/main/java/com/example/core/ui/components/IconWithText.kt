package com.example.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    text: String,
    iconId: Int,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color(0xFFb7b8ba)
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = fontWeight,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp),
        )
    }
}