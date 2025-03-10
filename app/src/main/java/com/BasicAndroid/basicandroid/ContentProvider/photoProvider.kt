package com.BasicAndroid.basicandroid.ContentProvider

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.database.getLongOrNull
import java.io.File

class photoProvider(private val context: Context) {

    fun fetchPhoto(): List<Photos> {
        val photosList = mutableListOf<Photos>()

        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            "${MediaStore.Images.Media.MIME_TYPE} = ?",
            arrayOf("image/jpeg"),
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )
        cursor?.use {
            val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val mimeIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val tittleIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
            val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (it.moveToNext()) {
                val index = it.getLong(idIndex)
                val mimeType = it.getString(mimeIndex)
                val tittle = it.getString(tittleIndex)
                val fullPath = it.getString(dataIndex)
                val folderName = File(fullPath).parentFile?.name ?: "Root"
                val imageUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, index
                )

                photosList.add(
                    Photos(
                        id = index,
                        mimeType = mimeType,
                        tittle = tittle,
                        folderName = folderName,
                        image = imageUri
                    )
                )
            }
        }
        return photosList
    }

    fun folderNames(): List<String> {
        val folderList = mutableSetOf<String>() // Use Set to avoid duplicates
        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media.DATA),
            null, // No filter to get all images
            null,
            "${MediaStore.Images.Media.DATA} DESC" // Fixed sorting order
        )

        cursor?.use {
            val dataIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (it.moveToNext()) {
                val fullPath = it.getString(dataIndex)
                val folderName = File(fullPath).parentFile?.name ?: "Root"
                folderList.add(folderName) // Avoid duplicates
            }
        }

        return folderList.toList() // Convert Set to List
    }
}
    data class Photos(
        val id: Long,
        val mimeType: String,
        val tittle: String,
        val folderName: String,
        val image: Uri,
    )