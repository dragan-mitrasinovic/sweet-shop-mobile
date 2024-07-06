package com.example.slatkizalogaji.ui

import androidx.lifecycle.ViewModel
import com.example.slatkizalogaji.data.Comment
import com.example.slatkizalogaji.data.DataSource
import com.example.slatkizalogaji.data.Item
import com.example.slatkizalogaji.data.Notification
import com.example.slatkizalogaji.data.Order
import com.example.slatkizalogaji.data.Product
import com.example.slatkizalogaji.data.UiState
import com.example.slatkizalogaji.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SweetShopViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getLoggedInUser(): User = _uiState.value.currentUser!!

    fun setLoggedInUser(username: String) {
        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(username))
        }
    }

    fun credentialsValid(username: String, password: String): Boolean {
        val user = getUser(username)
        return user?.password == password
    }

    fun logout() {
        _uiState.update { currentState ->
            currentState.copy(currentUser = null)
        }
    }

    fun updateUser(user: User) {
        DataSource.users.find { it.username == user.username }?.apply {
            this.name = user.name
            this.surname = user.surname
            this.phone = user.phone
            this.address = user.address
            this.username = user.username
        }

        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(user.username))
        }
    }

    fun updatePassword(oldPassword: String, newPassword: String): Boolean {
        val user = _uiState.value.currentUser

        return if (user?.password == oldPassword) {
            DataSource.users.find { it.username == user.username }?.password = newPassword
            true
        } else {
            false
        }
    }

    fun addComment(productName: String, commentText: String) {
        val product = DataSource.products.find { it.name == productName }
        if (product == null) {
            return
        }

        product.comments.add(
            Comment().apply {
                this.text = commentText
                this.username = _uiState.value.currentUser!!.username
            }
        )
    }

    fun getProduct(productName: String): Product? {
        return DataSource.products.find { it.name == productName }
    }

    fun addToCart(productName: String, quantity: Int) {
        val product = DataSource.products.find { it.name == productName }
        if (product == null) {
            return
        }

        val cart =
            DataSource.users.find { it.username == _uiState.value.currentUser!!.username }?.cart

        if (cart!!.any { it.product.name == productName }) {
            cart.find { it.product.name == productName }?.apply {
                this.count += quantity
            }
        } else {
            cart.add(Item().apply {
                this.product = product
                this.count = quantity
            })
        }

        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(_uiState.value.currentUser!!.username))
        }
    }

    fun setProductQuantity(productName: String, quantity: Int) {
        val cart =
            DataSource.users.find { it.username == _uiState.value.currentUser!!.username }?.cart

        if (quantity == 0) {
            DataSource.users.find { it.username == _uiState.value.currentUser!!.username }?.cart =
                cart?.filter { it.product.name != productName }?.toMutableList()!!
        } else {
            cart?.find { it.product.name == productName }?.apply {
                this.count = quantity
            }
        }

        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(_uiState.value.currentUser!!.username))
        }
    }

    fun completeOrder() {
        val user = _uiState.value.currentUser!!

        DataSource.orders.add(Order().apply {
            this.user = user.username
            this.items = user.cart
        })

        DataSource.users.find { it.username == user.username }?.nots?.add(
            Notification().apply {
                this.text = "Porudzbina je primljena"
                this.order = DataSource.orders.last()
            }
        )

        DataSource.users.find { it.username == user.username }?.cart = mutableListOf()

        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(user.username))
        }
    }

    fun setNotificationSeen(indexFromEnd: Int) {
        val nots =
            DataSource.users.find { it.username == _uiState.value.currentUser!!.username }?.nots

        for (i in nots!!.indices) {
            if (i == nots.size - 1 - indexFromEnd) {
                nots[i].seen = true
            }
        }

        DataSource.users.find { it.username == _uiState.value.currentUser!!.username }?.nots =
            nots.map {
                Notification().apply {
                    this.text = it.text
                    this.seen = it.seen
                    this.order = it.order
                }
            }.toMutableList()

        _uiState.update { currentState ->
            currentState.copy(currentUser = getUser(_uiState.value.currentUser!!.username))
        }

    }

    private fun getUser(username: String): User? {
        return DataSource.users.find { it.username == username }
    }
}