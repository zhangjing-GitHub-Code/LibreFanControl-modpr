package model.item

import model.ItemType
import ui.utils.Resources

data class SensorItem(
    override var name: String,
    override val type: ItemType.SensorType,

    override var isExpanded: Boolean = false,
    var sensorName: String = Resources.getString("none"),
    var sensorId: String? = null,
    override val itemId: Long
) : BaseItem