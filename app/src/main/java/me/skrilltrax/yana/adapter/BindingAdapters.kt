package me.skrilltrax.notes.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.skrilltrax.notes.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imgSrc")
    fun imageSrc(view: ImageView, url: Uri?) {
            Glide.with(view)
                .load(url)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_account_circle_24dp)
                .into(view)
    }
}
