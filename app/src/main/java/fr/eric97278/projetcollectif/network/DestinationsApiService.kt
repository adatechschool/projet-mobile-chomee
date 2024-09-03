package fr.eric97278.projetcollectif.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://freetestapi.com/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DestinationsApiService {
    @GET("destinations")
    fun getDestinations() : Any

    @GET("destinations/1")
    fun getOneDestination() : Any
}

object destinationsApi {
    val retrofitService : DestinationsApiService by lazy {
        retrofit.create(DestinationsApiService::class.java)
    }
}
