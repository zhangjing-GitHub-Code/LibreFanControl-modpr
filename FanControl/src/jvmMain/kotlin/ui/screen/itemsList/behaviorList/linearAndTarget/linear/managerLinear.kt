package ui.screen.itemsList.behaviorList.linearAndTarget.linear

import State
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import model.ItemType
import model.item.behavior.Behavior
import model.item.behavior.Linear
import ui.component.managerExpandItem
import ui.component.managerListChoice
import ui.component.managerNumberTextField
import ui.screen.itemsList.baseItemAddItem
import ui.screen.itemsList.baseItemBody
import ui.screen.itemsList.behaviorList.linearAndTarget.linAndTarSuffixes
import ui.screen.itemsList.behaviorList.linearAndTarget.managerNumberChoice
import ui.theme.LocalColors
import ui.theme.LocalSpaces
import ui.utils.Resources

private val viewModel: LinearVM = LinearVM()


@Composable
fun linearBody(
    behavior: Behavior,
    index: Int
) {
    baseItemBody(
        icon = Resources.getIcon("items/linear24"),
        onNameChange = { viewModel.setName(it, index) },
        onEditClick = { viewModel.remove(index) },
        item = behavior
    ) {

        val linear = behavior.extension as Linear

        val customTempList = viewModel.iTemps.filter { it.type == ItemType.SensorType.I_S_CUSTOM_TEMP }

        managerListChoice(
            text = with(linear.tempSensorId) {
                when {
                    this == null -> null
                    this > 0 -> viewModel.hTemps.first {
                        it.id == this
                    }.name

                    this < 0 -> customTempList.first {
                        it.id == this
                    }.name

                    else -> throw IllegalArgumentException()
                }
            },
            onItemClick = {
                viewModel.setTemp(
                    index = index,
                    tempSensorId = it
                )
            },
            ids = viewModel.hTemps.map { it.id } + customTempList.map { it.id },
            names = viewModel.hTemps.map { it.name } + customTempList.map { it.name }
        )

        val expanded = remember(
            behavior.id,
            State.settings.collectAsState().value.configId
        ) {
            mutableStateOf(false)
        }

        Spacer(Modifier.height(LocalSpaces.current.medium))

        managerExpandItem(
            value = linear.value,
            expanded = expanded
        ) {
            Spacer(Modifier.height(LocalSpaces.current.small))

            val linearValues = linearValues(linear)

            for (i in 0..3) {

                val text: MutableState<String> = remember(
                    behavior.id,
                    State.settings.collectAsState().value.configId
                ) {
                    mutableStateOf(linearValues[i].toString())
                }

                managerNumberChoice(
                    text = {
                        managerNumberTextField(
                            text = text,
                            opposedValue = when (i) {
                                0 -> linearValues[1]
                                1 -> linearValues[0]
                                2 -> linearValues[3]
                                3 -> linearValues[2]
                                else -> throw Exception("impossible index")
                            },
                            onValueChange = {
                                viewModel.onChange(
                                    index = index,
                                    value = it,
                                    type = linearTypes[i]
                                )
                            },
                            type = linearTypes[i]
                        )
                    },
                    prefix = linearPrefixes[i],
                    suffix = linAndTarSuffixes[i],
                    increase = {
                        text.value = viewModel.increase(
                            index = index,
                            type = linearTypes[i]
                        )
                    },
                    decrease = {
                        text.value = viewModel.decrease(
                            index = index,
                            type = linearTypes[i]
                        )
                    }
                )
            }
        }
    }
}


@Composable
fun linearAddItem() {
    baseItemAddItem(
        icon = Resources.getIcon("items/linear24"),
        name = Resources.getString("add_item/name/linear"),
        onEditClick = { viewModel.addBehavior(viewModel.defaultLinear()) },
        text = Resources.getString("add_item/info/linear")
    )
}