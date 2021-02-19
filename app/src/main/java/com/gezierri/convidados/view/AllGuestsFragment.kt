package com.gezierri.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gezierri.convidados.R
import com.gezierri.convidados.service.constants.GuestConstant
import com.gezierri.convidados.view.adapter.AllGuestAdapter
import com.gezierri.convidados.view.listener.GuestListener
import com.gezierri.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mGuestsViewModel: GuestsViewModel
    private val mAdapter: AllGuestAdapter = AllGuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mGuestsViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all_guest, container, false)

        //1 - Obter a   recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guest)

        //2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter
        recycler.adapter = mAdapter

        observer()

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestsFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstant.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mGuestsViewModel. delete(id)
                mGuestsViewModel.load(GuestConstant.FILTER.PRESENT)
            }
        }

        mAdapter.attachListener(mListener)

        return root
    }

    override fun onResume() {
        super.onResume()
        mGuestsViewModel.load(GuestConstant.FILTER.EMPTY)
    }

    private fun observer() {
        mGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}