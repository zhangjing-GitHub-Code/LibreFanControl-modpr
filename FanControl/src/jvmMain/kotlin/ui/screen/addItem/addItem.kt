package ui.screen.addItem

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.component.managerText
import ui.screen.itemsList.behaviorList.behaviorAddItemList
import ui.screen.itemsList.controlList.controlAddItem
import ui.screen.itemsList.controlList.controlList
import ui.screen.itemsList.sensor.addItem.sensorAddItemList
import ui.utils.Resources


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun addItem() {
    val state = remember { mutableStateOf(ChoiceState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseSurface)
    ) {
        addItemChoice(state)

        Divider(
            modifier = Modifier.padding(bottom = 10.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )



        AnimatedContent(
            targetState = state.value,
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = {
                        it * targetState.animationSign
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = {
                        it * targetState.animationSign * -1
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (it.current) {
                    ChoiceType.CONTROL -> controlList({ !it.visible }) { index, control ->
                        controlAddItem(
                            control = control,
                            index = index
                        )
                    }

                    ChoiceType.BEHAVIOR -> behaviorAddItemList()
                    ChoiceType.SENSOR -> sensorAddItemList()
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun addItemChoice(state: MutableState<ChoiceState>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                state.value = updateChoiceState(
                    state.value.copy(
                        animationSign = -1
                    )
                )
            }
        ) {
            Icon(
                painter = Resources.getIcon("arrow/notch/line_start_arrow_notch40"),
                contentDescription = Resources.getString("ct/previous"),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        AnimatedContent(
            targetState = state.value,
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = {
                        it * targetState.animationSign
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = {
                        it * targetState.animationSign * -1
                    }
                )
            }
        ) {
            managerText(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                text = it.title,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        IconButton(
            onClick = {
                state.value = updateChoiceState(
                    state.value.copy(
                        animationSign = 1
                    )
                )
            }
        ) {
            Icon(
                painter = Resources.getIcon("arrow/notch/line_end_arrow_notch40"),
                contentDescription = Resources.getString("ct/next"),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}