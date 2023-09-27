package com.plcoding.daggerhiltcourse.uicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plcoding.daggerhiltcourse.ui.theme.Typography
import com.plcoding.daggerhiltcourse.data.datamodel.Meme


@Composable
fun CustomItem2(item: Meme) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item.id?.let {
            Text(
                text = it,
                color = Color.Black,
                fontSize = Typography.h5.fontSize,
                fontWeight = FontWeight.Normal
            )
        }
        item.name?.let {
            Text(
                text = it,
                color = Color.Black,
                fontSize = Typography.h5.fontSize,
                fontWeight = FontWeight.Normal
            )
        }
    }
}