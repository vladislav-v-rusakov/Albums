package vladus177.com.albums.domain.model

data class AddressModel(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val location: LocationModel?
)