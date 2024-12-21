package com.easy.lokalsolution.Class

class UserClass {
    var image: String? = null
    var name: String? = null
    var email: String? = null
    var number: String? = null
    var token: String? = null
    var userid: String? = null
    var city: String? = null

    constructor()
    constructor(userid: String?) {
        this.userid = userid
    }

    constructor(
        image: String?,
        name: String?,
        email: String?,
        number: String?,
        token: String?,
        userid: String?,
        city: String?
    ) {
        this.image = image
        this.name = name
        this.email = email
        this.number = number
        this.token = token
        this.userid = userid
        this.city = city
    }
}
