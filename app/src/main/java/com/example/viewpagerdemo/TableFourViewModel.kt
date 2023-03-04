package com.example.viewpagerdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TableFourViewModel : ViewModel() {

    val dataFlow = MutableSharedFlow<Event>()

    fun setData(event: Event){
        viewModelScope.launch{
            dataFlow.emit(event)
        }
    }
}
