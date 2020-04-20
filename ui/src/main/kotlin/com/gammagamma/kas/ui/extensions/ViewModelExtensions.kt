@file:Suppress("unused")

package com.gammagamma.kas.ui.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline fun <reified T> liveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false
) : LiveData<T> = mutableLiveDataOf(initialValue, defaultValue, post)

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    noinline fn: (() -> Unit)? = null
) = MutableLiveData<T>().apply {
    if (!post) value = initialValue ?: defaultValue
    else postValue(initialValue ?: defaultValue)
    fn?.invoke()
}

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    post: Boolean = false,
    noinline fn: (() -> Unit)? = null
) = mutableLiveDataOf(initialValue, initialValue, post, fn)

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    noinline fn: (() -> Unit)? = null
) = mutableLiveDataOf(initialValue, initialValue, false, fn)

inline fun <reified T> mediatorLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    noinline block: (MediatorLiveData<T>.() -> Unit)? = null
) = MediatorLiveData<T>().apply {
    if (!post) value = initialValue ?: defaultValue
    else postValue(initialValue ?: defaultValue)
    block?.invoke(this)
}

fun <T, S> MediatorLiveData<T>.addSources(vararg sources: LiveData<S>, onChanged: (S) -> Unit) {
    sources.forEach { source ->
        addSource(source) { onChanged(it) }
    }
}
