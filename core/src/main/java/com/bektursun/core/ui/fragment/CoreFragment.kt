package com.bektursun.core.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.bektursun.core.viewmodel.CoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class CoreFragment<VB : ViewBinding, VM : CoreViewModel>(vmClass: KClass<VM>) :
    SimpleFragment<VB>() {

    protected val vm: VM by viewModel(clazz = vmClass)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
    }

    open fun subscribeToLiveData() {}

}