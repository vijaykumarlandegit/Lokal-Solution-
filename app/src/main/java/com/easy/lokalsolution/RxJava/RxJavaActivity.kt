package com.easy.lokalsolution.RxJava

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.easy.lokalsolution.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rx_java)
        basicRxJava()
        lambdaRxJavaExample()
        operatorExample()
        Observable.just("Hello, RxJava!")
            .subscribeOn(Schedulers.io()) // Background thread for data emission
            .observeOn(AndroidSchedulers.mainThread()) // Main thread for UI updates
            .subscribe { result ->
                println("Received: $result") // This runs on the main thread
            }

    }

    private fun basicRxJava() {
        // Create an Observable
        val observable = Observable.create<String> {
            it.onNext("Hello, RxJava!")
            it.onNext("Learning RxJava with Kotlin")
            it.onComplete()
        }

        // Create an Observer
        val observer = object : io.reactivex.rxjava3.core.Observer<String> {
            override fun onSubscribe(d: io.reactivex.rxjava3.disposables.Disposable) {
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
        observable.subscribeOn(Schedulers.io()) // Work on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Observe on Main thread
            .subscribe(observer)


    }
    fun lambdaRxJavaExample() {
        Observable.just("Kotlin", "RxJava", "Android")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item -> println("Received: $item") }, // onNext
                { error -> println("Error: ${error.message}") }, // onError
                { println("Completed") } // onComplete
            )
    }
    fun operatorExample() {
        Observable.just(1, 2, 3, 4, 5)
            .map { it * 2 } // Multiply each item by 2
            .filter { it > 5 } // Only allow items greater than 5
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { println("Received: $it") },
                { println("Error: ${it.message}") },
                { println("Completed") }
            )
    }

}