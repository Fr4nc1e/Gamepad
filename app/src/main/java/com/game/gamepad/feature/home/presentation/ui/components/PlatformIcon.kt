package com.game.gamepad.feature.home.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.game.gamepad.R

@Composable
fun PlatformIcon(
    platforms: List<String?>?
) {
    platforms?.forEach {
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
            "PlayStation" -> Icon(
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
            "Xbox" -> Icon(
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
