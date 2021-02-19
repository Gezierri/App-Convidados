package com.gezierri.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gezierri.convidados.service.model.GuestModel
import com.gezierri.convidados.service.repository.GuestRepository

class GuestsFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContextMenu = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContextMenu)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuestModel

    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }
        if (id == 0) {
            mSaveGuest.value = mGuestRepository.save(guest)
        }else{
            mSaveGuest.value = mGuestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mGuestModel.value = mGuestRepository.get(id)
    }
}