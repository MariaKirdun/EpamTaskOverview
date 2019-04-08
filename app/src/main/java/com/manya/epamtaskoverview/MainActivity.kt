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

class MainActivity : AppCompatActivity() {

    val GALLERY_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OverviewButton.setOnClickListener {
            val photoIntent = Intent(Intent.ACTION_PICK)
            photoIntent.type = "image/*"
            startActivityForResult(photoIntent, GALLERY_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                val selectedImage = MediaStore.Images.Media.getBitmap(contentResolver,uri)
                OverviewImageView.setImageBitmap(selectedImage)
            }
        } else {
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
        }
    }
}
