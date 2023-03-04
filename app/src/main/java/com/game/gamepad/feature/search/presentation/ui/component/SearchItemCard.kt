package com.game.gamepad.feature.search.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.game.gamepad.core.util.ImageLoader
import com.game.gamepad.feature.search.domain.models.SearchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItemCard(
    modifier: Modifier,
    item: SearchItem,
    onItemCardClick: () -> Unit
) {
    Card(
        onClick = { onItemCardClick() },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card {
                ImageLoader(
                    modifier = Modifier.width(80.dp).height(80.dp),
                    url = item.backgroundImage
                )
            }
            item.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
