package com.vladus177.albums.presentation.model

data class UserView (
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressView?,
    val phone: String?,
    val website: String?,
    val company: CompanyView?
)