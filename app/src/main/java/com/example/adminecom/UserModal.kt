package com.example.adminecom

data class UserModal(
    var dob: String? = "",
    var email: String? = "",
    var gender: String? = "",
    var name: String? = "",
    var pass: String? = "",
    var phnNo: String? = "",
    var profilePic: String? = "",
    //var status: Int? = -1,
) {
    var userId: String = ""
}
