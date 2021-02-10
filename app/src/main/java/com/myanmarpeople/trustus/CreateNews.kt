package com.myanmarpeople.trustus

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.myanmarpeople.trustus.util.Constant
import java.io.File

class CreateNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_news)
        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            pickStaffImage()
        }
    }

    private fun pickStaffImage() {
        ImagePicker.with(this).compress(Constant.APP_IMAGE_SIZE)
            .maxResultSize(Constant.APP_IMAGE_MAX_SIZE, Constant.APP_IMAGE_MAX_SIZE)
            .start { resultCode, data ->
                if (resultCode == RESULT_OK) {
                    val fileUri = data?.data
                    val file: File? = ImagePicker.getFile(data)
                    findViewById<ImageView>(R.id.imageView).setImageURI(fileUri)
                    findViewById<ImageView>(R.id.imageView).tag = file
                }
            }
    }
}