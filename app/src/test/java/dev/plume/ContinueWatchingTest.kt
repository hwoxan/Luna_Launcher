package dev.luna

import org.junit.Assert.assertEquals
import org.junit.Test

class ContinueWatchingTest {
    @Test fun newestFirstAndThreePerAppAhead() {
        // Netflix reports times for ten shows; Stremio's channel rows all get the time it was last opened.
        val netflix = (1..10).map { 100L - it to "netflix/$it" }
        val stremio = (1..4).map { 95L to "stremio/$it" }
        val nuvio = (1..2).map { 0L to "nuvio/$it" } // never opened from Luna
        val shown = newestPerApp(netflix + stremio + nuvio, 3) { it.substringBefore('/') }
        // Each app's three newest first, then the rest, newest first.
        assertEquals(
            listOf("netflix/1", "netflix/2", "netflix/3", "stremio/1", "stremio/2", "stremio/3", "nuvio/1", "nuvio/2",
                "netflix/4", "netflix/5", "stremio/4") + (6..10).map { "netflix/$it" },
            shown,
        )
    }

    @Test fun oneAppIsNotCapped() {
        val stremio = (1..10).map { 100L - it to "stremio/$it" }
        assertEquals(stremio.map { it.second }, newestPerApp(stremio, 3) { it.substringBefore('/') })
    }

    @Test fun busyAppCantPushOthersBack() {
        // Four newer Stremio shows, then one Netflix show: Netflix moves up to fourth.
        val items = (1..4).map { 100L - it to "stremio/$it" } + (90L to "netflix/1")
        assertEquals(listOf("stremio/1", "stremio/2", "stremio/3", "netflix/1", "stremio/4"), newestPerApp(items, 3) { it.substringBefore('/') })
    }

    @Test fun whatYouStartedComesBeforeSuggestionsWhateverTheUnit() {
        // Wholphin dates what is playing in seconds and its "next episode" suggestions in milliseconds. Sorted on the
        // raw numbers the suggestions win, though they are older (11:52 against 13:14).
        val rows = listOf(
            watchSortKey(1, 1_792_576_332_167) to "wholphin/suggestion-a",
            watchSortKey(1, 1_792_576_332_167) to "wholphin/suggestion-b",
            watchSortKey(0, 1_792_581_271) to "wholphin/ncis",
            watchSortKey(0, 1_791_758_381) to "wholphin/descendants",
            watchSortKey(0, 1_791_242_669) to "wholphin/coquin",
            watchSortKey(0, 1_791_237_906_401) to "moonfin/clochette", // another app, in milliseconds
        )
        val shown = newestPerApp(rows, 3) { it.substringBefore('/') }
        // Wholphin's fourth and fifth rows follow the other apps' front places.
        assertEquals(listOf("wholphin/ncis", "wholphin/descendants", "wholphin/coquin", "moonfin/clochette",
            "wholphin/suggestion-a", "wholphin/suggestion-b"), shown)
    }

    @Test fun suggestionsStillShowWhenAnAppHasNothingStarted() {
        val rows = listOf(watchSortKey(1, 1_792_576_332_167) to "netflix/next", watchSortKey(0, 1_792_581_271) to "wholphin/ncis")
        assertEquals(listOf("wholphin/ncis", "netflix/next"), newestPerApp(rows, 3) { it.substringBefore('/') })
    }

    @Test fun continueWatchingChannelsStartOnOthersOff() {
        assertEquals(true, channelOn("Continue Watching", "stremio/Continue Watching", emptySet()))
        assertEquals(false, channelOn("On Deck", "plex/On Deck", emptySet()))
        // Switched by the user, each way.
        assertEquals(true, channelOn("On Deck", "plex/On Deck", setOf("plex/On Deck")))
        assertEquals(false, channelOn("Continue watching", "stremio/Continue watching", setOf("stremio/Continue watching")))
    }
}
