package com.example.a3ite_oop

import android.util.Log


var userArray = ArrayList<UserInfo>()

class RegisterClass {

    public fun AddToArray(userInfo: UserInfo){

        try {
            userArray.add(userInfo)
            ShowArray()
        }catch (e : Exception){
           Log.e("error_palomo", e.message.toString())
        }

    }

    public fun ReturnArray() : ArrayList<UserInfo>{
        return userArray
    }

    private fun ShowArray(){

        for (element in userArray){
            Log.i("info_palomo", element.username + element.password)
        }

    }

}