package a.suman.restapi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface tMethods{
    @Query("Select * from uData")
    fun getuData():LiveData<List<table>>

    @Insert
    suspend fun addData(user:table)

    @Delete
    suspend fun delete(user: table)

    @Query("DELETE from uData")
    suspend fun deleteAll()
}