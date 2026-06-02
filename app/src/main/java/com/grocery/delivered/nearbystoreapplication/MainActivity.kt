package com.grocery.delivered.nearbystoreapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.grocery.delivered.nearbystoreapplication.domain.StoreModel
import com.grocery.delivered.nearbystoreapplication.screens.dashboard.BottomBar
import com.grocery.delivered.nearbystoreapplication.screens.dashboard.DashboardScreen
import com.grocery.delivered.nearbystoreapplication.screens.dashboard.TopBar
import com.grocery.delivered.nearbystoreapplication.screens.map.MapScreen
import com.grocery.delivered.nearbystoreapplication.screens.results.ResultList
import com.grocery.delivered.nearbystoreapplication.ui.theme.NearByStoreApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearByStoreApplicationTheme {
             MainApp()
            }
        }
    }
}


sealed class Screens {
    data object Dashboard : Screens()
    data class  Results(val id: String, val title: String): Screens()
    data class Map(val storeModel: StoreModel): Screens()
}


@Composable
fun MainApp() {

    val systemUIController = rememberSystemUiController()

    systemUIController.setSystemBarsColor(
        color = colorResource(R.color.white)
    )

    val backStack = remember {
        mutableStateListOf<Screens>(
            Screens.Dashboard
        )
    }

    val currentScreen = backStack.last()

    fun popBackStack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    BackHandler(enabled = backStack.size > 1) {
        popBackStack()
    }

    when (val screen = currentScreen) {

        Screens.Dashboard -> {

            DashboardScreen(
                onCategoryClick = { id, title ->

                    backStack.add(
                        Screens.Results(
                            id = id,
                            title = title
                        )
                    )
                }
            )
        }

        is Screens.Results -> {

            ResultList(
                id = screen.id,
                title = screen.title,
                onBackClick = {
                    popBackStack()
                },
                onStoreClick = { store ->
                    backStack.add(Screens.Map(store))
                }
            )
        }

        is Screens.Map->{
            MapScreen(storeModel =screen.storeModel)
        }
    }
}
