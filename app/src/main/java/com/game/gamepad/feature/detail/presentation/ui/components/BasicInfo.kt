package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.game.gamepad.R
import com.game.gamepad.feature.detail.domain.models.Genre

@Composable
fun BasicInfo(
    name: String?,
    genres: List<Genre>?,
    released: String?,
    rating: Double?
) {
    name?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.titleLarge
        )
    }
    genres?.let { genre ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            genre.forEach { item ->
                item.name?.let { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
    released?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.release_date),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            rating?.let {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.rating),
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
}