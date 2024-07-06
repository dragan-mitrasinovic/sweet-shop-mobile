package com.example.slatkizalogaji.data

object DataSource {
    val users = mutableListOf<User>()
    val products = mutableListOf<Product>()
    val orders = mutableListOf<Order>()

    init {
        userInit(users)
        productInit(products)
        commentInit(products)
        notificationInit(users, orders)
        orderInit(orders, products)
    }
}

fun userInit(users: MutableList<User>) {
    users.add(User().apply {
        name = "Dragan"
        surname = "Mitrasinovic"
        phone = "0617231405"
        address = "Tosin Bunar, Zemun"
        username = "dragan"
        password = "123"
        admin = false
    })

    users.add(User().apply {
        name = "Bogdan"
        surname = "Rsumovic"
        phone = "0617230000"
        address = "Bulevar Kralja Aleksandra 123"
        username = "bogdan"
        password = "123"
        admin = false
    })

    users.add(User().apply {
        name = "Maja"
        surname = "Mitrasinovic"
        phone = "060777777"
        address = "Kralja Petra, Uzice"
        username = "maja"
        password = "123"
        admin = false
    })

    users.add(User().apply {
        name = "Marija"
        surname = "Punt"
        phone = "0690000000"
        address = "ETF"
        username = "marija"
        password = "123"
        admin = true
    })
}

fun productInit(products: MutableList<Product>) {
    products.add(Product().apply {
        name = "Oreo mafin"
        type = "cupcake"
        desc = "Mafin punjen oreo komadicima, sa belom glazurom na vrhu"
        price = 300
        image = 1
        ingredients = "Oreo keks, mleko, slag, secer, brasno, cokolada"
    })

    products.add(Product().apply {
        name = "Mafin sa pomorandzom"
        type = "cupcake"
        desc =
            "Mafin od vazdusastog testa, sa slagom sa ekstraktom pomorandze i kriskom pomomrandze na vrhu"
        price = 250
        image = 2
        ingredients = "Pomorandza, slag, maslac, brasno, jaja, zuti secer, kokosovo mleko"
    })

    products.add(Product().apply {
        name = "Mafin sa jagodom"
        type = "cupcake"
        desc =
            "Mafin od vazdusastog testa, sa slagom sa roze slagom sa ukusom jagode i jagodicom na vrhu"
        price = 180
        image = 3
        ingredients = "Jagoda, roze boja za kolace, slag, brasno, jaja, mleko, secer, so"
    })

    products.add(Product().apply {
        name = "Mafin sa cokoladom"
        type = "cupcake"
        desc =
            "Cokoladni mafin, sa testom od cokolade i cokoladnim prelivom posut cokoladnim mrvicama"
        price = 230
        image = 4
        ingredients = "Crna cokolada, mlecna cokolada, brasno, mleko, jaja, cokoladne mrvice"
    })

    products.add(Product().apply {
        name = "Pauk oreo mafin"
        type = "cupcake"
        desc = "Mafin sa temom noci vestica, u obliku pauka, sa ukusom orea"
        price = 350
        image = 5
        ingredients =
            "Bela cokolada, beli oreo krem, oreo keks, mleko, puter, jaja, prasak za pecivo, usecerene oci, cokolada"
    })

    products.add(Product().apply {
        name = "Princeza mafin"
        type = "cupcake"
        desc =
            "Slatki mafin sa sljokicama, posut jos i zvezdicama i perlama, sa sarenom glazurom i obojenim testom"
        price = 280
        image = 6
        ingredients =
            "Bela cokolada, secer, maline, jestive sljokice, zvezdice i perle, jaja, mleko, brasno, puter"
    })

    products.add(Product().apply {
        name = "Bela vocna torta"
        type = "cake"
        desc =
            "Torta sa belim slagom, primerena za vencanja i druge proslave, sa vestackim ruzama od secera"
        price = 1500
        image = 1
        ingredients =
            "Slag, visnja, jagoda, malina, bela cokolada, secerne ruze, secer, puter, cokoladne kore za tortu"
    })

    products.add(Product().apply {
        name = "Crna cokolada torta"
        type = "cake"
        desc =
            "Torta od crne cokolade, bogata cokoladnim musom iznutra, sa rendanom crnom cokoladom preko, prelivom od cokolade i tresnjicama"
        price = 1350
        image = 3
        ingredients =
            "Slag, tresnje, crna cokolada, noissette, lesnik, secer, cokoladni preliv od crne cokolade, rendana crna cokolada"
    })

    products.add(Product().apply {
        name = "Ukrasena tresnja torta"
        type = "cake"
        desc =
            "Savrsena torta za mladence, sa tresnjicama, crvenim slagom kao ukrasom i roze slagom"
        price = 1700
        image = 2
        ingredients =
            "Slag, roze boja za kolace, crvena boja za kolace, secer, mleko, crna cokolada, vestacke ruze od secera"
    })

    products.add(Product().apply {
        name = "Sarena party torta"
        type = "cake"
        desc =
            "Torta sa sarenim testom, slagom i prelivima, sa keksicima na vrhu punjenim roze slagom, savrsena za decje rodjendane"
        price = 1890
        image = 4
        ingredients =
            "Boja za kolace, cokolada, keksici, slag, puter, jaja, secer, brasno, cimet, preliv sa borovnicama"
    })

    products.add(Product().apply {
        name = "Oreo torta"
        type = "cake"
        desc =
            "Omiljena torta kupaca, torta sa oreo slagom, oreo prelivom, oreo keksicima i punjena belim oreo kremom"
        price = 2500
        image = 5
        ingredients =
            "Oreo keks, crne oreo mrvice, slag, beli oreo krem, mlecna cokolada, oreo slag, secer, preliv od mlecne cokolade, beli cokoladni mus, mleko"
    })

    products.add(Product().apply {
        name = "Slatka sirena torta"
        type = "cake"
        desc =
            "Torta neznih boja, sa ukrasima od slaga i vestackim secernim keksicima na vrhu, iznutra od vanile, plazme i banane"
        price = 2100
        image = 6
        ingredients =
            "Slag, boja za testo, boja za slag, secerni ukrasi, vanila, mlevena plazma, plazma, banana, secer, mleko"
    })
}

