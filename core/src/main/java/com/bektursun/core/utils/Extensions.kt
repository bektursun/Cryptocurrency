package com.bektursun.core.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

inline fun <reified F : Fragment> AppCompatActivity.addFragmentExt(
    @IdRes containerId: Int,
    backStack: Boolean = false,
    noinline args: Bundle.() -> Bundle = { bundleOf() }
) {
    supportFragmentManager.commit {
        val arguments = Bundle().args()
        if (backStack) add(containerId, F::class.java, arguments).addToBackStack(null)
        else add(containerId, F::class.java, arguments)
    }
}