package com.easy.lokalsolution.Class

class IdClass {
    var type: String? = null
    var cname: String? = null
    var cdetail: String? = null
    var exptime: String? = null
    var sname: String? = null
    var saddress: String? = null
    var customtime: String? = null
    var more: String? = null
    var uname: String? = null
    var ucontact: String? = null
    var uwhatsapp: String? = null
    var uemail: String? = null
    var image: String? = null
    var id: String? = null
    var userid: String? = null
    var time: Long? = null

    constructor()
    constructor(
        type: String?,
        cname: String?,
        cdetail: String?,
        exptime: String?,
        sname: String?,
        saddress: String?,
        customtime: String?,
        more: String?,
        uname: String?,
        ucontact: String?,
        uwhatsapp: String?,
        uemail: String?,
        image: String?,
        id: String?,
        userid: String?,
        time: Long?
    ) {
        this.type = type
        this.cname = cname
        this.cdetail = cdetail
        this.exptime = exptime
        this.sname = sname
        this.saddress = saddress
        this.customtime = customtime
        this.more = more
        this.uname = uname
        this.ucontact = ucontact
        this.uwhatsapp = uwhatsapp
        this.uemail = uemail
        this.image = image
        this.id = id
        this.userid = userid
        this.time = time
    }
}
