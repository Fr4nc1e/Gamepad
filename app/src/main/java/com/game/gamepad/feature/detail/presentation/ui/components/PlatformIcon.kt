package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.game.gamepad.R
import com.game.gamepad.feature.detail.domain.models.Platform

@Composable
fun PlatformIcon(
    platform: Platform
) {
    platform.name?.let {
        when (it) {
            "PC" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.windows),
                contentDescription = null
            )
            "Android" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.android),
                contentDescription = null
            )
            "PlayStation", "PlayStation 5", "PlayStation 4", "PlayStation 3" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.playstation),
                contentDescription = null
            )
            "iOS" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = null
            )
            "Nintendo Switch" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.nintendoswitch),
                contentDescription = null
            )
            "Xbox", "Xbox 360", "Xbox One", "Xbox Series S/X" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.xbox),
                contentDescription = null
            )
            "Linux" -> Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.linux),
                contentDescription = null
            )
        }
    }
}