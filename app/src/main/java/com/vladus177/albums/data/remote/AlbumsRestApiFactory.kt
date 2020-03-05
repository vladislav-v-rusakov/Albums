package vladus177.com.albums.data.remote

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class AlbumsRestApiFactory {

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val albumsApi: AlbumsRestApi = retrofit().create(AlbumsRestApi::class.java)

}