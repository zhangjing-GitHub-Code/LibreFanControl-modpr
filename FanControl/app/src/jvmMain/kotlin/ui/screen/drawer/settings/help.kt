package ui.screen.drawer.settings

import androidx.compose.material3.Icon
import com.example.settingSlidingWindows.SettingScope
import ui.theme.LocalColors
import ui.utils.Resources


fun SettingScope.help() {
    item(
        title = Resources.getString("settings/help"),
        subTitle = Resources.getString("settings/help_sub_title"),
        icon = {
            Icon(
                painter = Resources.getIcon("settings/help24"),
                tint = LocalColors.current.onSecondContainer,
                contentDescription = null
            )
        }
    ) {
        Header(null, null)
    }
}