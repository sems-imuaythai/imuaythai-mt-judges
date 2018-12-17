package com.imuaythai.mtjudges.provider

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MTWebService {

    @GET("/2.0/?method=artist.search")
    fun searchArtist(@Query("artist") artist: String): Observable<String>

}