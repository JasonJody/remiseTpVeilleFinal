package com.jjodyaube.appsuiviegym

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object AudioPlayer {
    fun jouerSonAndroid(context: Context, resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            setOnCompletionListener {
                it.release()
            }
            start()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun jouerEffetSonore(context: Any, resId: Int) {

        GlobalScope.launch(Dispatchers.Main) {
            (context as? Context)?.let {
                jouerSonAndroid(it, resId)
            }
        }
    }
}