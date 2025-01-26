package com.easy.lokalsolution.RxJava

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rx_java)
        basicRxJava()
        generalRxOperators()

    }

    private fun generalRxOperators() {

        Observable.just(1, 2, 3)
            .map { it * 2 } // Multiply each item by 2
            .subscribe { println("Received: $it") }



    }

    private fun basicRxJava() {
        // Create an Observable
        val observable = Observable.create<String> {
            it.onNext("Hello, RxJava!")
            it.onNext("Learning RxJava with Kotlin")
            it.onComplete()
        }

        // Create an Observer
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("Subscribed")
            }

            override fun onNext(t: String) {
                println("Received: $t")
            }

            override fun onError(e: Throwable) {
                println("Error: ${e.message}")
            }

            override fun onComplete() {
                println("Completed")
            }
        }

        // Subscribe Observer to Observable
        observable
            .subscribeOn(Schedulers.io()) // Work on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Observe on Main thread
            .subscribe(observer)
    }



}