package com.dir.irfan_tentwenty_assignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.database.entity.Ticket
import com.dir.irfan_tentwenty_assignment.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.book_ticket_layout.*


class BookTicketsActivity : AppCompatActivity() {

    var spinnerLocation: Spinner? = null
    var spinnerCenema: Spinner? = null
    var spinnerSeat: Spinner? = null
    var location : String? = null
    var cenama : String? = null
    var seat : String? = null

    private lateinit var viewModel: TicketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.book_ticket_layout)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this)[TicketViewModel::class.java]
        spinnerLocation = findViewById<View>(R.id.spinnerLocation) as Spinner
        spinnerCenema = findViewById<View>(R.id.spinnerCenema) as Spinner
        spinnerSeat = findViewById<View>(R.id.spinnerSeat) as Spinner

        locationSpinner()
        cenemaSpinner()
        seatSpinner()
        setAction()
    }

    fun setAction(){
        btnBookNow.setOnClickListener {
            insertTicket()
        }
        btnBooked.setOnClickListener {
            startActivity(Intent(this,TicketsDetailActivity::class.java))
        }

    }

    fun locationSpinner() {

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.location_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocation!!.adapter = adapter
        spinnerLocation!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                location = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

    }

    fun cenemaSpinner() {

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.cenema_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCenema!!.adapter = adapter
        spinnerCenema!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                cenama = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

    }

    fun seatSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.seat_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeat!!.adapter = adapter
        spinnerSeat!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                seat = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
    }

    fun insertTicket(){
        viewModel.insert(Ticket(location!!, cenama!!, seat!!))
        Toast.makeText(this, "Ticket inserted!", Toast.LENGTH_SHORT).show()
    }


}