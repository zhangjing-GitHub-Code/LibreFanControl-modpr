package ui.screen.drawer.settings

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.example.settingSlidingWindows.SettingScope
import ui.utils.Resources


fun SettingScope.info() {
    item(
        title = Resources.getString("settings/info"),
        subTitle = Resources.getString("settings/info_sub_title"),
        icon = {
            Icon(
                painter = Resources.getIcon("settings/info40"),
                tint = MaterialTheme.colorScheme.inverseOnSurface,
                contentDescription = null
            )
        },
        showTopLine = true
    ) {
        Header(null, null)
    }
}