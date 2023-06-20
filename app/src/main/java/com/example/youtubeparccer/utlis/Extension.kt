package com.example.youtubeparccer.utlis

import android.widget.ImageView


fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).into(this)
}