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

class PersonViewModel {
    private val _name = MutableLiveData<String>()

    var name: LiveData<String> = _name
    private val _lastNames = MutableLiveData<String>()

    var lastNames: LiveData<String> = _lastNames
    private val _bornDate = MutableLiveData<LocalDate>()

    var bornDate: LiveData<LocalDate> = _bornDate
    private val _gender = MutableLiveData<Gender>()

    var gender: LiveData<Gender> = _gender
    private val _educationLevel = MutableLiveData<EducationLevel>()

    var educationLevel: LiveData<EducationLevel> = _educationLevel
    private val _email = MutableLiveData<String>()

    var email: LiveData<String> = _email
    private val _phone = MutableLiveData<String>()

    var phone: LiveData<String> = _phone
    private val _address = MutableLiveData<String>()

    var address: LiveData<String> = _address
    private val _city = MutableLiveData<String>()

    var city: LiveData<String> = _city
    private val _country = MutableLiveData<String>()

    var country: LiveData<String> = _country
    fun setBornDate(it: LocalDate) {
        _bornDate.value = it
    }

    fun setName(it: String) {
        _name.value = it
    }

    fun setLastNames(it: String) {
        _lastNames.value = it
    }

    fun setGender(it: Gender) {
        _gender.value = it
    }

    fun setEducationLevel(it: EducationLevel) {
        _educationLevel.value = it
    }

    override fun toString(): String {
        return "\n" +
                "name: ${name.value},\n" +
                "lastNames: ${lastNames.value},\n" +
                "bornDate: ${bornDate.value},\n" +
                "gender: ${gender.value},\n" +
                "educationLevel: ${educationLevel.value},\n" +
                "email: ${email.value},\n" +
                "phone: ${phone.value},\n" +
                "address: ${address.value},\n" +
                "city: ${city.value},\n" +
                "country: ${country.value},\n"
    }
}
