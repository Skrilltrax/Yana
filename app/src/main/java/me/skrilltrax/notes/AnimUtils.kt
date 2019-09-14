package me.skrilltrax.notes

import android.view.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

object AnimUtils {

    fun animateFAB(view: FloatingActionButton, res: Int) {
        val inAnim = AnimationUtils.loadAnimation(view.context, R.anim.fab_open)
        val outAnim = AnimationUtils.loadAnimation(view.context, R.anim.fab_close)

        view.startAnimation(outAnim)
        view.setImageResource(res)
        view.startAnimation(inAnim)
    }
}