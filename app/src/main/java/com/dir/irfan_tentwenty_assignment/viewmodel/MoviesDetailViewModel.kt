package com.dir.irfan_tentwenty_assignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.app.MyApplication
import com.dir.irfan_tentwenty_assignment.model.images.ImagesData
import com.dir.irfan_tentwenty_assignment.model.moviesdetail.MoviesDetail
import com.dir.irfan_tentwenty_assignment.model.videotrailer.VideoTrailer
import com.dir.irfan_tentwenty_assignment.network.RequestBodies
import com.dir.irfan_tentwenty_assignment.repository.AppRepository
import com.dir.irfan_tentwenty_assignment.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MoviesDetailViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    val _moviesDetailResponse = MutableLiveData<Resource<MoviesDetail>>()
    val videoTrailer: MutableLiveData<Resource<VideoTrailer>> = MutableLiveData()
    val imagesResponse: MutableLiveData<Resource<ImagesData>> = MutableLiveData()

    fun moviesDetails(body: RequestBodies.requestBody) = viewModelScope.launch {
        moviesDetail(body)
    }

     fun videoTrailers(body: RequestBodies.requestBody) = viewModelScope.launch {
         getvideoTrailer(body)
    }

     fun imagesList(body: RequestBodies.requestBody) = viewModelScope.launch {
         getImagesList(body)
    }

    private suspend fun moviesDetail( body: RequestBodies.requestBody) {
        _moviesDetailResponse.postValue((Resource.Loading()))
        try {
//            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getMoviesDetail(body)
                _moviesDetailResponse.postValue(handleMovieDetailResponse(response))
//            } else {
//                _moviesDetailResponse.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
//            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _moviesDetailResponse.postValue(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        )
                    )
                }
                else -> {
                    _moviesDetailResponse.postValue(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.conversion_error
                            )
                        )
                    )
                }
            }
        }
    }

    private suspend fun getvideoTrailer( body: RequestBodies.requestBody) {
        videoTrailer.postValue((Resource.Loading()))
        try {
//            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
            val response = appRepository.getVideoTrailer(body)
            videoTrailer.postValue(handleVideoTrailerResponse(response))
//            } else {
//                _moviesDetailResponse.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
//            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    videoTrailer.postValue(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        )
                    )
                }
                else -> {
                    videoTrailer.postValue(Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.conversion_error
                        )
                    )
                    )
                }
            }
        }
    }

    private suspend fun getImagesList( body: RequestBodies.requestBody) {
        imagesResponse.postValue((Resource.Loading()))
        try {
//            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
            val response = appRepository.getImagesList(body)
            imagesResponse.postValue(handleImagesResponse(response))
//            } else {
//                _moviesDetailResponse.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
//            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    imagesResponse.postValue(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        )
                    )
                }
                else -> {
//                    imagesResponse.postValue(Resource.Error(
//                        getApplication<MyApplication>().getString(
//                            R.string.conversion_error
//                        )
//                    )
//                    )
                }
            }
        }
    }

    private fun handleImagesResponse(response: Response<ImagesData>): Resource<ImagesData>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleVideoTrailerResponse(response: Response<VideoTrailer>): Resource<VideoTrailer>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleMovieDetailResponse(response: Response<MoviesDetail>): Resource<MoviesDetail>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}