package a.suman.restapi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="uData")
 data class table (
    @PrimaryKey
    @ColumnInfo(name = "Contact")
    var contactN: String=" ",

    @ColumnInfo(name = "Name")
    var name:String?=null,

    @ColumnInfo(name="Address")
    var address:String?=null
)