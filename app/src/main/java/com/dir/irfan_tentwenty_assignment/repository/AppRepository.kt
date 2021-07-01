package com.dir.irfan_tentwenty_assignment.repository

import com.dir.irfan_tentwenty_assignment.network.RequestBodies
import com.dir.irfan_tentwenty_assignment.network.RetrofitInstance

class AppRepository {

    suspend fun getMovies() = RetrofitInstance.serviceApi.getMovies()

    suspend fun getMoviesDetail(body: RequestBodies.requestBody) = RetrofitInstance.serviceApi.getMoveisDetail(body.id)

    suspend fun getVideoTrailer(body: RequestBodies.requestBody) = RetrofitInstance.serviceApi.getVideoTrailer(body.imdb)

    suspend fun getImagesList(body: RequestBodies.requestBody) = RetrofitInstance.serviceApi.getImagesList(body.imdb)

}