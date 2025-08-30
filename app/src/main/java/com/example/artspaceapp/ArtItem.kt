package com.example.artspaceapp

import androidx.annotation.DrawableRes

data class ArtItem(
    var title: String,
    var artist: String,
    @DrawableRes var imageId: Int
)
