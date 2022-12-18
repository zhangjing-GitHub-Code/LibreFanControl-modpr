import configuration.Configuration
import external.ExternalManager
import kotlinx.coroutines.*
import logicControl.Logic


class Application {

    private var jobUpdate: Job? = null
    private var jobControlUpdate: Job? = null

    private var configuration: Configuration? = null



    companion object {
        private val externalManager = ExternalManager()
        fun setControl(libIndex: Int, isAuto: Boolean, value: Int? = null) {
            println("try to set control: index: $libIndex, isAuto: $isAuto, value: $value")
            /*
            externalManager.setControl(
                libIndex = libIndex,
                isAuto = isAuto,
                value = value
            )
            */
        }
    }

    fun onStart() {
        // load library
        externalManager.start(
            State._fanList,
            State._tempList,
            State._controlList
        )
        configuration = Configuration()

        val res = configuration!!.checkConfiguration()

        when (res) {
            -1 -> {
                configuration!!.init()
            }

            else -> {

            }
        }
        startUpdate()
        startControlUpdate()
    }


    private var updateShouldStop = false
    private var updateControlShouldStop = false
    fun onStop() {
        runBlocking {
            updateShouldStop = true
            updateControlShouldStop = true
            jobUpdate?.cancel()
            jobControlUpdate?.cancel()
            jobUpdate = null
            jobControlUpdate = null
            externalManager.stop()
        }
    }


    private fun startUpdate() {
        jobUpdate = CoroutineScope(Dispatchers.Default).launch {
            while (!updateShouldStop) {
                externalManager.updateFan(State._fanList)
                externalManager.updateTemp(State._tempList)
                externalManager.updateControl(State._controlList)

                delay(2000L)
            }
        }
    }

    private val logic = Logic()

    private fun startControlUpdate() {
        jobControlUpdate = CoroutineScope(Dispatchers.Default).launch {
            while (!updateControlShouldStop) {
                logic.update()

                delay(2000L)
            }
        }
    }
}