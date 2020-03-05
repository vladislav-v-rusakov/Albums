package com.vladus177.albums.domain.model

import com.vladus177.albums.domain.model.AddressModel
import com.vladus177.albums.domain.model.CompanyModel

data class UserModel(
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressModel?,
    val phone: String?,
    val website: String?,
    val company: CompanyModel?,
    val isFavorite: Boolean
)
