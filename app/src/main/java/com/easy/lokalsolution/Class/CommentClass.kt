package com.easy.lokalsolution.Class

class CommentClass {
    var newsid: String? = null
    var userid: String? = null
    var comid: String? = null
    var comment: String? = null
    var time: Long? = null

    constructor()
    constructor(newsid: String?, userid: String?, comid: String?, comment: String?, time: Long?) {
        this.newsid = newsid
        this.userid = userid
        this.comid = comid
        this.comment = comment
        this.time = time
    }
}
