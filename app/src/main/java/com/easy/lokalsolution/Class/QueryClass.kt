package com.easy.lokalsolution.Class

class QueryClass {
    var type: String? = null
    var subtype: String? = null
    var money: String? = null
    var address: String? = null
    var disc: String? = null
    var note: String? = null
    var uname: String? = null
    var contact: String? = null
    var whatsapp: String? = null
    var contactime: String? = null
    var image: String? = null
    var id: String? = null
    var time: Long? = null

    constructor()
    constructor(
        type: String?,
        subtype: String?,
        money: String?,
        address: String?,
        disc: String?,
        note: String?,
        uname: String?,
        contact: String?,
        whatsapp: String?,
        contactime: String?,
        image: String?,
        id: String?,
        time: Long?
    ) {
        this.type = type
        this.subtype = subtype
        this.money = money
        this.address = address
        this.disc = disc
        this.note = note
        this.uname = uname
        this.contact = contact
        this.whatsapp = whatsapp
        this.contactime = contactime
        this.image = image
        this.id = id
        this.time = time
    }
}
