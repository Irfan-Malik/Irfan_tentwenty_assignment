package com.dir.irfan_tentwenty_assignment.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dir.irfan_tentwenty_assignment.database.AppDatabase
import com.dir.irfan_tentwenty_assignment.database.dao.TicketDao
import com.dir.irfan_tentwenty_assignment.database.entity.Ticket
import com.dir.irfan_tentwenty_assignment.util.subscribeOnBackground

class TicketRepository(application: Application) {

    private var ticketDao: TicketDao
     var allTicket: LiveData<List<Ticket>>
    private val database = AppDatabase.getInstance(application)

    init {
        ticketDao = database.ticketDao()
        allTicket = ticketDao.getAllTickets()
    }

    fun insert(note: Ticket) {
        subscribeOnBackground {
            ticketDao.insert(note)
        }
    }

    fun update(note: Ticket) {
        subscribeOnBackground {
            ticketDao.update(note)
        }
    }

    fun delete(note: Ticket) {
        subscribeOnBackground {
            ticketDao.delete(note)
        }
    }

    fun deleteallTickets() {
        subscribeOnBackground {
            ticketDao.deleteAllTickets()
        }
    }

    fun getAllTickets() : LiveData<List<Ticket>> {
        return allTicket
    }




}