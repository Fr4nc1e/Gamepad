package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.game.gamepad.R
import com.game.gamepad.feature.detail.domain.models.Platform

@Composable
fun RequirementsCard(
    modifier: Modifier,
    platform: Platform
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PlatformIcon(platform = platform)
            platform.apply {
                name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                if (isExpanded.value) {
                    requirements?.let {
                        it.minimum?.let { requirement ->
                            Text(
                                text = stringResource(R.string.minimum_requirement),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = requirement,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        it.recommended?.let { requirement ->
                            Text(
                                text = stringResource(R.string.recommended_requirement),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = requirement,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
        FilledIconToggleButton(
            checked = isExpanded.value,
            onCheckedChange = {
                isExpanded.value = isExpanded.value.not()
            },
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.End)
                .padding(
                    PaddingValues(
                        end = 10.dp,
                        bottom = 10.dp
                    )
                )
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