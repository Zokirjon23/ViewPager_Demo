package com.example.viewpagerdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val adapterFlow = MutableSharedFlow<PagerAdapter>()

    fun createAdapter(adapter: PagerAdapter){
       viewModelScope.launch {
           adapterFlow.emit(adapter)
       }
    }
}
