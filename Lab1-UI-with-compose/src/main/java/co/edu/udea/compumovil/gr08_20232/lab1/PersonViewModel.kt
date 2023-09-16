package co.edu.udea.compumovil.gr08_20232.lab1

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
) {
    fun validName() = name.isNotBlank().and(name.length > 3)
    fun validLastNames() = lastNames.isNotBlank().and(lastNames.length > 2)
    fun validBornDate() = bornDate != null

    fun validPhone() = phone.isNotBlank().and(phone.length > 6)
    fun validEmail() = email.isNotBlank().and(Patterns.EMAIL_ADDRESS.matcher(email).matches())
    fun validCountry() = country.isNotBlank().and(country.length > 3)

}

class PersonViewModel: ViewModel() {
    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    fun setUser(it: User) {
        _user.value = it
    }

    fun validPersonalInfo(): Boolean {
        if (user.value == null)
            return false
        return user.value!!.validName()
                && user.value!!.validLastNames()
                && user.value!!.validBornDate()
    }

    fun validContactInfo(): Boolean {
        if (user.value == null)
            return false
        return user.value!!.validPhone()
                && user.value!!.validEmail()
                && user.value!!.validCountry()
    }

}