fun commentInit(products: MutableList<Product>) {
    for (i in products.indices) {
        val commentNumber = (1..3).random()

        for (j in 0 until commentNumber) {
            val rngIndex = randomComments.indices.random()

            products[i].comments.add(Comment().apply {
                username = usernames[j]
                text = randomComments[rngIndex]
            })
        }
    }
}

fun notificationInit(users: MutableList<User>, orders: MutableList<Order>) {
    for (i in orders.indices) {
        users[i / 2].nots = mutableListOf(
            Notification().apply {
                text = "Porudzbina je primljena"
                order = orders[i]
            }
        )

        users[3].nots = mutableListOf(
            Notification().apply {
                text = "Stigla vam je porudzbina"
                order = orders[i]
            }
        )
    }
}

fun orderInit(orders: MutableList<Order>, products: MutableList<Product>) {
    for (i in 0..5) {
        val order = Order().apply {
            user = usernames[i / 2]
            ordered = true
        }

        val rngItems = (1..5).random()
        var d = 0

        for (j in 0 until rngItems) {
            val rngCnt = (1..10).random()
            val rngI = (1..2).random()
            d += rngI
            order.items.add(Item().apply {
                count = rngCnt
                product = products[d]
            })
        }

        orders.add(order)
    }
}

val usernames = listOf("dragan", "bogdan", "maja")
val randomComments = listOf(
    "Vrlo slatko, preporucujem",
    "Prelep slag, odlicno testo",
    "Izgleda isto kao na slici, sjajno",
    "Harmonija ukusa, narucicu opet",
    "Preslatko, samo za one koji vole secer",
    "Sjajno vazdusasto testo, svidja mi se",
    "Malo manje slaga bi trebalo, ima dosta",
    "Omiljena poslastica",
    "Bas mi se dopada, svaka cast"
)

class User {
    var name: String = ""
    var surname: String = ""
    var phone: String = ""
    var address: String = ""
    var username: String = ""
    var password: String = ""
    var admin: Boolean = false
    var nots: MutableList<Notification> = mutableListOf()
    var cart: MutableList<Item> = mutableListOf()
}

class Notification {
    var text: String = ""
    var seen: Boolean = false
    var order: Order = Order()
}

class Item {
    var count: Int = 0
    var product: Product = Product()
}

class Comment {
    var username: String = ""
    var text: String = ""
}

class Order {
    var user: String = ""
    var items: MutableList<Item> = mutableListOf()
    var ordered: Boolean = false
    var accepted: Boolean = true
    var processing: Boolean = false
    var done: Boolean = false
}

class Product {
    var name: String = ""
    var type: String = ""
    var desc: String = ""
    var price: Int = 0
    var image: Int = 0
    var ingredients: String = ""
    var comments: MutableList<Comment> = mutableListOf()
}