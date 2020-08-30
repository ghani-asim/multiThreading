package com.example.multithreading

import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import android.os.Handler

class Connection(mHand: Handler): Runnable {

    private val myHandler = mHand

    override fun run() {
        try {
            val myUrl = URL("http://www.randomtext.me/api/")
            val myConn = myUrl.openConnection() as HttpURLConnection
            myConn.requestMethod = "GET"

            val iStream: InputStream = myConn.inputStream
            val allText = iStream.bufferedReader().use {
                it.readText()
            }

            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()

            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)

        } catch (e: Exception) {

        }
    }

}