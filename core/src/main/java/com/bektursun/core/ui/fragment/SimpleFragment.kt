package com.bektursun.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class SimpleFragment<VB: ViewBinding> : Fragment() {

    private var _vb: VB? = null
    protected val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = createViewBinding(inflater)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    open fun setupViews() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    abstract fun createViewBinding(inflater: LayoutInflater): VB
}