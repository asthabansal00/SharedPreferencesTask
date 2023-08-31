package com.asthabansal.sharedpreferencestask

object Singleton {
    init {
        System.out.println("I am singleton")
    }
    var sharedPreferences : SharedPrefs = SharedPrefs()
}