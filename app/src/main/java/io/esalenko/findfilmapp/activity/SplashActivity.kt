package io.esalenko.findfilmapp.activity

import android.os.Bundle
import android.os.Handler
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.activity.common.BaseActivity
import org.jetbrains.anko.startActivity

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
        startActivity<MainActivity>()
        finish()
    }
}