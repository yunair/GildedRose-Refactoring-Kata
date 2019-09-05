package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            when {
                items[i].name == "Aged Brie" -> increaseQuality(i)
                items[i].name == "Backstage passes to a TAFKAL80ETC concert" -> handleBackstagePasses(i)
                else -> decreaseQuality(i)
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn >= 0) {
                continue
            }

            if (items[i].name == "Aged Brie") {
                increaseQuality(i)
            } else {
                if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                    items[i].quality = 0
                } else {
                    decreaseQuality(i)
                }
            }
        }
    }


    private fun increaseQuality(i: Int) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1
        }
    }

    private fun decreaseQuality(index: Int) {
        if (items[index].quality <= 0) {
            return
        }

        if (items[index].name != "Sulfuras, Hand of Ragnaros") {
            items[index].quality = items[index].quality - 1
        }
    }

    private fun handleBackstagePasses(index: Int) {
        if (items[index].quality >= 50) {
            return
        }
        increaseQuality(index)

        if (items[index].sellIn < 11) {
            increaseQuality(index)
        }

        if (items[index].sellIn < 6) {
            increaseQuality(index)
        }
    }

}

