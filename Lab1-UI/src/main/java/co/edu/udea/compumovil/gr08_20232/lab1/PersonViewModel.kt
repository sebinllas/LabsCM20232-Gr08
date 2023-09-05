package co.edu.udea.compumovil.gr08_20232.lab1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

enum class EducationLevel {
    PRIMARY,
    SECONDARY,
    TECHNICAL,
    TECHNOLOGIST,
    PROFESSIONAL,
    SPECIALIST,
    MASTER,
    DOCTORATE
}

data class User(
    val name: String = "",
    val lastNames: String = "",
    val bornDate: LocalDate? = null,
    val gender: Gender? = null,
    val educationLevel: EducationLevel? = null,
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val city: String = "",
    val country: String = "",
)

class PersonViewModel {
    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    fun setUser(it: User) {
        _user.value = it
    }
}
