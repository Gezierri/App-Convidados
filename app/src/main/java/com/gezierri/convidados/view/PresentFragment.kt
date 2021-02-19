package com.gezierri.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class PresentFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: AllGuestAdapter = AllGuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_present, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_present)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestsFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstant.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstant.FILTER.PRESENT)
            }
        }

        observe()
        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.load(GuestConstant.FILTER.PRESENT)
    }

    private fun observe() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}