package com.dir.irfan_tentwenty_assignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dir.irfan_tentwenty_assignment.repository.AppRepository

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(app, appRepository) as T
        }
        if (modelClass.isAssignableFrom(MoviesDetailViewModel::class.java)) {
            return MoviesDetailViewModel(app, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}