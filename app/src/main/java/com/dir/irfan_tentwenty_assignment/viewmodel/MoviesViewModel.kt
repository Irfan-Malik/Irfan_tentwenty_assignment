package com.dir.irfan_tentwenty_assignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.model.Movies
import com.dir.irfan_tentwenty_assignment.app.MyApplication
import com.dir.irfan_tentwenty_assignment.repository.AppRepository
import com.dir.irfan_tentwenty_assignment.util.Resource
import com.dir.irfan_tentwenty_assignment.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MoviesViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {
    val moviesData: MutableLiveData<Resource<Movies>> = MutableLiveData()

    init {
        getPictures()
    }

    fun getPictures() = viewModelScope.launch {
        fetchPics()
    }

    private suspend fun fetchPics() {
        moviesData.postValue(Resource.Loading())
        try {
            val responseMovies = appRepository.getMovies()
            moviesData.postValue(handleMoviesResponse(responseMovies))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> moviesData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.network_failure
                        )
                    )
                )
                else -> moviesData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.conversion_error
                        )
                    )
                )
            }
        }
    }

    private fun handleMoviesResponse(response: Response<Movies>): Resource<Movies> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}