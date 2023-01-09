package com.reza.rahmani.pokes.data.model.local

/**
 * Result Wrapper <Left = Exception, Right = Value/Success>
 */
sealed class ResultWraper<out E, out V> {
    data class Value<out V>(val value: V) : ResultWraper<Nothing, V>()
    data class Error<out E>(val error: E) : ResultWraper<E, Nothing>()
    companion object Factory{
        inline fun <V> build(function: () -> V): ResultWraper<Exception, V> =
            try {
                Value(function.invoke())
            } catch (e: java.lang.Exception) {
                Error(e)
            }
    }
}