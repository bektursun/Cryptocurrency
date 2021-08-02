package com.bektursun.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

class DebouncingTextWatcher(
    lifecycle: Lifecycle,
    private val lifecycleScope: LifecycleCoroutineScope,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : TextWatcher, LifecycleObserver {

    /**
     * default debounce time is 1 second
     * */
    var debouncePeriod: Long = DEBOUNCE_TIME
        private set

    private var searchJob: Job? = null

    init {
        lifecycle.addObserver(this)
    }

    fun setDebounceTime(millis: Long) {
        debouncePeriod = millis
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        searchJob?.cancel()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(text: Editable?) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            text?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(text.toString())
            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIME: Long = 1000
    }
}