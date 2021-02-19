package com.gezierri.convidados.service.repository

import androidx.room.*
import com.gezierri.convidados.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getInveted(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresence(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>

}