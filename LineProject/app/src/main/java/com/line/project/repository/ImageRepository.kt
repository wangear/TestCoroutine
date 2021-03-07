package com.line.project.repository

import android.graphics.Bitmap

interface ImageRepository {
    fun downloadImg(url:String, fileName:String) : Bitmap?
    fun getContentSize() : Long
    fun getImage(fileName:String) : Bitmap?
}