package com.example.grapup.data

import java.text.SimpleDateFormat
import java.util.*

object FakeData {

    private fun randomFloat(min: Int, max: Int): Float {
        return Random().nextFloat() * (max - min) + min
    }

    private fun randBetween(start: Int, end: Int): Int {
        return start + Math.round(Math.random() * (end - start)).toInt()
    }

    private fun randomDate(): String {
        val dfDateTime = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val year: Int =
            randBetween(2015, 2021) // Here you can set Range of years you need

        val month: Int = randBetween(0, 11)
        val hour: Int =
            randBetween(9, 22) // Hours will be displayed in between 9 to 22

        val min: Int = randBetween(0, 59)
        val sec: Int = randBetween(0, 59)

        val gc = GregorianCalendar(year, month, 1)
        val day: Int = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH))

        gc[year, month, day, hour, min] = sec

        return dfDateTime.format(gc.time)
    }

    private val routesNamesWithImages = listOf(
        "Przez las" to "https://drytooling.com.pl/images/artykuly/2021/prawy-filar-strzelecka-turnia-plytka.jpg",
        "Ryki marynarskie" to "https://drytooling.com.pl/images/artykuly/2021/korosadowicz-zolta-sciana-tatry010.jpg",
        "Filar wiewiórek" to "https://drytooling.com.pl/images/artykuly/2021/prawy-filar-strzelecka-turnia-crux.jpg",
        "Kominek adeptów kleszcze" to "https://drytooling.com.pl/images/artykuly/2021/crux-poludniowy-filar-koziego-wierchu.jpg",
        "Kominek adeptów z trawersem" to "https://drytooling.com.pl/images/artykuly/2021/prawy-filar-strzelecka-turnia-gran-wspinaczka.jpg",
        "Wariant R" to "https://drytooling.com.pl/images/artykuly/2021/poludniowy-filar-koziego-wierchu-zaciecie-marcin.jpg",
        "Zacięcie przy schodach" to "https://drytooling.com.pl/images/artykuly/2021/krzysiek-okon-droga-motyki-maly-lodowy-szczyt.jpg",
        "Pod schodami" to "https://climber.rafalantoniewski.pl/wp-content/uploads/2020/03/SDC10032-1.jpg",
        "Stara droga" to "https://wspinanie.pl/wp-content/uploads/2010/12/23jasper-tatry-zakrzowek1.jpg",
        "Filarek do platformy" to "https://8a.pl/8academy/wp-content/uploads/2020/04/0-cover-obyczaje-w-skalach-1200x900.jpg"
    )

    private fun randomRoute(index: Int) = Route(
        id = UUID.randomUUID().toString(),
        description = routesNamesWithImages[index].first,
        difficulty = Difficulty.values().random(),
        rockType = listOf("grafit", "piaskowiec", "wapień").random(),
        heightMeters = randomFloat(50, 140),
        belaying = Belaying.values().random(),
        rating = randomFloat(0, 5),
        type = Type.values().random(),
        covered = Random().nextBoolean(),
        author = users.random(),
        creationDate = randomDate(),
        surrounding = (0..2).map { surroundingTags.random() }.distinct(),
        photoUrl = routesNamesWithImages[index].second
    )

    private val surroundingTags = listOf(
        "las iglasty",
        "restauracja",
        "last liściasty",
        "jezioro",
        "gniazdo sokoła",
        "łąka",
        "hotel",
        "stok",
        "wyciąg"
    )

    val users = listOf(
        User(id = "user1", name = "Damian Kulus"),
        User(id = "user2", name = "Mikołaj Korbanek"),
        User(id = "user3", name = "Jakub Adamczewski"),
    )

    val routes = (routesNamesWithImages.indices).map { randomRoute(it) }
}
