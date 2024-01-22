package com.tiooooo.myproduct.utils

fun <T> States<T>.handleStates(
    loadingBlock: ((Boolean) -> Unit)? = null,
    emptyBlock: (() -> Unit)? = null,
    errorBlock: ((String) -> Unit)? = null,
    successBlock: (T) -> Unit,
) {
    when (this) {
        is States.Loading -> {
            loadingBlock?.invoke(true)
        }

        is States.Success -> {
            loadingBlock?.invoke(false)
            successBlock.invoke(data)
        }

        is States.Empty -> {
            loadingBlock?.invoke(false)
            emptyBlock?.invoke()
        }

        is States.Error -> {
            loadingBlock?.invoke(false)
            val error = "Ops something error, try again letter"
            errorBlock?.invoke(error)
        }
    }
}
