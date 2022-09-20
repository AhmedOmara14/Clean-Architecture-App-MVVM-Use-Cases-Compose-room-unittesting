package com.omaradev.movieapp.data.repository

import com.omaradev.movieapp.data.remote.Api
import com.omaradev.movieapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val api: Api) :Repository {

}