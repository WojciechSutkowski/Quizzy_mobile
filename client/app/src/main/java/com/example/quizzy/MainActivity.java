package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.quizzy.api.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout football = findViewById(R.id.football);
        RelativeLayout programming = findViewById(R.id.programming);
        RelativeLayout movies = findViewById(R.id.movies);
        RelativeLayout geography = findViewById(R.id.geography);
        RelativeLayout series = findViewById(R.id.series);
        RelativeLayout gaming = findViewById(R.id.gaming);

        football.setOnClickListener(this);
        programming.setOnClickListener(this);
        movies.setOnClickListener(this);
        geography.setOnClickListener(this);
        series.setOnClickListener(this);
        gaming.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.football:
                sendCategory("football");
                break;
            case R.id.programming:
                sendCategory("programming");
                break;
            case R.id.movies:
                sendCategory("movies");
                break;
            case R.id.geography:
                sendCategory("geography");
                break;
            case R.id.series:
                sendCategory("series");
                break;
            case R.id.gaming:
                sendCategory("gaming");
                break;
        }
    }

    private void sendCategory(String cat) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.5:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Void> category = jsonPlaceHolderApi.postCategory(cat);

        category.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Zapytanie sendCategory powiodlo sie: " );

                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Zapytanie sendCategory nie powiodlo sie: " );

            }
        });
    }
}