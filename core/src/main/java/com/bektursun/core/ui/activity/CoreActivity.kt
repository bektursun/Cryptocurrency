package com.bektursun.core.ui.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.bektursun.core.viewmodel.CoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class CoreActivity<VB : ViewBinding, VM : CoreViewModel>(vmClass: KClass<VM>) :
    SimpleActivity<VB>() {

    protected val vm: VM by viewModel(clazz = vmClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
    }

    open fun subscribeToLiveData() {}
}