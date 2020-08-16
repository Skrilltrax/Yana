package me.skrilltrax.notes.ui

import android.view.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.skrilltrax.notes.R

object AnimUtils {

    fun animateFAB(view: FloatingActionButton, res: Int) {
        val inAnim = AnimationUtils.loadAnimation(view.context,
            R.anim.fab_open
        )
        val outAnim = AnimationUtils.loadAnimation(view.context,
            R.anim.fab_close
        )

        view.startAnimation(outAnim)
        view.setImageResource(res)
        view.startAnimation(inAnim)
    }
}
