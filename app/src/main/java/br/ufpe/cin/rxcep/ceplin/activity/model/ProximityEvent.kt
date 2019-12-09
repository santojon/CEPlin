package br.ufpe.cin.rxcep.ceplin.activity.model

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class ProximityEvent(val dist: Float) : Event {
    override val timestamp = Date()
}

