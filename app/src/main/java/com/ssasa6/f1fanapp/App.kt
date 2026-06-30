package com.ssasa6.f1fanapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class App : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(this)
            .crossfade(300)
            .build()
}
