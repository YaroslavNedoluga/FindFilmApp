package io.esalenko.findfilmapp

import android.os.Build
import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*


class App : MultiDexApplication(), AnkoLogger {

    private lateinit var locale: Locale

    override fun onCreate() {
        super.onCreate()
        // Leak Canary initialization
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
    }

    fun getCurrentLocale(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            locale = resources.configuration.locale
            info { locale.toString() }
        } else {
            locale = resources.configuration.locales.get(0)
            info { locale.toString() }
        }

        return locale.toString()
    }

}