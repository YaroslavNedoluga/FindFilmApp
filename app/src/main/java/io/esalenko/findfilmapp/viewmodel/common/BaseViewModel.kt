package io.esalenko.findfilmapp.viewmodel.common

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.esalenko.findfilmapp.App
import io.esalenko.findfilmapp.service.RestManager


abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    protected val restManager: RestManager

    init {
        val localApp: App = app as App
        restManager = localApp.restManager!!
    }

}