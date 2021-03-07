package com.line.project.domain.usecase

import android.graphics.Bitmap
import com.line.project.repository.ImageRepository
import com.line.project.repository.ImageRepositoryImpl

class GetImageUseCase {
    private val imageRepository : ImageRepository = ImageRepositoryImpl()

    fun downloadImage(url:String, fileName : String) : Bitmap?{
        return imageRepository.downloadImg(url,fileName)
    }

    fun getContentSize(): Long {
        return imageRepository.getContentSize()
    }

    fun getImage(fileName: String) : Bitmap?{
        return imageRepository.getImage(fileName)
    }
}