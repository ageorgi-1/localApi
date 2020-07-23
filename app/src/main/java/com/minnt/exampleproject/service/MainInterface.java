package com.minnt.exampleproject.service;

import com.minnt.exampleproject.dto.Post;
import com.minnt.localapi.LocalService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface MainInterface {

    @Headers({
            LocalService.filePath + "fixture/posts.json"  ,
    })
    @GET("posts")
    Call<List<Post>> getPosts();



}
