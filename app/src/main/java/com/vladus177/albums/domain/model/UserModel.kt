package vladus177.com.albums.domain.model

data class UserModel(
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressModel?,
    val phone: String?,
    val website: String?,
    val company: CompanyModel?
)
