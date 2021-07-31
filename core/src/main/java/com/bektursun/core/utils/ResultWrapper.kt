package com.bektursun.core.utils

import java.io.IOException

/**
 * Result management for UI and data.
 */
sealed class ResultWrapper<out T> {

    data class Success<T>(val data: T) : ResultWrapper<T>()

    data class Error(val exception: Throwable) : ResultWrapper<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()

    companion object {

        /**
         * Return [Success] with [data] to emit.
         */
        fun <T> success(data: T) = Success(data)

        /**
         * Return [Error] with [exception] to emit.
         */
        fun error(exception: Throwable) = Error(exception)

        /**
         * Return [Empty].
         */
        fun empty() = Empty

        /**
         * Return [Loading].
         */
        fun loading() = Loading

        /**
         * Return [Empty] if [list] is empty, otherwise return [Success].
         */
        fun <T> successOrEmpty(list: List<T>): ResultWrapper<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}