package dev.luna

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FittingLabelTest {
    @Test fun aNameThatFitsHasNothingToScroll() {
        assertEquals(0f, labelOverflow(80f, 100f), 0f)
        assertEquals(0f, labelOverflow(100f, 100f), 0f)
    }

    @Test fun aNameTooWideScrollsByTheExcess() {
        assertEquals(60f, labelOverflow(160f, 100f), 0f)
    }

    @Test fun theSpeedIsSteadyButTheWayNeverTakesLessThanAMoment() {
        assertEquals(2000, marqueeCycle(overflowPx = 56f, pxPerSecond = 28f).scrollMs)
        assertEquals(900, marqueeCycle(overflowPx = 4f, pxPerSecond = 28f).scrollMs)
    }

    @Test fun aRoundWaitsScrollsWaitsAndComesBack() {
        val c = MarqueeCycle(pauseMs = 1000, scrollMs = 2000)
        val overflow = 60f
        assertEquals(6000, c.totalMs)
        assertEquals("starts still", 0f, marqueeShift(0, overflow, c), 0f)
        assertEquals("waits at the start", 0f, marqueeShift(999, overflow, c), 0f)
        assertEquals("half way, half the way", 30f, marqueeShift(2000, overflow, c), 0.01f)
        assertEquals("at the end", overflow, marqueeShift(3000, overflow, c), 0.01f)
        assertEquals("waits at the end", overflow, marqueeShift(3500, overflow, c), 0.01f)
        assertEquals("half way back", 30f, marqueeShift(5000, overflow, c), 0.01f)
        assertEquals("back at the start, ready to wait", 0f, marqueeShift(5999, overflow, c), 0.5f)
        assertEquals("and the next round is the same", marqueeShift(2000, overflow, c), marqueeShift(2000 + 6000, overflow, c), 0f)
    }

    @Test fun itStartsAndStopsSoftly() {
        // Eased: in the first tenth of the way it moves far less than a tenth of the distance.
        val c = MarqueeCycle(pauseMs = 0, scrollMs = 2000)
        assertTrue(marqueeShift(200, 100f, c) < 5f)
        assertTrue(marqueeShift(1800, 100f, c) > 95f)
    }

    @Test fun itNeverLeavesItsRange() {
        val c = MarqueeCycle(pauseMs = 800, scrollMs = 1500)
        for (t in 0 until c.totalMs step 37) {
            val s = marqueeShift(t, 42f, c)
            assertTrue("shift $s at $t", s in 0f..42f)
        }
    }
}
