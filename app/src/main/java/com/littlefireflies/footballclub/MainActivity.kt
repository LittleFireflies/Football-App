package com.littlefireflies.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val clubList: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    private fun initData() {
        val names = resources.getStringArray(R.array.club_name)
        val images = resources.obtainTypedArray(R.array.club_image)
        val descriptions = resources.getStringArray(R.array.club_description)

        clubList.clear()

        for (i in names.indices) {
            clubList.add(Club(names[i], images.getResourceId(i, 0), descriptions[i]))
        }

        images.recycle()
    }
}
