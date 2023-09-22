package co.edu.udea.compumovil.gr08_20232.lab1

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

enum class Gender(@StringRes val resourceId: Int) {
    MALE(R.string.gender_male),
    FEMALE(R.string.gender_female),
    OTHER(R.string.gender_other)
}

enum class EducationLevel(val resourceId: Int) {
    PRIMARY(R.string.education_level_primary),
    SECONDARY(R.string.education_level_secondary),
    TECHNICAL(R.string.education_level_technical),
    TECHNOLOGIST(R.string.education_level_technologist),
    PROFESSIONAL(R.string.education_level_professional),
    SPECIALIST(R.string.education_level_specialist),
    MASTER(R.string.education_level_master),
    DOCTORATE(R.string.education_level_doctorate)
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

class PersonViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    private val _personalInfoNextClicked = MutableLiveData(false)
    var personalInfoNextClicked: LiveData<Boolean> = _personalInfoNextClicked


    private val _contactInfoNextClicked = MutableLiveData(false)
    var contactInfoNextClicked: LiveData<Boolean> = _contactInfoNextClicked

    fun getCitySuggestions(): Int? {
        if (user.value?.country == "Colombia") {
            return R.array.colombian_cities
        }
        return null
    }

    fun setUser(it: User) {
        _user.value = it
    }

    fun setPersonalInfoNextClicked(it: Boolean) {
        _personalInfoNextClicked.value = it
    }

    fun setContactInfoNextClicked(it: Boolean) {
        _contactInfoNextClicked.value = it
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

    fun getInvalidPersonalInfoFields(): List<Int> {
        if (user.value == null) {
            return listOf(R.string.name_label, R.string.lastname_label, R.string.birthdate_label)
        }
        return mutableListOf<Int>().apply {
            if (!user.value!!.validName())
                add(R.string.name_label)
            if (!user.value!!.validLastNames())
                add(R.string.lastname_label)
            if (!user.value!!.validBornDate())
                add(R.string.birthdate_label)
        }
    }

    fun getInvalidContactInfoFields(): List<Int> {
        if (user.value == null) {
            return listOf(R.string.country_label, R.string.phone_label, R.string.email_label)
        }
        return mutableListOf<Int>().apply {
            if (!user.value!!.validCountry())
                add(R.string.country_label)
            if (!user.value!!.validPhone())
                add(R.string.phone_label)
            if (!user.value!!.validEmail())
                add(R.string.email_label)
        }
    }

}
