package com.dir.irfan_tentwenty_assignment.network

import com.dir.irfan_tentwenty_assignment.model.Movies
import com.dir.irfan_tentwenty_assignment.model.images.ImagesData
import com.dir.irfan_tentwenty_assignment.model.moviesdetail.MoviesDetail
import com.dir.irfan_tentwenty_assignment.model.videotrailer.VideoTrailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("upcoming?api_key=f15d1b35a6ac84a33a15c2d635face6a")
    suspend fun getMovies(): Response<Movies>

    @GET("{id}?api_key=f15d1b35a6ac84a33a15c2d635face6a")
    suspend fun getMoveisDetail(@Path("id",encoded = true) id: Int): Response<MoviesDetail>

    @GET("{id}/videos?api_key=f15d1b35a6ac84a33a15c2d635face6a")
    suspend fun getVideoTrailer(@Path("id",encoded = true) id: String): Response<VideoTrailer>

 @GET("{id}/images?api_key=f15d1b35a6ac84a33a15c2d635face6a")
    suspend fun getImagesList(@Path("id",encoded = true) id: String): Response<ImagesData>


}