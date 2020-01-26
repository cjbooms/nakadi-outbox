package com.cjbooms.nakadi.impl

import com.cjbooms.nakadi.NakadiPublisher
import nakadi.DataChangeEvent
import nakadi.NakadiException
import org.slf4j.LoggerFactory

class NakadiPublishException(
    override val message: String,
    var httpStatusCode: Int? = null
) : RuntimeException(message) {
    constructor(message: String, ex: NakadiException) : this(message, ex.problem()?.status())

    override fun fillInStackTrace(): Throwable = this
}

class NakadiPublisherImpl : NakadiPublisher {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun <T> sendEvent(event: DataChangeEvent<T>, eventType: String, flowId: String, appId: String) {
        try {
            tracedSendEvent(event, eventType, flowId, appId)
        } catch (ex: Exception) {
            val errorMsg =
                    "[$flowId] Exception when publishing to eventType [$eventType] for [$appId]. Reason: [${ex.message}"
            when (ex) {
                is NakadiPublishException -> throw ex
                is NakadiException -> throw NakadiPublishException(
                        message = errorMsg,
                        ex = ex
                )
                else -> throw NakadiPublishException(errorMsg)
            }
        }
    }

    private fun <T> tracedSendEvent(event: DataChangeEvent<T>, eventType: String, flowId: String, appId: String) {
        TODO("Implement me")
    }
}
