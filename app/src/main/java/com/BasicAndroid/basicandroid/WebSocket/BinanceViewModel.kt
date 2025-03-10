package com.BasicAndroid.basicandroid.WebSocket


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class BinanceViewModel : ViewModel() {
    private val _tickerDataMap = MutableStateFlow<Map<String, TickerData>>(emptyMap())
    val tickerDataMap = _tickerDataMap.asStateFlow()

    private val listener = BinanceWebSocketListener { data ->
        viewModelScope.launch {
            _tickerDataMap.value = _tickerDataMap.value + (data.symbol to data)
        }
    }

    init {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("wss://stream.binance.com:9443/stream?streams=btcusdt@ticker/ethusdt@ticker/bnbusdt@ticker/gpsusdt@ticker/linkusdt@ticker/xrpusdt@ticker")
            .build()
        client.newWebSocket(request, listener)
    }
}
