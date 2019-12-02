package a.suman.restapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application){
    val tMethods= Database.getDatabase(application).tmethods()
    val repository=Repository(tMethods)
    val tableData=repository.tableData
    fun delete(user: table)=viewModelScope.launch { repository.delete(user) }
  fun insert(user:table)=viewModelScope.launch {  repository.insert(user)}
}