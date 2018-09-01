package com.littlefireflies.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val clubList: MutableList<Club> = mutableListOf()
    lateinit var rvClubList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvClubList = recyclerView {
            lparams(width = matchParent, height = matchParent)
            layoutManager = LinearLayoutManager(context)
        }

        initData()

        rvClubList.adapter = RecyclerViewAdapter(this, clubList) {
            startActivity<DetailActivity>("club" to it)
        }
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
