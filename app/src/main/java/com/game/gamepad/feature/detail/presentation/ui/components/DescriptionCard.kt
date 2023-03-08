package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat

@Composable
fun DescriptionCard(
    description: String
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }

    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = HtmlCompat.fromHtml(
                    description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ).toString(),
                style = MaterialTheme.typography.bodyMedium,
                overflow = when (isExpanded.value) {
                    true -> TextOverflow.Visible
                    false -> TextOverflow.Ellipsis
                },
                maxLines = if (!isExpanded.value) 3 else Int.MAX_VALUE
            )
            FilledIconToggleButton(
                checked = isExpanded.value,
                onCheckedChange = {
                    isExpanded.value = isExpanded.value.not()
                },
                modifier = Modifier.size(20.dp)
            ) {
                when (isExpanded.value) {
                    true -> {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                    false -> {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}