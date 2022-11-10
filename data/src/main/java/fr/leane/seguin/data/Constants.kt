package fr.leane.seguin.data

import com.google.gson.GsonBuilder

object Constants {
    val gson by lazy {
        GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()
    }
}