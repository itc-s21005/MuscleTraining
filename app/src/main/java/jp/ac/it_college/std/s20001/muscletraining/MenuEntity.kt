package jp.ac.it_college.std.s20001.muscletraining

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuEntity(
    val name: String,
    val description: String,
    val images: String
): Parcelable
