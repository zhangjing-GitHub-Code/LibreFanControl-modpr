package model.hardware

import model.BaseItem

interface BaseHardware : BaseItem {
    var maxValue: Int
    var minValue: Int

    var libName: String
}