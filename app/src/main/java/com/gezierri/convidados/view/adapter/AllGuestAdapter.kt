package com.gezierri.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gezierri.convidados.R
import com.gezierri.convidados.service.model.GuestModel
import com.gezierri.convidados.view.listener.GuestListener
import com.gezierri.convidados.view.viewholder.GuestAllViewHolder

class AllGuestAdapter: RecyclerView.Adapter<GuestAllViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAllViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestAllViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GuestAllViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mListener = listener
    }
}