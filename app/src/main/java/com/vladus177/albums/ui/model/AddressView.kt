package com.vladus177.albums.presentation.model

data class AddressView (
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val location: LocationView?
)