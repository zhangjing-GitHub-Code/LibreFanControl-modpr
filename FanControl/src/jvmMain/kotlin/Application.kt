import configuration.Configuration
import external.ExternalManager
import external.getOS
import kotlinx.coroutines.*


class Application {

    private var jobUpdate: Job? = null

    private var configuration: Configuration? = null

    companion object {
        private val externalManager = ExternalManager(getOS())

        fun setValue(index: Int, isAuto: Boolean, value: Int) {
            externalManager.setControl(index, isAuto, value)
        }

    }

    fun onStart() {
        //check si conf exist

        // get conf

        //load viewModelFactory

        //load library

        // applique conf if conf

        // start update


        externalManager.start(
            State._fanList,
            State._tempList,
            State._controlList
        )

        configuration = Configuration()

        configuration!!.run()

        startUpdate()
    }


    fun onStop() {
        runBlocking {
            updateShouldStop = true
            jobUpdate?.cancel()
            jobUpdate = null
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

    private var updateShouldStop = false
}