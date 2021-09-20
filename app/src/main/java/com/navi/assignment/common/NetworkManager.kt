package com.navi.assignment.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import kotlin.properties.Delegates

object NetworkManager {
    var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        Log.i("", "Network connectivity changed from $oldValue to $newValue : $property")
    }

    fun startNetworkCallback(context: Context) {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    isNetworkConnected = true
                }
            })
    }
}