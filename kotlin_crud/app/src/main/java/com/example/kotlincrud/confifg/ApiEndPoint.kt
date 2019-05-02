package com.example.kotlincrud.confifg

class ApiEndPoint {

    companion object {
        private val SERVER = "http://192.168.43.71/kotlin_api/crud_api/"
        val CREATE = SERVER+"create.php"
        val READ  = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"
    }

}