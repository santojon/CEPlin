package br.ufpe.cin.rxcep.ceplin.activity.model

import br.ufpe.cin.rxcep.ceplin.Event
import java.util.*

class LightEvent(val lx: Float) : Event {
    override val timestamp = Date()
}

