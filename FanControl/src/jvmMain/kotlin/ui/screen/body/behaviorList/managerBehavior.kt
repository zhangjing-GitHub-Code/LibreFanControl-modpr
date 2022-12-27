package ui.screen.body.behaviorList

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import model.ItemType
import ui.screen.body.behaviorList.flat.flatBehavior
import ui.screen.body.behaviorList.linear.linearBehavior


private val viewModel: BehaviorViewModel = BehaviorViewModel()

fun LazyListScope.behaviorList(
    editModeActivated: Boolean
) {
    itemsIndexed(viewModel.behaviorItemList.value) { index, behavior ->

        when (behavior.type) {
            ItemType.BehaviorType.B_FLAT -> {
                flatBehavior(
                    behavior = behavior,
                    index = index,
                    editModeActivated = editModeActivated,
                    onNameChange = {
                        viewModel.setName(it, index)
                    },
                    onEditClick = {
                        viewModel.remove(index)
                    },
                )
            }

            ItemType.BehaviorType.B_LINEAR -> {
                linearBehavior(
                    behavior = behavior,
                    index = index,
                    editModeActivated = editModeActivated,
                    onNameChange = {
                        viewModel.setName(it, index)
                    },
                    onEditClick = {
                        viewModel.remove(index)
                    },
                )
            }

            ItemType.BehaviorType.B_TARGET -> TODO()

            else -> throw Exception("unspecified item type")
        }

    }
}