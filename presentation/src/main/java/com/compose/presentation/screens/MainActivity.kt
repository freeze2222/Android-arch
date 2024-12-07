package com.compose.presentation.screens

import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.compose.data.utils.Constants
import com.compose.presentation.composable.NavGraph
import com.compose.presentation.theme.Grey
import com.compose.presentation.theme.Purple40
import com.compose.presentation.theme.Purple80
import com.compose.presentation.theme.PurpleGrey40
import com.compose.presentation.theme.composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        window.insetsController?.hide(WindowInsets.Type.statusBars())
        setContent {
            composeTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val coroutineScope = rememberCoroutineScope()
                var currentItem = Constants.NavDestinations.CATS_SCREEN
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(drawerContainerColor = Grey) {
                            for (i in Constants.NavDestinations.entries) {
                                NavigationDrawerItem({i.toLocalizedName()},
                                    modifier = Modifier.padding(top = 10.dp),
                                    selected = currentItem == i,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = NavigationDrawerItemDefaults.colors(
                                        selectedContainerColor = Purple40,
                                        unselectedContainerColor = PurpleGrey40,
                                        selectedTextColor = Color.White,
                                        unselectedTextColor = Color.White
                                    ),
                                    onClick = {
                                        currentItem = i
                                        navController.navigate(i.name)
                                        coroutineScope.launch {
                                            drawerState.close()
                                        }
                                    }, badge = {
                                        Text(i.toLocalizedName())
                                    })
                            }
                        }
                    },
                ) {
                    Scaffold (
                        topBar = {
                            Row(horizontalArrangement = Arrangement.Start) {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Outlined.List,
                                        contentDescription = "",
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    ) { contentPadding ->
                        NavGraph(navController = navController, contentPadding)
                    }

                }

            }
        }
    }
}
