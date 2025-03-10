package com.BasicAndroid.basicandroid.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.BasicAndroid.basicandroid.WebSocket.BinanceViewModel
import com.BasicAndroid.basicandroid.WebSocket.TickerData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinanceTickerScreen(binanceViewModel: BinanceViewModel) {
    val tickerDataMap by binanceViewModel.tickerDataMap.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Binance Ticker Data") })
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.padding(16.dp)
        ) {
            items(tickerDataMap.values.toList()) { data ->
                TickerDataItem(tickerData = data)
            }
        }
    }
}

@Composable
fun TickerDataItem(tickerData: TickerData) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Symbol: ${tickerData.symbol}")
        Text(text = "Last Price: ${tickerData.lastPrice}")
        Text(text = "Price Change: ${tickerData.priceChange} (${tickerData.priceChangePercent}%)")
        Text(text = "High Price: ${tickerData.highPrice}")
        Text(text = "Low Price: ${tickerData.lowPrice}")
        Text(text = "Volume: ${tickerData.volume}")
    }
}