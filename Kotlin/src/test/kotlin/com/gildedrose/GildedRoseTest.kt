package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Test

class GildedRoseTest {

    @Test
    fun foo() {
        val items = arrayOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun lower() {
        val items = arrayOf(Item("foo", 1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
        assertEquals(1, app.items[0].quality)
    }

    @Test
    fun qualityDegradesTwice() {
        val items = arrayOf(Item("foo", 1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(7, app.items[0].quality)
    }

    @Test
    fun neverNegativeQuality() {
        val items = arrayOf(Item("foo", 1, 1))
        val app = GildedRose(items)
        app.updateQuality()
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testIncreaseQualityOfAgedBrie() {
        val items = arrayOf(Item("Aged Brie", 1, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun testQualityNotMoreThan50() {
        // 假设item初始值不允许 > 50
        val items = arrayOf(Item("Aged Brie", 3, 49))
        val app = GildedRose(items)
        app.updateQuality()
        app.updateQuality()
        assertEquals(1, app.items[0].sellIn)
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun legendarySulfuras() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, app.items[0].sellIn)
        assertEquals(2, app.items[0].quality)
        app.updateQuality()
        assertEquals(1, app.items[0].sellIn)
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun testIncreaseQualityBackstagePasses() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 30, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(29, app.items[0].sellIn)
        assertEquals(3, app.items[0].quality)
        app.updateQuality()
        assertEquals(28, app.items[0].sellIn)
        assertEquals(4, app.items[0].quality)
    }

    @Test
    fun testIncreaseQualityFrom6To10BackstagePasses() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(4, app.items[0].quality)
        app.updateQuality()
        assertEquals(8, app.items[0].sellIn)
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun testIncreaseQualityFrom1To5BackstagePasses() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].sellIn)
        assertEquals(5, app.items[0].quality)
        app.updateQuality()
        assertEquals(3, app.items[0].sellIn)
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun testIncreaseQualityFrom0To1BackstagePasses() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
        assertEquals(5, app.items[0].quality)
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

}


