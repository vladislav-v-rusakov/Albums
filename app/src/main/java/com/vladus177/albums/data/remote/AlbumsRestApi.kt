package vladus177.com.albums.data.remote

import retrofit2.Call
import retrofit2.http.GET
import vladus177.com.albums.data.remote.model.UserEntry

interface AlbumsRestApi {

    @GET("/users")
    suspend fun getAllUsers(): Call<List<UserEntry>>

}