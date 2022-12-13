package ui.screen.body

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.screen.body.behaviorList.behaviorList
import ui.screen.body.controlList.controlList
import ui.screen.body.fanList.fanList
import ui.screen.body.tempList.tempList
import ui.utils.Resources

@Composable
fun body(
    editModeActivated: MutableState<Boolean>,
    addItemExpanded: MutableState<Boolean>
) {

    val viewModel = BodyViewModel()

    // body + addItem button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        scrollableBox {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                itemsList(
                    title = Resources.getString("title/control")
                ) {
                    controlList(editModeActivated.value)
                }
                itemsList(
                    title = Resources.getString("title/behavior")
                ) {
                    behaviorList(editModeActivated.value)
                }
                itemsList(
                    title = Resources.getString("title/fan")
                ) {
                    fanList(editModeActivated.value)
                }
                itemsList(
                    title = Resources.getString("title/temp")
                ) {
                    tempList(editModeActivated.value)
                }
            }
        }

        // add button
        if (!addItemExpanded.value) {

            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = {
                    viewModel.expandAddItem()
                }
            ) {
                Icon(
                    painter = Resources.getIcon("add"),
                    contentDescription = Resources.getString("ct/open_add_item"),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun itemsList(
    title: String,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn {
        item {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 40.sp
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
        }
        content()
    }
}


@Composable
fun BoxScope.scrollableBox(
    content: @Composable () -> Unit
) {
    val stateHorizontal = rememberScrollState(0)

    val scrollBarShouldShow =
        stateHorizontal.value != stateHorizontal.maxValue || stateHorizontal.value != 0

    val modifier = if (scrollBarShouldShow)
        Modifier.padding(bottom = 20.dp)
    else
        Modifier

    Box(
        modifier = modifier
            .horizontalScroll(stateHorizontal)

    ) {
        content()
    }

    if (scrollBarShouldShow) {
        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomStart)
                .height(20.dp)
                .background(Color.Green),
            adapter = rememberScrollbarAdapter(stateHorizontal)
        )
    }
}