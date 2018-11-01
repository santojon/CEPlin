package br.ufpe.cin.jonas.ceplin.util

import br.ufpe.cin.jonas.ceplin.Event
import java.util.*


class AccelerationEvent(val x: Float, val y: Float, val z: Float) : Event {
    override val timestamp = Date()
}

