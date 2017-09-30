package io.esalenko.findfilmapp.common

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    lateinit var unbinder: Unbinder

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater?.inflate(getLayoutRes(), container, false)!!
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }
}