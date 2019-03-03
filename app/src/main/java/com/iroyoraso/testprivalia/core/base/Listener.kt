package com.iroyoraso.testprivalia.core.base

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
interface Listener<OUT> {
    fun successful(output: OUT)
    fun failed()
}