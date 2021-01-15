package com.example.esoftwarica.model

import android.os.Parcel
import android.os.Parcelable

data class student(val fullname:String?, val address:String?, val age:String?, val gender:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullname)
        parcel.writeString(address)
        parcel.writeString(age)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<student> {
        override fun createFromParcel(parcel: Parcel): student {
            return student(parcel)
        }

        override fun newArray(size: Int): Array<student?> {
            return arrayOfNulls(size)
        }
    }
}