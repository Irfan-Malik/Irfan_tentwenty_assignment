package com.dir.irfan_tentwenty_assignment.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.database.entity.Ticket
import com.dir.irfan_tentwenty_assignment.viewmodel.TicketViewModel
import kotlinx.android.synthetic.main.book_ticket_layout.*


class TicketsDetailActivity : AppCompatActivity() {

    var spinnerLocation: TextView? = null
    var spinnerCenema: TextView? = null
    var spinnerSeat: TextView? = null
    var location : String? = null
    var cenama : String? = null
    var seat : String? = null

    private lateinit var viewModel: TicketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.ticket_detail_screen)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this)[TicketViewModel::class.java]
        spinnerLocation = findViewById<View>(R.id.spinnerLocation) as TextView
        spinnerCenema = findViewById<View>(R.id.spinnerCenema) as TextView
        spinnerSeat = findViewById<View>(R.id.spinnerSeat) as TextView


        getTicket()
        setAction()
    }

    fun setAction(){
        btnBookNow.setOnClickListener {
            finish()
        }
    }


    fun getTicket(){
        var allTickets : LiveData<List<Ticket>> = viewModel.getAlltickets()
        allTickets.observe(this, Observer {
            if(it.size > 0){
                spinnerLocation!!.text = it.get(it.size-1).location
                spinnerCenema!!.text = it.get(it.size-1).cenema
                spinnerSeat!!.text = it.get(it.size-1).seat
            }
        })


    }


}