package com.manya.epamtaskoverview

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is main activity. We can choose image from gallery when click on button
 * and picked image we can see on ImageView.
 *
 * @author: Maria Kirdun
 *
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseImageButton.setOnClickListener {
          chooseImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                val selectedImage = MediaStore.Images.Media.getBitmap(contentResolver,uri)
                pickedImageView.setImageBitmap(selectedImage)
            }
            else -> Toast.makeText(this, R.string.wrong_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun chooseImage(){
        val photoIntent = Intent(Intent.ACTION_PICK)
        photoIntent.type = PHOTO_TYPE
        startActivityForResult(photoIntent, Companion.GALLERY_REQUEST)
    }

    companion object {
        private const val GALLERY_REQUEST : Int = 1
        private const val PHOTO_TYPE : String = "image/*"
    }
}
