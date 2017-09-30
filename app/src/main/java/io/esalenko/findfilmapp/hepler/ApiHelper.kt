package io.esalenko.findfilmapp.hepler

import android.content.Context
import android.os.Build
import io.esalenko.findfilmapp.R
import javax.inject.Inject


class ApiHelper @Inject constructor(val context: Context) {

    val region = "ISO 3166-1"
    fun getApiKey() = context.getString(R.string.api_key)

    fun getLocale() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        context.resources.configuration.locale.toString()
    else
        context.resources.configuration.locales.get(0).toString()


}