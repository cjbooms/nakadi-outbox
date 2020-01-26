package com.cjbooms.nakadi

import nakadi.DataChangeEvent

interface NakadiPublisher {
    fun <T> sendEvent(event: DataChangeEvent<T>, eventType: String, flowId: String, appId: String)
}
