package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzy.api.JsonPlaceHolderApi;
import com.example.quizzy.dto.AnswersDto;
import com.example.quizzy.dto.ResultsDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    private TextView quizResultsView;
    private TextView quizPointsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getResults();
    }

    public void switchToStart(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void getResults() {
        quizResultsView = findViewById(R.id.quiz_result);
        quizPointsView = findViewById(R.id.quiz_points);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.5:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<ResultsDto> listCall = jsonPlaceHolderApi.getResults();

        listCall.enqueue(new Callback<ResultsDto>() {

            @Override
            public void onResponse(Call<ResultsDto> call, Response<ResultsDto> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Zapytanie nie powiodlo sie: ");
                    return;
                }
                quizResultsView.setText("Result: " + response.body().getResult());
                quizPointsView.setText("Points: " + response.body().getUserPoints() + "/" + response.body().getMaxPoints());

            }

            @Override
            public void onFailure(Call<ResultsDto> call, Throwable t) {
                System.out.println("Zapytanie nie powiodlo sie: " + t);
            }
        });
    }

}
