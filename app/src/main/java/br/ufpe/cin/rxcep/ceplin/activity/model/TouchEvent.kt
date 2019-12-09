package br.ufpe.cin.rxcep.ceplin.activity.model

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class TouchEvent(val x: Float, val y: Float) : Event {
    override val timestamp = Date()
}

