package ui.screen.topBar.configuration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import ui.component.managerNameOutlinedTextField
import ui.component.managerText
import ui.utils.Resources
import utils.checkNameTaken
import utils.getAvailableId

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun managerDialogAddConfiguration(
    enabled: MutableState<Boolean>,
    keyEnterPressed: MutableState<Boolean>
) {

    val configList = viewModel.configList.value

    val id = getAvailableId(
        ids = configList.map {
            it.id
        }
    )
    val text = remember(
        id
    ) {
        mutableStateOf("")
    }

    Dialog(
        visible = enabled.value,
        onCloseRequest = {
            enabled.value = false
        },
        title = Resources.getString("title/add_config"),
        focusable = enabled.value,
        onKeyEvent = {
            when (it.key) {
                Key.Escape -> {
                    enabled.value = false
                    return@Dialog true
                }

                Key.Enter -> {
                    if (viewModel.addConfiguration(
                            name = text.value,
                            id = id
                        )
                    ) {
                        keyEnterPressed.value = true
                        enabled.value = false
                    }
                    return@Dialog true
                }

                else -> {
                    return@Dialog false
                }
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface
                ),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val focusRequester = remember { FocusRequester() }

            managerNameOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .focusRequester(focusRequester),
                onValueChange = {
                    checkNameTaken(
                        names = configList.map { config ->
                            config.name
                        },
                        name = it
                    )
                },
                label = Resources.getString("label/conf_name"),
                id = id,
                text = text
            )
            LaunchedEffect(
                Unit
            ) {
                if (enabled.value) {
                    delay(400L)
                    focusRequester.requestFocus()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(
                    onClick = {
                        enabled.value = false
                    }
                ) {
                    managerText(
                        text = Resources.getString("cancel")
                    )
                }

                Button(
                    onClick = {
                        if (viewModel.addConfiguration(
                                name = text.value,
                                id = id
                            )
                        ) {
                            enabled.value = false
                        }
                    }
                ) {
                    managerText(
                        text = Resources.getString("add")
                    )
                }
            }
        }
    }
}