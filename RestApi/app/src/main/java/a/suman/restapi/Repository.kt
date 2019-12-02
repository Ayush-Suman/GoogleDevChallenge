package a.suman.restapi

class Repository(private val tMethods: tMethods){
    val tableData=tMethods.getuData()

    suspend fun insert(table: table) {
        tMethods.addData(table)
    }
    suspend fun delete(table: table){
        tMethods.delete(table)
    }
}