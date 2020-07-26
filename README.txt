
About :

this library works with Retrofit and gets the response Data from Asset folder instead of the server .



Implementation : (in the Project )

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
      implementation 'com.github.ageorgi-1:localApi:Tag'
}


Implementation : ( with Retrofit )

1. add this line to  ".client(LocalService.LocalApiBuilder(instance))" to Retrofit builder

    Example:
    Retrofit retrofit = builder
            .client(LocalService.LocalApiBuilder(instance))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


2 . add the following line to Retrofit interface Header
     LocalService.filePath + "fixture/posts.json"
     the json file should be placed in Asset Folder

     Example :

     @Headers({
                 LocalService.filePath + "fixture/posts.json"  ,
         })
         @GET("posts")
         Call<List<Post>> getPosts();




How to use :

1 . setDeveloperMode(true) ;


2. call this function "callLocalApi();" before calling the server ...

    Example :
     void getPostsFromManager() {

           callLocalApi();

            MainService.getAllPosts(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                    if(response.isSuccessful()) {
                        setTxt(response.body().get(0).getTitle());
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                    Log.d(TAG, "onFailure: ");
                }
            }  );
        }

3. the response will be the json file from the asset Folder .