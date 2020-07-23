package com.minnt.exampleproject.service;

import com.minnt.exampleproject.dto.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface MainInterface {

    @Headers({
            "localFixtureFile:posts.json" ,
    })
    @GET("posts")
    Call<List<Post>> getPosts();



}
