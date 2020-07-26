package com.minnt.exampleproject.service;


import com.minnt.exampleproject.ApiService;
import com.minnt.exampleproject.dto.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainService  {


    private static MainInterface mainInterface = ApiService.createService(MainInterface.class);

    public static void getAllPosts(Callback<List<Post>> getPostsResponseCallback ) {

        Call< List<Post> > getPosts = mainInterface.getPosts() ;

        getPosts.enqueue( getPostsResponseCallback );


    }


}
