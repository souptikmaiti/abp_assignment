///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.util

import android.app.Application
import com.example.abpweddingsouptik.data.network.NetworkConnectionInterceptor
import com.example.abpweddingsouptik.data.network.VisitsApi
import com.example.abpweddingsouptik.repository.VisitHistoryRepository
import com.example.abpweddingsouptik.viewmodel.VisitHistoryViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class KodeinDependencyInjection: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@KodeinDependencyInjection))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { VisitsApi(instance()) }
        bind() from singleton { VisitHistoryRepository(instance()) }
        bind() from provider { VisitHistoryViewModelFactory(instance()) }
    }
}