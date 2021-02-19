package com.gezierri.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gezierri.convidados.viewmodel.GuestsFormViewModel
import com.gezierri.convidados.R
import com.gezierri.convidados.service.constants.GuestConstant
import kotlinx.android.synthetic.main.activity_guests_form.*
import kotlinx.android.synthetic.main.row_guest.*

class GuestsFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestsFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guests_form)

        mViewModel = ViewModelProvider(this).get(GuestsFormViewModel::class.java)

        // Eventos
        setListeners()

        // Cria observadores
        observe()

        // Carrega dados do usuário, caso haja
        loadData()

        // Default
        radio_presence.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = edit_name.text.toString()
            val presence = radio_presence.isChecked

            mViewModel.save(mGuestId, name, presence)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstant.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.presence) {
                radio_presence.isChecked = true 
            } else {
                radio_absent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }
}