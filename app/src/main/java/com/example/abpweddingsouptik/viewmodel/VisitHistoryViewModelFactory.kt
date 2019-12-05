///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.abpweddingsouptik.repository.VisitHistoryRepository

@Suppress("UNCHECKED_CAST")
class VisitHistoryViewModelFactory (private val repository: VisitHistoryRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VisitHistoryViewModel(repository) as T
    }
}