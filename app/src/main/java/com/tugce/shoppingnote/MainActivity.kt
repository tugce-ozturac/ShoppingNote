package com.tugce.shoppingnote

import AddItemScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tugce.shoppingnote.model.Item
import com.tugce.shoppingnote.screens.DetailScreen
import com.tugce.shoppingnote.screens.ItemList
import com.tugce.shoppingnote.ui.theme.ShoppingNoteTheme
import com.tugce.shoppingnote.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            ShoppingNoteTheme {
                Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = "list_screen"
                    ) {
                        // LIST SCREEN
                        composable("list_screen") {
                            viewModel.getItemList()
                            val itemList by viewModel.itemList

                            ItemList(itemList = itemList, navController = navController)
                        }

                        // ADD ITEM SCREEN
                        composable("add_item_screen") {
                            AddItemScreen { item: Item ->
                                viewModel.saveItem(item)
                                navController.navigate("list_screen")
                            }
                        }

                        // DETAIL SCREEN
                        composable(
                            "details_screen/{itemId}",
                            arguments = listOf(
                                navArgument("itemId") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val itemIdString = backStackEntry.arguments?.getString("itemId")
                            viewModel.getItem(itemIdString?.toIntOrNull() ?: 1)
                            val selectedItem by viewModel.selectedItem

                            selectedItem?.let { item ->
                                DetailScreen(item = item) {
                                    viewModel.deleteItem(item)
                                    navController.navigate("list_screen")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
