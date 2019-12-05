///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abpweddingsouptik.data.model.MainResponse
import com.example.abpweddingsouptik.repository.VisitHistoryRepository
import com.example.abpweddingsouptik.util.ApiException
import com.example.abpweddingsouptik.util.Coroutines
import com.example.abpweddingsouptik.util.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VisitHistoryViewModel (private val repository:VisitHistoryRepository) :ViewModel() {


    var year:Int? = null
    var month:Int? = null
    var viewModelListener:ViewModelListener? =null

    private lateinit var job: Job

    private val _histories = MutableLiveData<MainResponse>()
    val histories: LiveData<MainResponse>
        get() = _histories

    fun getVisitHistory() {
        if(year!=null && month!=null){
                job = Coroutines.ioThenMain(
                    {
                        try{
                            repository.getVisitHistory(year!!,month!!)
                        } catch (e:NoInternetException){
                            CoroutineScope(Dispatchers.Main).launch { viewModelListener?.onFailure(e.message!!) }
                            null
                        } catch (e:ApiException){
                            CoroutineScope(Dispatchers.Main).launch { viewModelListener?.onFailure(e.message!!) }
                            null
                        }
                    },

                    { _histories.value = it }
                )

        }

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}