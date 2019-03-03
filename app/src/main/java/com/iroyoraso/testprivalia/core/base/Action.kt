package com.iroyoraso.testprivalia.core.base

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
interface Action<IN, OUT> {

    fun performWith(input: IN, listener: Listener<OUT>)

    fun cancel()

}