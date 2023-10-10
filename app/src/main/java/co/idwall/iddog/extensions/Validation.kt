package co.idwall.iddog.extensions

import co.idwall.iddog.util.Validator

object Validation {
    fun String.isValidEmail(): Boolean {
        return Regex(Validator.EMAIL).matches(this)
    }

}