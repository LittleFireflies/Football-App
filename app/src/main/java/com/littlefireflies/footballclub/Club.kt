package com.littlefireflies.footballclub

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by widyarso.purnomo on 31/08/2018.
 */
@Parcelize
data class Club(val name: String?, val image: Int?, val description: String?) : Parcelable