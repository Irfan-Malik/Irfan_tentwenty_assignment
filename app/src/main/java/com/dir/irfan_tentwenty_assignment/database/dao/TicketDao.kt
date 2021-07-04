package com.dir.irfan_tentwenty_assignment.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dir.irfan_tentwenty_assignment.database.entity.Ticket

@Dao
interface TicketDao {
    @Insert
    fun insert(ticket: Ticket)
    @Update
    fun update(ticket: Ticket)
    @Delete
    fun delete(ticket: Ticket)
    @Query("delete from ticket_table")
    fun deleteAllTickets()

    @Query("select * from ticket_table")
    fun getAllTickets(): LiveData<List<Ticket>>
}