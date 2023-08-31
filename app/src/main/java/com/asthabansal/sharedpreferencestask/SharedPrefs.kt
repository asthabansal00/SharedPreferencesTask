package com.asthabansal.sharedpreferencestask

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData


//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class SharedPrefs {

    var sharedPreferences:SharedPreferences?=null
    var editor:SharedPreferences.Editor?=null

    fun initPrefs(context: Context){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(
                context.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE)
            editor = sharedPreferences?.edit()
        }
    }

    fun saveColor(key:String,value:String){
        editor?.putString(key,value)
        editor?.commit()
    }

    fun saveListCount(key:String,value: Int){
        editor?.putInt(key,value)
        editor?.commit()
    }

    fun getColor(key: String):String{
        return sharedPreferences?.getString(key,"")?:""
    }

    fun getListCount(key:String):Int{
        return sharedPreferences?.getInt(key,0)?:0
    }


}
