package ui.screen.drawer.firstView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.IconButton
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import settings.SettingsModel
import ui.component.managerText
import ui.utils.Resources


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settingFistView(
    drawerState: DrawerState,
    scope: CoroutineScope,
    lazyListState: LazyListState,
    settings: SettingsModel
) {


    LazyColumn(
        state = lazyListState
    ) {

        baseTransition(Resources.getString("settings/trans/donate"))


        baseTransition(Resources.getString("settings/trans/other"))


    }
}


@OptIn(ExperimentalMaterial3Api::class)
private fun LazyListScope.topMainSetting(
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            managerText(
                modifier = Modifier
                    .padding(start = 25.dp, top = 40.dp, bottom = 50.dp),
                text = Resources.getString("title/setting"),
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(
                onClick = { scope.launch { drawerState.close() } }
            ) {
                Icon(
                    painter = Resources.getIcon("arrow/arrow_back48"),
                    contentDescription = Resources.getString("ct/close_drawer"),
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
    item {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            thickness = 2.dp
        )
    }
}


private fun LazyListScope.baseTransition(
    text: String
) {
    item {
        managerText(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier.padding(top = 25.dp, bottom = 5.dp)
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            thickness = 2.dp
        )
    }
}


@Composable
private fun baseDonateItemSetting(
    item: DonateSettingItem,
) {

}


