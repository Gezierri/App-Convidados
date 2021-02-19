package com.gezierri.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gezierri.convidados.R
import com.gezierri.convidados.service.model.GuestModel
import com.gezierri.convidados.view.listener.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestAllViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(guestModel: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guestModel.name

        textName.setOnClickListener {
            listener.onClick(guestModel.id)
        }

        textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Excluir")
                .setMessage("deseja realmente excluir convidado?")
                .setPositiveButton("remover") { dialog, which ->
                    listener.onDelete(guestModel.id)
                }
                .setNeutralButton("Cancelar", null)
                .show()
            true
        }
    }
}