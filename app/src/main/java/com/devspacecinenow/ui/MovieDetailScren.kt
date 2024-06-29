package com.devspacecinenow.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailScreen() {


}

@Composable
private fun MovieDetailContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MovieDetailPreview() {
    MovieDetailContent()

}
