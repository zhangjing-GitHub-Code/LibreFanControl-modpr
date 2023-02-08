package model.item

import State.hTemps
import androidx.compose.runtime.MutableState
import model.ItemType

class NameException : Exception()

interface BaseI {
    val name: MutableState<String>
    val type: ItemType
    val id: String

    companion object {
        /**
         * use for generate id
         */
        const val IControlPrefix = "iControl"
        const val IFlatPrefix = "iFlat"
        const val ILinearPrefix = "iLinear"
        const val ITargetPrefix = "iTarget"
        const val ITempPrefix = "iTemp"
        const val ICustomTempPrefix = "iCustomTemp"
        const val IFanPrefix = "iFan"


        fun getPrefix(id: String?): String? {
            if (id == null)
                return null

            return id.split("#").let {
                if (it.isEmpty()) "" else it[0]
            }
        }

        fun getAvailableId(list: List<String>, prefix: String): String =
            getAvailableString(list, prefix, "#")

        fun getAvailableName(list: List<String>, prefix: String): String =
            getAvailableString(list, prefix, " #")


        private fun getAvailableString(list: List<String>, prefix: String, separator: String): String {
            var nb = 1
            var str = "$prefix$separator$nb"
            while (list.contains(str)) {
                nb++
                str = "$prefix$separator$nb"
            }
            return str
        }


        fun checkNameTaken(names: List<String>, name: String, index: Int? = null) {
            if (name.isBlank())
                throw NameException()

            when (names.count { it == name }) {
                0 -> return
                1 -> {
                    if (index == null)
                        throw NameException()

                    if (names[index] == name)
                        return

                    throw NameException()
                }

                else -> throw NameException()
            }
        }

    }

}


/**
 * centralize the constraint of having custom sensor inside
 * iTemps and hardware sensors inside hTemps
 */
class TempHelper {
    companion object {

        fun getNameIorH(
            hTempId: String?,
            customTempList: List<ICustomTemp>,
        ): String? = with(hTempId) {
            when (BaseI.getPrefix(this)) {
                null -> null

                BaseI.ICustomTempPrefix -> customTempList.first {
                    it.id == this
                }.name.value

                else -> hTemps.first {
                    it.id == this
                }.name
            }
        }


        fun getValueIorH(
        hTempId: String?,
        customTempList: List<ICustomTemp>,
        ): Int? = with(hTempId) {
            when (BaseI.getPrefix(this)) {
                null -> null
                BaseI.ICustomTempPrefix -> customTempList.first {
                    it.id == this
                }.value.value

                else -> hTemps.first {
                    it.id == this
                }.value.value
            }
        }
    }
}

