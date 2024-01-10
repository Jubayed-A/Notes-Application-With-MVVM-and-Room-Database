package com.example.notesapplication.Model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class representing a Note in the application
@Entity(tableName = "Notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,      // Unique identifier for the note
    var title: String?,       // Title of the note
    var subTitle: String?,    // Subtitle of the note
    var notes: String?,       // Content/body of the note
    var date: String?,        // Date associated with the note
    var priority: String?     // Priority level of the note
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    // Writing object values to a parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(subTitle)
        parcel.writeString(notes)
        parcel.writeString(date)
        parcel.writeString(priority)
    }

    // Required method to describe the contents of the parcel
    override fun describeContents(): Int {
        return 0
    }

    // Creator object implementing Parcelable's CREATOR interface
    companion object CREATOR : Parcelable.Creator<Notes> {
        // Create a Note object from the Parcel
        override fun createFromParcel(parcel: Parcel): Notes {
            return Notes(parcel)
        }

        // Create an array of Note objects of the specified size
        override fun newArray(size: Int): Array<Notes?> {
            return arrayOfNulls(size)
        }
    }
}
