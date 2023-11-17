package com.example.moviepedia.model

import com.example.moviepedia.R

object MovieDataSource {
    val movieList = listOf(
        Movie(
            id = 1,
            title = "Oppenheimer",
            image = R.drawable.ic_launcher_background,
            duration = "3h",
            releasedYear = 2023,
            rating = 8.5,
            director = "Christopher Nolan",
            storyline = "A dramatization of the life story of J. Robert Oppenheimer, the physicist who had a large hand in the development of the atomic bomb, thus helping end World War 2. We see his life from university days all the way to post-WW2, where his fame saw him embroiled in political machinations."
        ),
        Movie(
            id = 2,
            title = "The Shawshank Redemption",
            image = R.drawable.ic_launcher_background,
            duration = "2h 22m",
            releasedYear = 1994,
            rating = 9.3,
            director = "Frank Darabont",
            storyline = "Chronicles the experiences of a formerly successful banker as a prisoner in the gloomy jailhouse of Shawshank after being found guilty of a crime he did not commit. The film portrays the man's unique way of dealing with his new, torturous life; along the way he befriends a number of fellow prisoners, most notably a wise long-term inmate named Red"
        ),
        Movie(
            id = 3,
            title = "The Godfather",
            image = R.drawable.ic_launcher_background,
            duration = "2h 55m",
            releasedYear = 1972,
            rating = 9.2,
            director = "Francis Ford Coppola",
            storyline = "The Godfather \"Don\" Vito Corleone is the head of the Corleone mafia family in New York. He is at the event of his daughter's wedding. Michael, Vito's youngest son and a decorated WW II Marine is also present at the wedding. Michael seems to be uninterested in being a part of the family business. Vito is a powerful man, and is kind to all those who give him respect but is ruthless against those who do not. But when a powerful and treacherous rival wants to sell drugs and needs the Don's influence for the same, Vito refuses to do it. What follows is a clash between Vito's fading old values and the new ways which may cause Michael to do the thing he was most reluctant in doing and wage a mob war against all the other mafia families which could tear the Corleone family apart."
        ),
        Movie(
            id = 4,
            title = "The Dark Knight",
            image = R.drawable.ic_launcher_background,
            duration = "2h 32m",
            releasedYear = 2008,
            rating = 9.0,
            director = "Christopher Nolan",
            storyline = "Set within a year after the events of Batman Begins (2005), Batman, Lieutenant James Gordon, and new District Attorney Harvey Dent successfully begin to round up the criminals that plague Gotham City, until a mysterious and sadistic criminal mastermind known only as \"The Joker\" appears in Gotham, creating a new wave of chaos. Batman's struggle against The Joker becomes deeply personal, forcing him to \"confront everything he believes\" and improve his technology to stop him. A love triangle develops between Bruce Wayne, Dent, and Rachel Dawes."
        ),
        Movie(
            id = 5,
            title = "12 Angry Men",
            image = R.drawable.ic_launcher_background,
            duration = "1h 36m",
            releasedYear = 1957,
            rating = 9.0,
            director = "Sidney Lumet",
            storyline = "The defense and the prosecution have rested, and the jury is filing into the jury room to decide if a young man is guilty or innocent of murdering his father. What begins as an open-and-shut case of murder soon becomes a detective story that presents a succession of clues creating doubt, and a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, AND each other. Based on the play, all of the action takes place on the stage of the jury room"
        ),
        Movie(
            id = 6,
            title = "Forrest Gump",
            image = R.drawable.ic_launcher_background,
            duration = "2h 22m",
            releasedYear = 1994,
            rating = 8.8,
            director = "Robert Zemeckis",
            storyline = "Forrest Gump is a simple man with a low I.Q. but good intentions. He is running through childhood with his best and only friend Jenny. His 'mama' teaches him the ways of life and leaves him to choose his destiny. Forrest joins the army for service in Vietnam, finding new friends called Dan and Bubba, he wins medals, creates a famous shrimp fishing fleet, inspires people to jog, starts a ping-pong craze, creates the smiley, writes bumper stickers and songs, donates to people and meets the president several times. However, this is all irrelevant to Forrest who can only think of his childhood sweetheart Jenny Curran, who has messed up her life. Although in the end all he wants to prove is that anyone can love anyone."
        ),
        Movie(
            id = 7,
            title = "Fight Club",
            image = R.drawable.ic_launcher_background,
            duration = "2h 19m",
            releasedYear = 1999,
            rating = 8.8,
            director = "David Fincher",
            storyline = "A nameless first person narrator (Edward Norton) attends support groups in attempt to subdue his emotional state and relieve his insomniac state. When he meets Marla (Helena Bonham Carter), another fake attendee of support groups, his life seems to become a little more bearable. However when he associates himself with Tyler (Brad Pitt) he is dragged into an underground fight club and soap making scheme. Together the two men spiral out of control and engage in competitive rivalry for love and power."
        ),
        Movie(
            id = 8,
            title = "The Matrix",
            image = R.drawable.ic_launcher_background,
            duration = "2h 16m",
            releasedYear = 1999,
            rating = 8.7,
            director = "Lana Wachowski and Lilly Wachowski",
            storyline = "Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. As a rebel against the machines, Neo must confront the agents: super-powerful computer programs devoted to stopping Neo and the entire human rebellion."
        ),
        Movie(
            id = 9,
            title = "Spider-Man: Across the Spider-Verse",
            image = R.drawable.ic_launcher_background,
            duration = "2h 20m",
            releasedYear = 2023,
            rating = 8.7,
            director = "Joaquim Dos Santos, Kemp Powers, and Justin K. Thompson",
            storyline = "Miles Morales returns for the next chapter of the Oscar®-winning Spider-Verse saga, an epic adventure that will transport Brooklyn's full-time, friendly neighborhood Spider-Man across the Multiverse to join forces with Gwen Stacy and a new team of Spider-People to face off with a villain more powerful than anything they have ever encountered."
        ),
        Movie(
            id = 10,
            title = "Spirited Away",
            image = R.drawable.ic_launcher_background,
            duration = "2h 16m",
            releasedYear = 2001,
            rating = 8.6,
            director = "Hayao Miyazaki",
            storyline = "The fanciful adventures of a ten-year-old girl named Chihiro, who discovers a secret world when she and her family get lost and venture through a hillside tunnel. When her parents undergo a mysterious transformation, Chihiro must fend for herself as she encounters strange spirits, assorted creatures and a grumpy sorceress who seeks to prevent her from returning to the human world."
        ),
    )


    val recommendationMovieList = movieList.shuffled()
}