package com.vladus177.albums.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

object ViewModelUtil {
    fun <T : ViewModel> createFor(model: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }

    fun <T : ViewModel> create(
        modelClass: Class<T>,
        creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}