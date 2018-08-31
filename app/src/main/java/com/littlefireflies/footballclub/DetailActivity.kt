package com.littlefireflies.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val club = intent.getParcelableExtra<Club>("club")

        verticalLayout {
            padding = dip(16)

            imageView(club.image!!)
                    .lparams(width = dip(100), height = dip(100)) {
                        gravity = Gravity.CENTER
                    }

            val title = textView()
                    .lparams {
                        gravity = Gravity.CENTER
                    }

            val description = textView()
                    .lparams(width = matchParent)

            title.text = club.name
            description.text = club.description
        }

    }

}
