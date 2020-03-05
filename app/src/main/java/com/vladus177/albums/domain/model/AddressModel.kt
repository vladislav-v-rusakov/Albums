package com.vladus177.albums.domain.model

data class AddressModel(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val location: LocationModel?
)