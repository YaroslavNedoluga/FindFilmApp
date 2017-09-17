package io.esalenko.findfilmapp.hepler

import android.content.Context
import android.os.Build
import io.esalenko.findfilmapp.R


class ApiHelper {

    companion object {
        val region = "ISO 3166-1"
        fun getApiKey(context: Context) = context.getString(R.string.api_key)

        fun getLocale(context: Context) = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            context.resources.configuration.locale.toString()
        else
            context.resources.configuration.locales.get(0).toString()

    }

}