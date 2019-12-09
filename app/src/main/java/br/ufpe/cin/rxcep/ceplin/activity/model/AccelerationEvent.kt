package br.ufpe.cin.rxcep.ceplin.activity.model

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class AccelerationEvent(val x: Float, val y: Float, val z: Float) : Event {
    override val timestamp = Date()
}

