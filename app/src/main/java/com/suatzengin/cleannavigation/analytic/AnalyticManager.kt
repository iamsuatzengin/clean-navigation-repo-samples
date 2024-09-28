package com.suatzengin.cleannavigation.analytic

class AnalyticManager {
    private val firebaseEvent = AnalyticEvent { event ->
        println("Event Name: $event")
    }

    private val other1Event = AnalyticEvent { event ->
        println("Event Name: $event")
    }

    private val other2Event = AnalyticEvent { event ->
        println("Event Name: $event")
    }

    fun sendAnalyticEvent(eventName: String, eventType: EventType) {
        when (eventType) {
            EventType.Firebase -> firebaseEvent.sendEvent(eventName)
            EventType.Other1 -> other1Event.sendEvent(eventName)
            EventType.Other2 -> other2Event.sendEvent(eventName)
        }
    }
}

fun interface AnalyticEvent {
    fun sendEvent(eventName: String)
}

enum class EventType {
    Firebase,
    Other1,
    Other2
}

fun main() {
    val analyticManager = AnalyticManager()

    analyticManager.sendAnalyticEvent(eventName = "button_clicked", eventType = EventType.Firebase)
}