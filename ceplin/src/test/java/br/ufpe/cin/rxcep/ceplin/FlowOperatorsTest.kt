package br.ufpe.cin.rxcep.ceplin

import br.ufpe.cin.rxcep.ceplin.test.EventStreamSimulator
import br.ufpe.cin.rxcep.ceplin.util.IntEvent
import org.junit.Test

/**
 * Tests of CEP operators related to Flow Management
 */
class FlowOperatorsTest {
    /**
     *
     * Tests
     *
     */

    /**
     * Verify return distinct Events from source
     */
    @Test
    fun distinctEvents() {
        val events = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(5),
                IntEvent(12),
                IntEvent(12)
        )

        val expected = listOf(
                IntEvent(10),
                IntEvent(5),
                IntEvent(12)
        )

        val simulator = EventStreamSimulator<IntEvent>()
        val output = simulator.simulate(events, ::distinctFunction)

        assert(expected == output)
    }

    /**
     * Verify two sources to give only Events existent only in first one
     */
    @Test
    fun exceptEvents() {
        val events1 = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(5),
                IntEvent(12),
                IntEvent(12)
        )

        val events2 = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(12),
                IntEvent(12)
        )

        val expected = listOf(
                IntEvent(5)
        )

        val simulator = EventStreamSimulator<IntEvent>()
        val output = simulator.simulate(events1, events2, ::exceptFunction)

        assert(expected == output)
    }

    /**
     * Verify two sources to give Events existent in both with no duplicates
     */
    @Test
    fun intersectEvents() {
        val events1 = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(5),
                IntEvent(12),
                IntEvent(12)
        )

        val events2 = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(12),
                IntEvent(12)
        )

        val expected = listOf(
                IntEvent(10),
                IntEvent(12)
        )

        val simulator = EventStreamSimulator<IntEvent>()
        val output = simulator.simulate(events1, events2, ::intersectFunction)

        assert(expected == output)
    }

    /**
     * Order Events in source by comparison
     */
    @Test
    fun orderEvents() {
        val events = listOf(
                IntEvent(10),
                IntEvent(11),
                IntEvent(5),
                IntEvent(1),
                IntEvent(12)
        )

        val expected = listOf(
                IntEvent(1),
                IntEvent(5),
                IntEvent(10),
                IntEvent(11),
                IntEvent(12)
        )

        val simulator = EventStreamSimulator<IntEvent>()
        val output = simulator.simulateCompare(events, ::orderByFunction)

        assert(expected == output)
    }

    /**
     * Order Events in source by comparison
     */
    @Test
    fun groupEvents() {
        val events = listOf(
                IntEvent(10),
                IntEvent(10),
                IntEvent(5),
                IntEvent(12),
                IntEvent(12)
        )

        val expected = mapOf<Int, List<IntEvent>>(
                Pair(10, listOf(
                        IntEvent(10),
                        IntEvent(10)
                )),
                Pair(5, listOf(
                        IntEvent(5)
                )),
                Pair(12, listOf(
                        IntEvent(12),
                        IntEvent(12)
                ))
        )

        val simulator = EventStreamSimulator<IntEvent>()
        val output = simulator.simulate(events, ::groupByFunction)

        assert(expected == output)
    }

    /**
     *
     * CEP using functions
     *
     */

    /**
     * Filter the distinct Events
     */
    private fun distinctFunction(stream: EventStream<IntEvent>): EventStream<IntEvent> {
        return stream.distinct()
    }

    /**
     * Executes Except operator
     */
    private fun exceptFunction(stream1: EventStream<IntEvent>, stream2: EventStream<IntEvent>): EventStream<IntEvent> {
        return stream1.not(stream2)
    }

    /**
     * Executes Intersect operator
     */
    private fun intersectFunction(stream1: EventStream<IntEvent>, stream2: EventStream<IntEvent>): EventStream<IntEvent> {
        return stream1.intersect(stream2)
    }

    /**
     * Executes OrderBy operator
     */
    private fun orderByFunction(stream: EventStream<IntEvent>): EventStream<List<IntEvent>> {
        return stream.orderBy { event ->
            event.value
        }
    }

    /**
     * Executes GroupBy operator
     */
    private fun groupByFunction(stream: EventStream<IntEvent>): EventStream<Map<Int, List<IntEvent>>> {
        return stream.groupBy { event ->
            event.value
        }
    }
}
