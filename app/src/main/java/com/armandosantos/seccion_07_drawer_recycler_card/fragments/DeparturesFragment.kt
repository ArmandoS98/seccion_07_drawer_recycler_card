package com.armandosantos.seccion_07_drawer_recycler_card.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.armandosantos.seccion_07_drawer_recycler_card.R
import com.armandosantos.seccion_07_drawer_recycler_card.adapters.FlightAdapter
import com.armandosantos.seccion_07_drawer_recycler_card.listeners.RecyclerFlightListener
import com.armandosantos.seccion_07_drawer_recycler_card.models.Flight
import com.armandosantos.seccion_07_drawer_recycler_card.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*

class DeparturesFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { getFlights() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_departures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.recyclerView as RecyclerView

        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object : RecyclerFlightListener {
            override fun onClick(flight: Flight, position: Int) {
                activity?.toast("Let´s go to ${flight.city}")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city}  has been removed!")
            }
        }))

        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<Flight> {
        return object : ArrayList<Flight>() {
            init {
                add(Flight(1, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(2, "Santa Rosa", R.drawable.ic_arrivals))
                add(Flight(3, "Baja Verapaz", R.drawable.ic_arrivals))
                add(Flight(4, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(5, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(6, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(7, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(8, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(9, "Guatemala", R.drawable.ic_arrivals))
                add(Flight(10, "Guatemala", R.drawable.ic_arrivals))
            }
        }
    }
}
