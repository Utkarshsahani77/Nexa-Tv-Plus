package com.nexatv.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.nexatv.app.ui.addons.AddonsScreen
import com.nexatv.app.ui.addons.AddonsViewModel
import com.nexatv.app.ui.addons.AddonsViewModelFactory
import com.nexatv.app.ui.addons.CatalogScreen
import com.nexatv.app.ui.addons.MetaScreen
import com.nexatv.app.ui.details.DetailsScreen
import com.nexatv.app.ui.home.HomeScreen
import com.nexatv.app.ui.home.HomeViewModel
import com.nexatv.app.ui.home.HomeViewModelFactory
import com.nexatv.app.ui.livetv.LiveTvScreen
import com.nexatv.app.ui.livetv.LiveTvViewModel
import com.nexatv.app.ui.movies.MoviesScreen
import com.nexatv.app.ui.movies.MoviesViewModel
import com.nexatv.app.ui.movies.MoviesViewModelFactory
import com.nexatv.app.ui.player.HlsPlayer
import com.nexatv.app.ui.search.SearchScreen
import com.nexatv.app.ui.search.SearchViewModel
import com.nexatv.app.ui.search.SearchViewModelFactory
import com.nexatv.app.ui.series.SeriesScreen
import com.nexatv.app.ui.series.SeriesViewModel
import com.nexatv.app.ui.series.SeriesViewModelFactory
import com.nexatv.app.ui.settings.SettingsScreen
import com.nexatv.app.ui.splash.SplashScreen
import com.nexatv.app.ui.stalker.StalkerScreen
import com.nexatv.app.ui.stalker.StalkerViewModel
import com.nexatv.app.ui.stalker.StalkerViewModelFactory
import com.nexatv.app.ui.theme.NexaTVTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as NexaApp

        setContent {
            NexaTVTheme {
                MainNavHost(app)
            }
        }
    }
}

data class BottomItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun NexaBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomItem("home", "Home", Icons.Filled.Home),
        BottomItem("livetv", "Live TV", Icons.Filled.LiveTv),
        BottomItem("movies", "Movies", Icons.Filled.Movie),
        BottomItem("series", "Series", Icons.Filled.Tv),
        BottomItem("search", "Search", Icons.Filled.Search),
        BottomItem("library", "Library", Icons.Filled.Favorite),
        BottomItem("settings", "Settings", Icons.Filled.Settings)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                    Icon(item.icon, contentDescription = item.label)
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}

@Composable
fun MainNavHost(app: NexaApp) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        "home",
        "livetv",
        "movies",
        "series",
        "search",
        "library",
        "settings"
    )

    androidx.compose.material3.Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NexaBottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("home")
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash") {
                SplashScreen(
                    onFinish = {
                        navController.navigate("home") {
                            popUpTo("splash")
                        }
                    }
                )
            }

            composable("home") {
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(app.repository)
                )

                HomeScreen(
                    viewModel = viewModel,
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("livetv") {
                val viewModel: LiveTvViewModel = viewModel()

                LiveTvScreen(
                    viewModel = viewModel,
                    onPlayChannel = { channel ->
                        navController.navigate("player/" + channel.url)
                    }
                )
            }

            composable("movies") {
                val viewModel: MoviesViewModel = viewModel(
                    factory = MoviesViewModelFactory(app.repository)
                )

                MoviesScreen(
                    viewModel = viewModel,
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("series") {
                val viewModel: SeriesViewModel = viewModel(
                    factory = SeriesViewModelFactory(app.repository)
                )

                SeriesScreen(
                    viewModel = viewModel,
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("search") {
                val viewModel: SearchViewModel = viewModel(
                    factory = SearchViewModelFactory(app.repository)
                )

                SearchScreen(
                    viewModel = viewModel,
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("library") {
                LibraryScreen(
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("settings") {
                SettingsScreen(
                    onOpenAddons = {
                        navController.navigate("addons")
                    },
                    onOpenStalker = {
                        navController.navigate("stalker")
                    }
                )
            }

            composable(
                route = "details/{itemId}",
                arguments = listOf(
                    navArgument("itemId") { type = NavType.StringType }
                )
            ) { entry ->
                val itemId = entry.arguments?.getString("itemId") ?: ""

                DetailsScreen(
                    itemId = itemId,
                    onPlay = { url ->
                        navController.navigate("player/" + url)
                    },
                    onDownload = { url, title ->
                        navController.navigate("player/" + url)
                    }
                )
            }

            composable(
                route = "player/{url}",
                arguments = listOf(
                    navArgument("url") { type = NavType.StringType }
                )
            ) { entry ->
                val url = entry.arguments?.getString("url") ?: ""

                HlsPlayer(
                    streamUrl = url,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable("addons") {
                val viewModel: AddonsViewModel = viewModel(
                    factory = AddonsViewModelFactory(
                        app.stremioRepository,
                        app.apiKeys
                    )
                )

                AddonsScreen(
                    viewModel = viewModel,
                    onOpenCatalog = { addonId ->
                        navController.navigate("catalog/" + addonId)
                    }
                )
            }

            composable(
                route = "catalog/{addonId}",
                arguments = listOf(
                    navArgument("addonId") { type = NavType.StringType }
                )
            ) { entry ->
                val addonId = entry.arguments?.getString("addonId") ?: ""

                val viewModel: AddonsViewModel = viewModel(
                    factory = AddonsViewModelFactory(
                        app.stremioRepository,
                        app.apiKeys
                    )
                )

                CatalogScreen(
                    viewModel = viewModel,
                    addonId = addonId,
                    onOpenMeta = { metaId ->
                        navController.navigate("meta/" + metaId)
                    }
                )
            }

            composable(
                route = "meta/{metaId}",
                arguments = listOf(
                    navArgument("metaId") { type = NavType.StringType }
                )
            ) { entry ->
                val metaId = entry.arguments?.getString("metaId") ?: ""

                val viewModel: AddonsViewModel = viewModel(
                    factory = AddonsViewModelFactory(
                        app.stremioRepository,
                        app.apiKeys
                    )
                )

                MetaScreen(
                    viewModel = viewModel,
                    metaId = metaId,
                    onPlay = { url ->
                        navController.navigate("player/" + url)
                    }
                )
            }

            composable("stalker") {
                val viewModel: StalkerViewModel = viewModel(
                    factory = StalkerViewModelFactory(app.repository)
                )

                StalkerScreen(
                    viewModel = viewModel,
                    onPlayChannel = { channel ->
                        navController.navigate("player/" + channel.url)
                    }
                )
            }
        }
    }
}
