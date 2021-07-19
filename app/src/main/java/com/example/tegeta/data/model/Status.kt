package com.example.tegeta.data.model

enum class Status(val value:Int) {
    ADDED(0), FINISHED(1);

    companion object {
        private val map = Status.values().associateBy(Status::value)
        fun fromInt(type: Int) = map[type]
    }
}