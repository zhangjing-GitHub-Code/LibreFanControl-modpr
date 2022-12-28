package configuration

import State
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import model.ItemType
import model.hardware.Sensor
import model.item.ControlItem
import model.item.SensorItem
import model.item.behavior.BehaviorItem
import settings.Settings
import utils.getAvailableId

class Configuration(
    private val _fanList: MutableStateFlow<SnapshotStateList<Sensor>> = State._fanList,
    private val _tempList: MutableStateFlow<SnapshotStateList<Sensor>> = State._tempList,


    private val _controlItemList: MutableStateFlow<SnapshotStateList<ControlItem>> = State._controlItemList,
    private val _behaviorItemList: MutableStateFlow<SnapshotStateList<BehaviorItem>> = State._behaviorItemList,
    private val _fanItemList: MutableStateFlow<SnapshotStateList<SensorItem>> = State._fanItemList,
    private val _tempItemList: MutableStateFlow<SnapshotStateList<SensorItem>> = State._tempItemList
) {
    // returns configId if it exists, otherwise null
    fun checkConfiguration(): Long? {

        return with(Settings.getSetting("config")) {
            try {
                this as Long
            } catch (e: ClassCastException) {
                null
            }
        }
    }

    fun loadConfig(configId: Long) {

    }

    fun init() {
        _fanList.value.forEach {
            _fanItemList.value.add(
                SensorItem(
                    name = it.libName,
                    type = ItemType.SensorType.S_FAN,
                    sensorName = it.libName,
                    sensorId = it.libId,
                    itemId = getAvailableId(
                        ids = _fanItemList.value.map { item ->
                            item.itemId
                        }
                    )
                )
            )
        }

        _tempList.value.forEach {
            _tempItemList.value.add(
                SensorItem(
                    name = it.libName,
                    type = ItemType.SensorType.S_TEMP,
                    sensorName = it.libName,
                    sensorId = it.libId,
                    itemId = getAvailableId(
                        ids = _tempItemList.value.map { item ->
                            item.itemId
                        }
                    )
                )
            )
        }
    }
}