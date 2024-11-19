package com.jjodyaube.appsuiviegym

class FichierJson private constructor() {
    companion object {

        @Volatile
        private var instance: FichierJson? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FichierJson().also { instance = it }
            }
    }

    fun getFichier() = HashMap<String, Any>()
}