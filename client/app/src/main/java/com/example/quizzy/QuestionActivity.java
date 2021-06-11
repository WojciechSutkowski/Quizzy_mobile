package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzy.api.JsonPlaceHolderApi;
import com.example.quizzy.dto.AnswersDto;
import com.example.quizzy.dto.QuestionDto;
import com.example.quizzy.dto.ResultsDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionActivity extends AppCompatActivity {

    int question_index = 1;
    private TextView quizQuestionNumber;
    private TextView quizQuestionView;
    private TextView quizAnswer1View;
    private TextView quizAnswer2View;
    private TextView quizAnswer3View;
    private TextView quizAnswer4View;
    private Button nextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        nextQuestion = findViewById(R.id.button_next_question);
        nextQuestion.setOnClickListener(v -> {
            putAnswers();
            question_index++;
            getQuestion();
        });

        getQuestion();
    }

    public void getQuestion() {
        quizQuestionNumber = findViewById(R.id.question_number);
        quizQuestionView = findViewById(R.id.question_text);
        quizAnswer1View = findViewById(R.id.answer_button1);
        quizAnswer2View = findViewById(R.id.answer_button2);
        quizAnswer3View = findViewById(R.id.answer_button3);
        quizAnswer4View = findViewById(R.id.answer_button4);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ip:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<QuestionDto> listCall = jsonPlaceHolderApi.getQuestion();

        listCall.enqueue(new Callback<QuestionDto>() {
            @Override
            public void onResponse(Call<QuestionDto> call, Response<QuestionDto> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Zapytanie nie powiodlo sie: ");
                    return;
                }
                quizQuestionNumber.setText(question_index/10);
                quizQuestionView.setText(response.body().getQuestion());
                quizAnswer1View.setText(response.body().getAnswers()[1]);
                quizAnswer2View.setText(response.body().getAnswers()[2]);
                quizAnswer3View.setText(response.body().getAnswers()[3]);
                quizAnswer4View.setText(response.body().getAnswers()[4]);
                response.body().getLastQuestion();
                response.body().getPoints();
            }

            @Override
            public void onFailure(Call<QuestionDto> call, Throwable t) {
                System.out.println("Zapytanie nie powiodlo sie: " + t);
            }
        });
    }

    private void putAnswers() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ip:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        AnswersDto answers = new AnswersDto();

        Call<AnswersDto> callAns = jsonPlaceHolderApi.putAnswers(answers);

        callAns.enqueue(new Callback<AnswersDto>() {
            @Override
            public void onResponse(Call<AnswersDto> call, Response<AnswersDto> response) {
                response.body().getQuestionId();
                response.body().getSelectedAnswers();
                response.body().isLastQuestion();
            }

            @Override
            public void onFailure(Call<AnswersDto> call, Throwable t) {
                System.out.println("Zapytanie nie powiodlo sie: " + t);
            }
        });
    }


}