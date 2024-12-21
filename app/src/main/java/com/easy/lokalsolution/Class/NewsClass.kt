package com.easy.lokalsolution.Class

class NewsClass {
    var type: String? = null
    var title: String? = null
    var disc: String? = null
    var usrerid: String? = null
    var id: String? = null
    var image: String? = null
    var time: Long? = null

    constructor()
    constructor(
        type: String?,
        title: String?,
        disc: String?,
        usrerid: String?,
        id: String?,
        image: String?,
        time: Long?
    ) {
        this.type = type
        this.title = title
        this.disc = disc
        this.usrerid = usrerid
        this.id = id
        this.image = image
        this.time = time
    }


}
