package com.vladus177.albums.data.source

import com.vladus177.albums.data.repository.UserLocal
import com.vladus177.albums.data.repository.UserRemote
import javax.inject.Inject

class UserDataSourceFactory @Inject constructor(
    private val userRemote: UserRemote,
    private val userLocal: UserLocal
) {

    fun getRemote(): UserRemote = userRemote

    fun getLocal(): UserLocal = userLocal
}
