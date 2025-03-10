package com.BasicAndroid.basicandroid.WebSocket

import org.json.JSONObject

data class TickerData(
    val symbol: String,
    val priceChange: String,
    val priceChangePercent: String,
    val weightedAvgPrice: String,
    val lastPrice: String,
    val lastQty: String,
    val openPrice: String,
    val highPrice: String,
    val lowPrice: String,
    val volume: String,
    val quoteVolume: String
)

fun parseTickerData(json: String): TickerData {
    val jsonObject = JSONObject(json)
    return TickerData(
        symbol = jsonObject.getString("s"),
        priceChange = jsonObject.getString("p"),
        priceChangePercent = jsonObject.getString("P"),
        weightedAvgPrice = jsonObject.getString("w"),
        lastPrice = jsonObject.getString("c"),
        lastQty = jsonObject.getString("Q"),
        openPrice = jsonObject.getString("o"),
        highPrice = jsonObject.getString("h"),
        lowPrice = jsonObject.getString("l"),
        volume = jsonObject.getString("v"),
        quoteVolume = jsonObject.getString("q")
    )
}
