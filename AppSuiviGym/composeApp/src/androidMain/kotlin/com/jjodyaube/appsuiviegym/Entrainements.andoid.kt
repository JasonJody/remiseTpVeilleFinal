package com.jjodyaube.appsuiviegym

actual fun getHashMapEntrainement(): HashMap<String, Any> = FichierJson.getInstance().getFichier()
