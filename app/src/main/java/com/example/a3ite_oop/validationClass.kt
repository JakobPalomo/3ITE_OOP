package com.example.a3ite_oop

import android.util.Log
import java.util.regex.Pattern
import kotlin.coroutines.suspendCoroutine
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class validationClass {
    public fun validatePassword(password: String) : Boolean{
        try {
                val pattern = """^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9@#$%^&+=!]).{5,8}$""".toRegex()
                return password.matches(pattern)

        }catch (e : Exception){
            Log.e("error_palomo", e.message.toString())
            return false
        }

    }

    public fun validateUsername(username: String) : Boolean{
        try {
            var registerObject = RegisterClass()
            var FindUsername = registerObject.ReturnArray().stream().anyMatch{x -> x.username.equals(username)}
                if(FindUsername)
                return false
            return true
        }catch (e : Exception){
            Log.e("error_palomo", e.message.toString())
            return false
        }
    }


    public fun isEighteenOrOlder(dateOfBirth: String, UserName: String): Boolean {
        try {
            // Define the date format
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            // Parse the input date string
            val birthDate = dateFormat.parse(dateOfBirth)

            // Calculate the age based on the birthdate
            val cal = Calendar.getInstance()
            cal.time = birthDate

            val today = Calendar.getInstance()
            val age = today.get(Calendar.YEAR) - cal.get(Calendar.YEAR) - if (today.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) 1 else 0

            if (age >= 18) {
                // User is at least 18 years old, log their age
                Log.d("info_palomo", "$UserName age is: $age years old")
            }

            // Check if the user is at least 18 years old
            return age >= 18
        } catch (e: Exception) {
            Log.e("error_palomo", e.message.toString())
            return false
        }
    }


}

