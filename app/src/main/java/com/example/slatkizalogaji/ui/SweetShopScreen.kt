package com.example.slatkizalogaji.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.slatkizalogaji.R
import com.example.slatkizalogaji.data.DataSource
import com.example.slatkizalogaji.data.Item
import com.example.slatkizalogaji.data.Product
import com.example.slatkizalogaji.data.User
import com.example.slatkizalogaji.ui.screen.CartScreen
import com.example.slatkizalogaji.ui.screen.ChangePasswordScreen
import com.example.slatkizalogaji.ui.screen.ChangeProfileScreen
import com.example.slatkizalogaji.ui.screen.ContactUsScreen
import com.example.slatkizalogaji.ui.screen.HomeScreen
import com.example.slatkizalogaji.ui.screen.LoginScreen
import com.example.slatkizalogaji.ui.screen.NotificationsScreen
import com.example.slatkizalogaji.ui.screen.OrderScreen
import com.example.slatkizalogaji.ui.screen.ProductDetailsScreen
import com.example.slatkizalogaji.ui.screen.ProductsScreen
import com.example.slatkizalogaji.ui.screen.ProfileScreen
import com.example.slatkizalogaji.ui.theme.SlatkiZalogajiTheme
import com.example.slatkizalogaji.ui.theme.fancyTextStyle

enum class SweetShopScreen {
    Login,
    Home,
    Products,
    Cart,
    Profile,
    ProductDetails,
    ChangePassword,
    ChangeProfile,
    ContactUs,
    Notifications,
    Order
}

