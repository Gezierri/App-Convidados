package com.gezierri.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gezierri.convidados.service.constants.GuestConstant
import com.gezierri.convidados.service.model.GuestModel
import com.gezierri.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestAllRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {

        if (filter == GuestConstant.FILTER.EMPTY){
            mGuestList.value = mGuestAllRepository.getAll()
        }else if (filter == GuestConstant.FILTER.PRESENT){
            mGuestList.value = mGuestAllRepository.getPresent()
        }else{
            mGuestList.value = mGuestAllRepository.getAbsent()
        }
    }

    fun delete(id: Int) {
        mGuestAllRepository.delete(id)
    }
}