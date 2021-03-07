package com.line.project.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.LruCache
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class ImageRepositoryImpl : ImageRepository {
    var contentSizeLiveData: MutableLiveData<Long> = MutableLiveData()
    private var cache: LruCache<String, Bitmap>

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        cache = LruCache(cacheSize)
    }

    override fun downloadImg(url: String, fileName: String): Bitmap? {
        contentSizeLiveData.postValue(0)
        var buffer = ByteArray(4096)
        var imgUrl: URL = URL(url)
        var connection = imgUrl.openConnection()
        var count = -1
        var file = File(
            Environment.getExternalStorageDirectory(),
            fileName
        )
        var input = connection.getInputStream()
        input.use { input ->
            FileOutputStream(file).use {
                while (true) {
                    count = input.read(buffer)
                    if (count <= 0)
                        break
                    it.write(buffer, 0, count)
                    contentSizeLiveData.postValue(contentSizeLiveData.value?.plus(count))
                }
                it.flush()
                it.close()
            }
        }
        input.close()
        cache.put(fileName, BitmapFactory.decodeFile(file.toString()))
        return BitmapFactory.decodeFile(file.toString())

    }

    override fun getContentSize(): Long {
        return contentSizeLiveData.value ?: 0
    }

    override fun getImage(fileName: String): Bitmap? {
        var bitmap: Bitmap? = cache.get(fileName)

        if (bitmap == null) {
            return getImageFromStorage(fileName)
        }else
            return bitmap
    }

    private fun getImageFromStorage(fileName: String) : Bitmap? {
        var file = File(
            Environment.getExternalStorageDirectory(),
            fileName
        )

        if (file.exists() && file.isFile) {
            var bitmap : Bitmap? = BitmapFactory.decodeFile(file.toString())
            cache.put(fileName, bitmap)
            return bitmap
        }else{
            return null
        }
    }
}