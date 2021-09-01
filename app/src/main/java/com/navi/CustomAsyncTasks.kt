//package com.navi
//
//import android.os.Handler
//import android.os.Looper
//
//abstract class CustomAsyncTasks<T,  K>{
//
//    val handler = Handler(Looper.getMainLooper())
//
//    abstract fun doInBackground(input: T): K
//
//    abstract fun onPostExecute(result: K)
//
//    fun execute(originalInput: T): K{
//        Thread{
//         val result = doInBackground(originalInput)
//         handler.post {
//             onPostExecute(result)
//         }
//        }.start()
//
//    }
//}
//
//
//class MyTask(input: Int) : CustomAsyncTasks<Int, Int>() {
//
//    override fun onPostExecute(result: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun doInBackground(input: Int): K {
//        TODO("Not yet implemented")
//    }
//
//}