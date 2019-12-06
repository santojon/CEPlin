package br.ufpe.cin.rxcep.ceplin.activity.model

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class ListEvent<T : Event>(val events: List<T>) : Event {
    override val timestamp = Date()
}

