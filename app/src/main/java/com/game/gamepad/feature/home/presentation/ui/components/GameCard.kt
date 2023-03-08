package com.game.gamepad.feature.home.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.game.gamepad.R
import com.game.gamepad.core.util.ImageLoader
import com.game.gamepad.feature.home.domain.models.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    game: Game,
    modifier: Modifier,
    onCardClick: () -> Unit
) {
    val isExpanded = remember { mutableStateOf(false) }

    Card(
        onClick = { onCardClick() },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageLoader(
                modifier = Modifier.aspectRatio(1920f / 1080f),
                url = game.backgroundImage
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        PlatformIcon(platforms = game.platforms)
                    }

                    game.metacritic?.let {
                        val color = when {
                            it >= 80 -> Color.Green
                            it in 60..79 -> Color.Yellow
                            it in 0..59 -> Color.Red
                            else -> Color.Black
                        }
                        Box(
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = color,
                                shape = RoundedCornerShape(6.dp)
                            ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.toString(),
                                modifier = Modifier
                                    .padding(PaddingValues(horizontal = 3.dp, vertical = 2.dp)),
                                style = MaterialTheme.typography.bodySmall.copy(color)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    game.name?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    IconButton(onClick = {
                        isExpanded.value = !isExpanded.value
                    }) {
                        Icon(
                            imageVector = when (isExpanded.value) {
                                true -> Icons.Filled.KeyboardArrowUp
                                false -> Icons.Filled.KeyboardArrowDown
                            },
                            contentDescription = null
                        )
                    }
                }

                when (isExpanded.value) {
                    true -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 5.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            game.released?.let {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.release_date),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            game.esrbRating?.let {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.category),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            game.rating?.let {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.rating),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = it.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            game.ratingsCount?.let {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.rating_count),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = it.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            game.ratingTop?.let {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.rating_top),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = it.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                    false -> Unit
                }
            }
        }
    }
}
