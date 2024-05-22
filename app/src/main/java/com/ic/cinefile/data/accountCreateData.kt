package com.ic.cinefile.data

import com.google.gson.annotations.SerializedName


class accountCreateData(username:String,email:String,password: String,year_nac:String,genere:String,movie_genere:List<String>,avatar:String?) {


    @SerializedName("username")
    private var username: String

    @SerializedName("email")
    private var email: String

    @SerializedName("password")
    private var password: String

    @SerializedName("year_nac")
    private var year_nac: String

    @SerializedName("genere")
    private var genere: String

    @SerializedName("movie_genere")
    private var movie_genere: List<String>

    @SerializedName("avatar")
    private var avatar: String?

    init {

        this.username = username
        this.email = email
        this.password = password
        this.year_nac = year_nac
        this.genere = genere
        this.movie_genere = movie_genere
        this.avatar = avatar
    }

    //setters
    public fun setUsername(username: String) {

        this.username = username
    }

    public fun setEmail(email: String) {
        this.email = email
    }

    public fun setPassword(password: String) {

        this.password = password
    }

    public fun setYear_nac(year_nac: String) {

        this.year_nac = year_nac
    }

    public fun setGenere(genere: String) {

        this.genere = genere
    }

    public fun setMovieGenere(movie_genere: List<String>) {

        this.movie_genere = movie_genere
    }

    public fun setAvatar(avatar: String?) {

        this.avatar = avatar
    }


    //getters
    public fun getUsername(): String {
        return username
    }

    public fun getEmail(): String {
        return email
    }

    public fun getPassword(): String {
        return password
    }

    public fun getYear_nac(): String {

        return year_nac
    }

    public fun getGenere(): String {

        return genere
    }

    public fun getMovieGenere(): List<String> {

        return movie_genere
    }

    public fun getAvatar(): String? {

        return avatar
    }

}






