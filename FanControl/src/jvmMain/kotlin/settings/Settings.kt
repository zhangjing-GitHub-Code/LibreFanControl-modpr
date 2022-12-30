package settings

import State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import model.ConfigurationModel
import model.SettingsModel
import org.json.JSONObject
import org.json.JSONTokener
import utils.DIR_CONF
import utils.getJsonValue
import utils.setJsonValue
import java.io.File


const val SETTINGS_FILE_NAME = "settings.json"

class Settings {
    companion object {

        private var rootObj: JSONObject
        private val file: File = File(DIR_CONF + SETTINGS_FILE_NAME)

        init {
            val existed = initSettingsFile()
            val string = file.bufferedReader().readText()
            rootObj = JSONTokener(string).nextValue() as JSONObject

            if(existed)
                initSettingsState(State._settings)
        }

        /*
            initialize the settings.json file using the settings.sot.json file
            which serves as a source of truth, to avoid committing a modified settings.json file.

            This avoids using "smudge", the git tool, because it is really not practical.

            the function return true if the file existed, false otherwise
        */
        private const val SETTINGS_SOT_FILE_NAME = "settings.sot.json"
        private fun initSettingsFile(): Boolean {
            val localSettingFile = File(DIR_CONF + SETTINGS_FILE_NAME)

            return when (localSettingFile.exists()) {
                false -> {
                    val settingsSotFile = File(DIR_CONF + SETTINGS_SOT_FILE_NAME)
                    localSettingFile.writeText(settingsSotFile.readText())
                    false
                }
                true -> true
            }
        }

        private fun initSettingsState (
            settings: MutableStateFlow<SettingsModel>
        ) {
            settings.update {
                it.language.value = getSetting("language")!!
                it.configId.value = getSetting("configId")
                getConfigList(it.configList)
                it.updateDelay.value = getSetting("updateDelay")!!
                it
            }
        }

        private fun getConfigList(configList: SnapshotStateList<ConfigurationModel>) {
            val jsonList = rootObj.getJSONObject("configList")

            for (key in jsonList.keys()) {
                configList.add(
                    ConfigurationModel(
                        id = key.toLong(),
                        name = jsonList.getString(key)
                    )
                )
            }
        }

        fun <T> getSetting(path: String): T? {
            return getJsonValue(
                path = path,
                obj = rootObj
            )
        }


        fun setSetting(path: String, value: Any?) {
            updateVariable(
                setJsonValue(
                    path = path,
                    value = value,
                    obj = rootObj
                )
            )
        }

        fun removeConfig(id: Long) {
            val path = "configList/$id"
            updateVariable(
                setJsonValue(
                    path = path,
                    value = null,
                    obj = rootObj
                )
            )
        }

        private fun updateVariable(newObj: JSONObject) {

            file.writeText(
                newObj.toString()
            )
            rootObj = newObj
        }
    }
}