package ui.screen.itemsList.sensor.addItem

import androidx.compose.foundation.lazy.LazyListScope
import model.ItemType
import ui.screen.itemsList.sensor.baseSensorAddItem
import ui.utils.Resources

val viewModel = AddSensorVM()


fun LazyListScope.sensorAddItemList() {

    // fan
    item {
        baseSensorAddItem(
            iconPainter = Resources.getIcon("toys_fan"),
            iconContentDescription = Resources.getString("ct/fan"),
            name = Resources.getString("add_item/fan_name"),
            onEditClick = {
                viewModel.addFan()
            },
            sensorName = Resources.getString("add_item/choose_sensor"),
            sensorValue = "1000 ${Resources.getString("unity/rpm")}",
            type = ItemType.SensorType.I_S_FAN
        )
    }

    // temp
    item {
        baseSensorAddItem(
            iconPainter = Resources.getIcon("thermometer"),
            iconContentDescription = Resources.getString("ct/temp"),
            name = Resources.getString("add_item/temp_name"),
            onEditClick = {
                viewModel.addTemp()
            },
            sensorName = Resources.getString("add_item/choose_sensor"),
            sensorValue = "50 ${Resources.getString("unity/degree")}",
            type = ItemType.SensorType.I_S_TEMP
        )
    }

}