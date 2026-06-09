package com.learn.tutorialcompose

interface Paginator<Key, Item> {
    suspend fun loadNextitems()
//    fun reset()
}