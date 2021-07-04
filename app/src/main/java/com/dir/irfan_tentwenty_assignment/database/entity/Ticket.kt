package com.dir.irfan_tentwenty_assignment.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
class Ticket(val location: String,
             val cenema: String,
             val seat: String,
              @PrimaryKey(autoGenerate = false) val id: Int? = null
             )
