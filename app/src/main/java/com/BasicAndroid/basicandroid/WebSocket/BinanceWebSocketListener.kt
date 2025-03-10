package com.BasicAndroid.basicandroid.WebSocket

import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import android.util.Log
import org.json.JSONObject

class BinanceWebSocketListener(
    private val onTickerUpdate: (TickerData) -> Unit
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        Log.d("WebSocket", "Connected to Binance WebSocket")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d("WebSocket", "onMessage: $text")
        val jsonObject = JSONObject(text)
        val data = jsonObject.optJSONObject("data")
        data?.let {
            val tickerData = parseTickerData(it.toString())
            onTickerUpdate(tickerData)
        }
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d("WebSocket", "Received bytes: ${bytes.hex()}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("WebSocket", "Closing: $code / $reason")
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        Log.e("WebSocket", "Error: ${t.message}", t)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }
}
