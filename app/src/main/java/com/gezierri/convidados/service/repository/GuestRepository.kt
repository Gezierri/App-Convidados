package com.gezierri.convidados.service.repository

import android.content.Context
import com.gezierri.convidados.service.model.GuestModel

class GuestRepository(context: Context) {

    private val mDatabase = GuestDataBase.getDatabase(context).guestDao()

    fun get(id: Int): GuestModel{
        return mDatabase.get(id)
    }

    fun save(guest: GuestModel): Boolean{
        return mDatabase.save(guest) > 0
    }

    fun getAll(): List<GuestModel>{
        return mDatabase.getInveted()
    }

    fun getPresent(): List<GuestModel>{
        return mDatabase.getPresence()
    }

    fun getAbsent(): List<GuestModel>{
        return mDatabase.getAbsent()
    }

    fun update(guest: GuestModel): Boolean{
        return mDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel){
        return mDatabase.delete(guest)
    }

}