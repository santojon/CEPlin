package br.ufpe.cin.rxcep.ceplin.util

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class IntEvent(val value: Int) : Event, Comparable<IntEvent> {

    override val timestamp = Date()

    override fun compareTo(other: IntEvent): Int {
        return this.value.compareTo(other.value)
    }

    override fun equals(other: Any?): Boolean {
        if (other is IntEvent) {
            return this.value == other.value
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.toString()
    }
}

fun Int.toEvent(): IntEvent {
    return IntEvent(this)
}