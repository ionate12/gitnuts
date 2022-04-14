package com.example.gitnuts.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

//In order to add type converter in the future
fun getGson(): Gson = GsonBuilder().create()