@Composable
fun SweetShopAppBar(opened: MutableState<Boolean>, navController: NavController) {
    Row(horizontalArrangement = Arrangement.spacedBy((-20).dp, Alignment.End)) {
        if (!opened.value) {
            Text(
                text = "Slatki Zalogaji",
                style = fancyTextStyle.copy(fontSize = 48.sp, color = Color.White),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navController.navigate(SweetShopScreen.Home.name) }
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { opened.value = true }, modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_closed),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(32.dp))
        } else {
            IconButton(
                onClick = { navController.navigate(SweetShopScreen.ContactUs.name) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { navController.navigate(SweetShopScreen.Products.name) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cake),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { navController.navigate(SweetShopScreen.Notifications.name) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notify),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { navController.navigate(SweetShopScreen.Cart.name) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { navController.navigate(SweetShopScreen.Profile.name) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
            IconButton(onClick = { opened.value = false }, modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_open),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun SweetShopApp() {
    ProductInit()

    val navController = rememberNavController()
    val viewModel = SweetShopViewModel()
    val appBarOpened = remember { mutableStateOf(false) }

    val currentProductIndex = remember { mutableIntStateOf(0) }
    val currentProduct = remember { mutableStateOf(DataSource.products[0]) }
    val showLeftProductButton = remember { mutableStateOf(false) }
    val showRightProductButton = remember { mutableStateOf(true) }

    val currentProductInDetails: MutableState<Product?> = remember { mutableStateOf(null) }

    val loggedInUser: MutableState<User?> = remember { mutableStateOf(null) }
    val cart: MutableState<List<Item>> = remember { mutableStateOf(emptyList()) }

    val orderItems: MutableState<List<Item>> = remember { mutableStateOf(emptyList()) }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SweetShopScreen.Login.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .paint(
                    painterResource(id = R.drawable.background_image),
                    contentScale = ContentScale.Crop
                )
        ) {

            composable(SweetShopScreen.Login.name) {
                LoginScreen(
                    attemptLogin = { username, password ->
                        val attemptValid = viewModel.credentialsValid(username, password)
                        if (attemptValid) {
                            viewModel.setLoggedInUser(username)
                            loggedInUser.value = viewModel.getLoggedInUser()
                            cart.value = viewModel.getLoggedInUser().cart
                            navController.navigate(SweetShopScreen.Home.name)
                        }

                        attemptValid
                    },
                )
            }

            composable(SweetShopScreen.Home.name) {
                HomeScreen(
                    navController,
                    appBarOpened,
                    currentProduct,
                    onClickPrev = {
                        var newIndex = 0
                        if (currentProductIndex.intValue != 0) {
                            newIndex = currentProductIndex.intValue - 1
                        }

                        currentProductIndex.intValue = newIndex
                        currentProduct.value = DataSource.products[newIndex]
                        showLeftProductButton.value = newIndex != 0
                        showRightProductButton.value = newIndex != DataSource.products.size - 1
                    },
                    onClickNext = {
                        var newIndex = DataSource.products.size - 1
                        if (currentProductIndex.intValue != DataSource.products.size - 1) {
                            newIndex = currentProductIndex.intValue + 1
                        }

                        currentProductIndex.intValue = newIndex
                        currentProduct.value = DataSource.products[newIndex]
                        showLeftProductButton.value = newIndex != 0
                        showRightProductButton.value = newIndex != DataSource.products.size - 1
                    },
                    onClickProduct = {
                        currentProductInDetails.value = currentProduct.value
                        navController.navigate(SweetShopScreen.ProductDetails.name)
                    },
                    showLeftButton = showLeftProductButton,
                    showRightButton = showRightProductButton
                )
            }

            composable(SweetShopScreen.Products.name) {
                ProductsScreen(
                    navController,
                    appBarOpened,
                    DataSource.products,
                    navigateToProductPage = { product ->
                        currentProductInDetails.value = product
                        navController.navigate(SweetShopScreen.ProductDetails.name)
                    }
                )
            }

            composable(SweetShopScreen.Cart.name) {
                CartScreen(
                    navController,
                    appBarOpened,
                    cart,
                    setProductQuantity = { productName, quantity ->
                        viewModel.setProductQuantity(productName, quantity)
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                    },
                    goToProductDetailPage = { productName ->
                        currentProductInDetails.value = viewModel.getProduct(productName)
                        navController.navigate(SweetShopScreen.ProductDetails.name)
                    },
                    completeOrder = {
                        viewModel.completeOrder()
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                    }
                )
            }

            composable(SweetShopScreen.Profile.name) {
                ProfileScreen(
                    navController,
                    appBarOpened,
                    loggedInUser,
                    navigateToChangeProfile = { navController.navigate(SweetShopScreen.ChangeProfile.name) },
                    navigateToChangePassword = { navController.navigate(SweetShopScreen.ChangePassword.name) },
                    logout = {
                        navController.navigate(SweetShopScreen.Login.name)
                        viewModel.logout()
                        loggedInUser.value = null
                        cart.value = emptyList()
                    }
                )
            }

            composable(SweetShopScreen.ProductDetails.name) {
                ProductDetailsScreen(
                    navController,
                    appBarOpened,
                    currentProductInDetails,
                    addComment = { commentText ->
                        viewModel.addComment(currentProductInDetails.value!!.name, commentText)
                        currentProductInDetails.value =
                            viewModel.getProduct(currentProductInDetails.value!!.name)
                    },
                    addToCart = { quantity ->
                        viewModel.addToCart(currentProductInDetails.value!!.name, quantity)
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                    }
                )
            }

            composable(SweetShopScreen.ChangePassword.name) {
                ChangePasswordScreen(
                    navController,
                    appBarOpened,
                    navigateToProfile = { navController.navigate(SweetShopScreen.Profile.name) },
                    updatePassword = { oldPassword, newPassword ->
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                        viewModel.updatePassword(oldPassword, newPassword)
                    }
                )
            }

            composable(SweetShopScreen.ChangeProfile.name) {
                ChangeProfileScreen(
                    navController,
                    appBarOpened,
                    loggedInUser,
                    navigateToProfile = { navController.navigate(SweetShopScreen.Profile.name) },
                    updateUser = { user ->
                        viewModel.updateUser(user)
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                        navController.navigate(SweetShopScreen.Profile.name)
                    }
                )
            }

            composable(SweetShopScreen.ContactUs.name) {
                ContactUsScreen(navController, appBarOpened)
            }

            composable(SweetShopScreen.Notifications.name) {
                NotificationsScreen(
                    navController,
                    appBarOpened,
                    remember { mutableStateOf(loggedInUser.value!!.nots.reversed()) },
                    setNotificationSeen = { index ->
                        viewModel.setNotificationSeen(index)
                        loggedInUser.value = viewModel.getLoggedInUser()
                        cart.value = viewModel.getLoggedInUser().cart
                    },
                    goToOrder = { order ->
                        orderItems.value = order.items
                        navController.navigate(SweetShopScreen.Order.name)
                    }
                )
            }

            composable(SweetShopScreen.Order.name) {
                OrderScreen(
                    navController,
                    appBarOpened,
                    orderItems.value,
                    goToProductDetailPage = { productName ->
                        currentProductInDetails.value = viewModel.getProduct(productName)
                        navController.navigate(SweetShopScreen.ProductDetails.name)
                    }
                )
            }
        }
    }
}

@Composable
fun ProductInit() {
    val resources = LocalContext.current.resources
    val packageName = LocalContext.current.packageName

    DataSource.products.forEach { product ->
        val imageName = "${product.type}${product.image}"
        val imageResId = resources.getIdentifier(imageName, "drawable", packageName)
        product.image = imageResId
    }
}

@Preview(showBackground = true)
@Composable
fun SweetShopPreview() {
    SlatkiZalogajiTheme {
        SweetShopApp()
    }
}