package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.game.gamepad.R

@Composable
fun RedditCard(
    redditName: String?,
    redditUrl: String?,
    redditCount: Int?,
    redditDescription: String?
) {
    val uriHandler = LocalUriHandler.current

    redditName?.let { name ->
        Card {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.reddit),
                        style = MaterialTheme.typography.titleMedium
                    )

                    redditUrl?.let {
                        IconButton(
                            onClick = {
                                uriHandler.openUri(it)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }
                }

                Text(
                    text = "Reddit name: $name",
                    style = MaterialTheme.typography.bodyMedium
                )

                redditCount?.let {
                    Text(
                        text = "Reddit count: $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                redditDescription?.let {
                    Text(
                        text = "Reddit description: $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}