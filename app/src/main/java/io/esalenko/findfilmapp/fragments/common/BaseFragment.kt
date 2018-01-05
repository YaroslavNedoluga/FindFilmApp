package io.esalenko.findfilmapp.fragments.common

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import es.dmoral.toasty.Toasty
import io.esalenko.findfilmapp.App

abstract class BaseFragment : Fragment() {

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(layoutRes, container, false)!!
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    protected val app: App
        get() = activity?.application as App

    protected fun Fragment.toastError(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toasty.error(context!!, message, duration, true).show()
    }

    protected fun Fragment.toastSuccess(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toasty.success(context!!, message, duration, true).show()
    }

    protected fun Fragment.toastWarning(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toasty.warning(context!!, message, duration, true).show()
    }
}