package com.example.searchcinema.ui.presintation.shared.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.searchcinema.R
import com.example.searchcinema.ui.presintation.theme.Colors
import com.example.searchcinema.ui.presintation.theme.SCTypography

@Composable
fun ErrorScreen(
    onRefresh: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        IconAndText(
            text = R.string.error_loading_data,
            icon = R.drawable.error_loading_data,
            style = SCTypography.titleLarge,
            spacer = 22.dp,
            width = 128.dp,
            height = 113.dp,
            content = {
                Spacer(modifier = Modifier.height(51.dp))
                Box(
                    modifier = Modifier
                        .clickable(onClick = onRefresh)
                        .background(color = Colors.jewel, shape = RoundedCornerShape(100.dp))
                ) {
                    Text(
                        text = stringResource(R.string.refresh),
                        color = Color.White,
                        style = SCTypography.bodySmall,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 36.5.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun IconAndText(
    content: @Composable () -> Unit = {},
    spacer: Dp,
    width: Dp,
    height: Dp,
    text: Int,
    icon: Int,
    style: TextStyle
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier.size(width, height)
        )
        Spacer(modifier = Modifier.height(spacer))
        Text(
            text = stringResource(text),
            color = Color.White,
            style = style
        )
        content()
    }
}