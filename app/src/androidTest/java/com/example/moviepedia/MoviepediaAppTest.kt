package com.example.moviepedia

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import com.example.moviepedia.model.MovieDataSource
import com.example.moviepedia.ui.navigation.Screen
import com.example.moviepedia.ui.theme.MoviepediaTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviepediaAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navHostController: TestNavHostController

    @Before
    fun setUp(){
        composeTestRule.setContent {
            MoviepediaTheme {
                navHostController = TestNavHostController(LocalContext.current)
                navHostController.navigatorProvider.addNavigator(ComposeNavigator())
                MoviepediaApp(navHostController = navHostController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination(){
        navHostController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigateToDetailWithData(){
        composeTestRule.onNodeWithTag("MovieList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[9].title).performClick()
        navHostController.assertCurrentRouteName(Screen.DetailMovies.route)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[9].title).assertIsDisplayed()
    }


    @Test
    fun navHost_onWacthlistClick_shouldExistsInWatchlistPage() {
        composeTestRule.onNodeWithTag("MovieList").performScrollToIndex(3)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[3].title).performClick()
        navHostController.assertCurrentRouteName(Screen.DetailMovies.route)
        composeTestRule.onNodeWithTag("add_remove_watchlist").performClick()
        Espresso.pressBack()
        navHostController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_watchlist).performClick()
        navHostController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[3].title).assertIsDisplayed()
    }

    @Test
    fun navHost_removeWatchlist_shouldShowEmptyWatchlist() {
        composeTestRule.onNodeWithTag("MovieList").performScrollToIndex(3)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[3].title).performClick()
        navHostController.assertCurrentRouteName(Screen.DetailMovies.route)
        composeTestRule.onNodeWithTag("add_remove_watchlist").performClick()
        Espresso.pressBack()
        navHostController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_watchlist).performClick()
        navHostController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithText(MovieDataSource.movieList[3].title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("watchlist_icon").performClick()
        composeTestRule.onNodeWithStringId(R.string.empty_watchlist).assertIsDisplayed()
    }


    @Test
    fun navHost_bottomNavigation_working(){
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navHostController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_watchlist).performClick()
        navHostController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navHostController.assertCurrentRouteName(Screen.Profile.route)
    }

    @Test
    fun searchItem_shouldShowResult() {
        composeTestRule.onNodeWithStringId(R.string.search_placeholder).performTextInput("Spirited")
        composeTestRule.onNodeWithText("Spirited").assertIsDisplayed()
    }

    @Test
    fun searchItem_shouldShowEmptyData() {
        val wrongQuery = "Jujutsu"
        composeTestRule.onNodeWithStringId(R.string.search_placeholder).performTextInput(wrongQuery)
        composeTestRule.onNodeWithTag("empty_data").assertIsDisplayed()
    }
}