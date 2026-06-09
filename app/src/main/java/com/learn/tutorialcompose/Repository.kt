package com.learn.tutorialcompose

import kotlinx.coroutines.delay

class Repository {
    private val remoteDataResource = (1..100).map {
        ListItem2(
            title = "Item $it" ?: "null",
            description = "Description $it" ?: "null"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem2>> {
        delay(1500)
        val startingIndex = page * pageSize
        return if(startingIndex + pageSize <= remoteDataResource.size) {
            Result.success(
                remoteDataResource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}