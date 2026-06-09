package com.learn.tutorialcompose

class Defaultpaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private val getNextKey: () -> Key,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {
    private var currentKey = initialKey
    var isMakingRequest = false
    override suspend fun loadNextitems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse(
            onFailure = { throwable ->
                onError(throwable)
                onLoadUpdated(false)
                return
            })
        currentKey = getNextKey()
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

//    override fun reset() {
//        currentKey = initialKey
//    }
}