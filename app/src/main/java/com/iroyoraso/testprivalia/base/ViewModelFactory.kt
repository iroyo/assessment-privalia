package com.iroyoraso.testprivalia.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by iroyo on 13/02/2019. iroyoraso@gmail.com
 */
class ViewModelFactory<VM : ViewModel>(private val creator: () -> VM) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }

}