package com.example.searchcinema.ui.presintation.mock

import com.example.domain.ui.presintashion.feature.discover.model.Film

object Mock {
    val demoFilms = listOf(
        Film(
            id = 1,
            imageUrl = "https://avatars.mds.yandex.net/i?id=68ca9d6424f61e966a6ff59735d665bc_l-12583108-images-thumbs&n=13",
            film = "Душа",
            description = "",
            genre = listOf(),
            rate = 3.4,
            time = 152,
            year = 2023,
        ),
        Film(
            id = 2,
            imageUrl = "https://img.rl0.ru/80d9fa272f14b7d84002139535299170/1320x750/https/tv.rambler.ru/epg/pic/4254467",
            film = "Достать ножи",
            description = "",
            genre = listOf(),
            rate = 3.4,
            time = 152,
            year = 2008,
        ),
        Film(
            id = 3,
            imageUrl = "https://cdn.culture.ru/images/f8d2978d-d2ce-502a-bb42-41e2428bc7a3",
            film = "Вперед",
            description = "",
            genre = listOf(),
            rate = 3.4,
            time = 152,
            year = 2024,
        ),
        Film(
            id = 4,
            imageUrl = "https://avatars.mds.yandex.net/get-vthumb/3919086/b1f0c44b58e18b4813cca3a22c454429/800x450",
            film = "Мулан",
            description = "",
            genre = listOf(),
            rate = 3.4,
            time = 152,
            year = 2017,
        ),
    )

}