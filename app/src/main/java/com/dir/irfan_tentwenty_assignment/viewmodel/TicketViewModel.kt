package com.dir.irfan_tentwenty_assignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dir.irfan_tentwenty_assignment.database.entity.Ticket
import com.dir.irfan_tentwenty_assignment.repository.TicketRepository

class TicketViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = TicketRepository(app)

    fun insert(ticket: Ticket) {
        repository.insert(ticket)
    }

    fun update(ticket: Ticket) {
        repository.update(ticket)
    }

    fun delete(ticket: Ticket) {
        repository.delete(ticket)
    }

    fun deleteAlltickets() {
        repository.deleteallTickets()
    }

    fun getAlltickets() : LiveData<List<Ticket>> {
        return repository.getAllTickets()
    }


}