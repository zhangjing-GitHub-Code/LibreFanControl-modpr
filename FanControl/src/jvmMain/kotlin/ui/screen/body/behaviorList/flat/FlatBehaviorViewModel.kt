package ui.screen.body.behaviorList.flat

import State
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.item.behavior.BehaviorItem
import model.item.behavior.FlatBehavior

class FlatBehaviorViewModel(
    private val behaviorItemList: SnapshotStateList<BehaviorItem> = State.behaviorItemList
) {
    fun onMore(index: Int, value: Int) {
        if (value >= 100) return

        behaviorItemList[index] = behaviorItemList[index].copy(
            extension = (behaviorItemList[index].extension as FlatBehavior).copy(
                value = value + 1
            )
        )
    }

    fun onLess(index: Int, value: Int) {
        if (value <= 0) return

        behaviorItemList[index] = behaviorItemList[index].copy(
            extension = (behaviorItemList[index].extension as FlatBehavior).copy(
                value = value - 1
            )
        )
    }

    fun onChange(index: Int, value: Int) {
        behaviorItemList[index] = behaviorItemList[index].copy(
            extension = (behaviorItemList[index].extension as FlatBehavior).copy(
                value = value
            )
        )
    }
}