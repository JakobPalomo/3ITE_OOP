package com.example.a3ite_oop

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declare of object
        var registerObject = RegisterClass()


        //Declaration of buttons
        var buttonRegister : Button = findViewById(R.id.btnRegister)
        var buttonMainMenu : Button = findViewById(R.id.btnMenu)

        //Dec edit txt
        var editFirstName : EditText = findViewById(R.id.edtFirstname)
        var editLastName : EditText = findViewById(R.id.edtLastname)
        var editUserName : EditText = findViewById(R.id.edtUsername)
        var editPassword  : EditText = findViewById(R.id.edtPassword)
        var editEmail     : EditText = findViewById(R.id.edtEmail)
        var editMobile    : EditText = findViewById(R.id.editPhoneNumber)
        var editBirthday : EditText= findViewById(R.id.edtBirthday)

        var database = mutableListOf<UserInfo>()
        //Dec spin
        var spinnerAccess : Spinner = findViewById(R.id.spinner)

        val validationObject = validationClass()
//        val phoneNumberEditText = findViewById<EditText>(R.id.editPhoneNumber)
//        validationObject.setupPhoneNumberInputValidation(phoneNumberEditText)

        editMobile.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    if (editMobile.text.length >=12){
                        editMobile.setText(editMobile.text.substring(0,editMobile.length() - 1))
                        editMobile.setSelection(11)
                    }
                }catch (e : Exception){
                    Log.e("error_palomo", e.message.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {
//                val maxLength = 11 // Maximum allowed length for the phone number
//
//                if (editable != null && editable.length > maxLength) {
//                    // If the input length exceeds the maximum, trim it to maxLength
//                    val trimmedText = editable.toString().substring(0, maxLength)
//                    editMobile.setText(trimmedText)
//                    editMobile.setSelection(maxLength) // Set cursor to the end
//                }
            }
        })

        //setting events
        buttonRegister.setOnClickListener {

            //1st option dialog window
            //showConfirmationDialog()

            //1st option dialog window
            var fragmentObject = FragmentClass()
            fragmentObject.isCancelable = false
            var bundle = Bundle()
            bundle.putString("bundle01", "Jakob")
            bundle.putString("bundle02", "Palomo")
            fragmentObject.arguments = bundle
            fragmentObject.show(supportFragmentManager, "Custom Dialog")


            bundle.putString("bundleFN", editFirstName.text.toString())
            bundle.putString("bundleLN", editLastName.text.toString())
            bundle.putString("bundleEM", editEmail.text.toString())
            bundle.putString("bundleUN", editUserName.text.toString())
            bundle.putString("bundleMB", editMobile.text.toString())
            bundle.putString("bundleAcc", spinnerAccess.selectedItem.toString())


            // Validate the password
            val password = editPassword.text.toString()
            val validationObject = validationClass()
            validationObject.validatePassword(password)
            bundle.putString("bundlePW", password)

            if (validationObject.validatePassword(password)) {
                var userData = UserInfo(editUserName.text.toString(),
                    editPassword.text.toString(),
                    editFirstName.text.toString(),
                    editLastName.text.toString(),
                    editEmail.text.toString(),
                    editMobile.text.toString(),
                    editBirthday.text.toString(),
                    spinnerAccess.selectedItem.toString()
                )
                registerObject.AddToArray(userData)

                database.add(userData)

                for (user in database) {
                    Log.i("info_content",user.toString())

                }


            }

            if (validationObject.isEighteenOrOlder(editBirthday.text.toString(),editUserName.text.toString())) {
                // Date of birth is valid, and the user is at least 18 years old
                bundle.putString("bundleDate", editBirthday.text.toString())
            } else {
                Toast.makeText(this, "You are not 18 and above", Toast.LENGTH_SHORT).show()
            }


        }
        buttonMainMenu.setOnClickListener {

            for (user in database) {
                Log.i("info_content",user.toString())

            }
        }

        editBirthday.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year =  calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { it, year, month, day ->
                    val dat= (day.toString()+'-'+(month+1)+'-'+year)
                    editBirthday.setText(dat)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        //populating spinner
        val accesses = resources.getStringArray(R.array.Accesses)
        val adapter = ArrayAdapter(this,R.layout.activity_spinner, R.id.txtSpinner, accesses)
        spinnerAccess.adapter=adapter



    }

    private fun showConfirmationDialog(){
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var textDialog : TextView = dialog.findViewById(R.id.txtDialog)
        textDialog.setText("Jakob Palomo is showing Dialog")

        var buttonExit : Button = dialog.findViewById(R.id.btnExit)

        buttonExit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}