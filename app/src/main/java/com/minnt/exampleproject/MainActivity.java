package com.minnt.exampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.minnt.exampleproject.dto.Post;
import com.minnt.exampleproject.service.MainService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.minnt.localapi.LocalService.callLocalApi;
import static com.minnt.localapi.LocalService.setDeveloperMode;

public class MainActivity extends AppCompatActivity {


    String TAG = "HHH" ;

    TextView text  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = findViewById(R.id.text) ;
        setDeveloperMode(true) ;

        getPostsFromManager();
    }


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



    void setTxt( String txt) {
        text.setText(txt);
    }


}