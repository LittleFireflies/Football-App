package com.littlefireflies.footballclub

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val club = intent.getParcelableExtra<Club>("club")

        verticalLayout {
            padding = dip(16)

            val image = imageView().lparams(width = dip(100), height = dip(100)) {
                        gravity = Gravity.CENTER
                    }

            val title = textView {
                textColor = Color.BLACK
                textSize = 20f
            }.lparams {
                topMargin = dip(8)
                gravity = Gravity.CENTER
            }

            val description = textView()
                    .lparams(width = matchParent) {
                        topMargin = dip(16)
                    }

            title.text = club.name
            description.text = club.description
            Glide.with(context).load(club.image).into(image)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
