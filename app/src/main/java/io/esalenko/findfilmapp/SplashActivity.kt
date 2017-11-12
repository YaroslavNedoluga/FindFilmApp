package io.esalenko.findfilmapp

import android.os.Bundle
import android.os.Handler
import io.esalenko.findfilmapp.common.BaseActivity
import org.jetbrains.anko.intentFor

class SplashActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(
                {
                    startMainActivity()
                }
                , 3000)
    }

    private fun startMainActivity() {
        startActivity(intentFor<MainActivity>())
        finish()
    }
}