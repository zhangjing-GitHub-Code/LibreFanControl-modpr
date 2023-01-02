package ui.screen.body.behaviorList

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import model.ItemType
import ui.screen.body.behaviorList.flat.flatBehavior
import ui.screen.body.behaviorList.linear.linearBehavior


private val viewModel: BehaviorViewModel = BehaviorViewModel()

fun LazyListScope.behaviorList() {
    itemsIndexed(viewModel.behaviorItemList) { index, behavior ->

        when (behavior.type) {
            ItemType.BehaviorType.I_B_FLAT -> {
                flatBehavior(
                    behavior = behavior,
                    index = index,
                    onNameChange = {
                        viewModel.setName(it, index)
                    },
                    onEditClick = {
                        viewModel.remove(index)
                    },
                )
            }

            ItemType.BehaviorType.I_B_LINEAR -> {
                linearBehavior(
                    behavior = behavior,
                    index = index,
                    onNameChange = {
                        viewModel.setName(it, index)
                    },
                    onEditClick = {
                        viewModel.remove(index)
                    },
                )
            }

            ItemType.BehaviorType.I_B_TARGET -> TODO()

            else -> throw Exception("unspecified item type")
        }

    }
}