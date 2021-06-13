package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.quizzy.api.JsonPlaceHolderApi;
import com.example.quizzy.dto.AnswersDto;
import com.example.quizzy.dto.QuestionDto;
import com.example.quizzy.dto.ResultsDto;

import java.lang.reflect.Array;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionActivity extends AppCompatActivity {

    int question_index = 1;
    boolean is_last;
    private  int[] selected;
    int size;
    private TextView quizQuestionNumber;
    private TextView quizQuestionView;
    private ToggleButton quizAnswer1View;
    private ToggleButton quizAnswer2View;
    private ToggleButton quizAnswer3View;
    private ToggleButton quizAnswer4View;
    private Button nextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        nextQuestion = findViewById(R.id.button_next_question);

        nextQuestion.setOnClickListener(v -> {
            size = 0;

            if(quizAnswer1View.isChecked()){
                selected=addAnswer(size, selected, 1);
                size++;
            }
            if(quizAnswer2View.isChecked()){
                selected=addAnswer(size, selected, 2);
                size++;
            }
            if(quizAnswer3View.isChecked()){
                selected=addAnswer(size, selected, 3);
                size++;
            }
            if(quizAnswer4View.isChecked()){
                selected=addAnswer(size, selected, 4);
                size++;
            }

            System.out.println("Zaznaczone odpowiedzi: " + Arrays.toString(selected));

            putAnswers(selected);

            if(quizAnswer1View.isChecked() || quizAnswer2View.isChecked() || quizAnswer3View.isChecked() || quizAnswer4View.isChecked()) {
                quizAnswer1View.setChecked(false);
                quizAnswer2View.setChecked(false);
                quizAnswer3View.setChecked(false);
                quizAnswer4View.setChecked(false);
            }

            if(is_last) {
                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
                return;
            }

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.5:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<QuestionDto> listCall = jsonPlaceHolderApi.getQuestion(question_index);

        listCall.enqueue(new Callback<QuestionDto>() {
            @Override
            public void onResponse(Call<QuestionDto> call, Response<QuestionDto> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Zapytanie getQuestion nie powiodlo sie: ");
                    return;
                }

                quizQuestionNumber.setText(question_index+"/10");
                quizQuestionView.setText(response.body().getQuestion());

                quizAnswer1View.setText(response.body().getAnswers()[0]);
                quizAnswer1View.setTextOn(response.body().getAnswers()[0]);
                quizAnswer1View.setTextOff(response.body().getAnswers()[0]);

                quizAnswer2View.setText(response.body().getAnswers()[1]);
                quizAnswer2View.setTextOn(response.body().getAnswers()[1]);
                quizAnswer2View.setTextOff(response.body().getAnswers()[1]);

                quizAnswer3View.setText(response.body().getAnswers()[2]);
                quizAnswer3View.setTextOn(response.body().getAnswers()[2]);
                quizAnswer3View.setTextOff(response.body().getAnswers()[2]);

                quizAnswer4View.setText(response.body().getAnswers()[3]);
                quizAnswer4View.setTextOn(response.body().getAnswers()[3]);
                quizAnswer4View.setTextOff(response.body().getAnswers()[3]);
                question_index++;

                if(response.body().getLastQuestion()) {
                    is_last = true;
                    nextQuestion.setText("Finish quiz");
                }

            }

            @Override
            public void onFailure(Call<QuestionDto> call, Throwable t) {
                System.out.println("Zapytanie getQuestion nie powiodlo sie: " + t);
            }
        });
    }

    private void putAnswers(int[] sel) {
        quizQuestionNumber = findViewById(R.id.question_number);
        quizAnswer1View = findViewById(R.id.answer_button1);
        quizAnswer2View = findViewById(R.id.answer_button2);
        quizAnswer3View = findViewById(R.id.answer_button3);
        quizAnswer4View = findViewById(R.id.answer_button4);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.0.5:8080/quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // TODO
        AnswersDto answers = new AnswersDto(sel,question_index - 1 ,is_last);

        Call<Void> callAns = jsonPlaceHolderApi.putAnswers(answers);

        callAns.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Zapytanie putAnswers powiodlo sie: " );

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Zapytanie putAnswers nie powiodlo sie: " );

            }
        });
    }

    private static int[] addAnswer(int n, int sel[], int x) {
        int newSel[] = new int[n+1];

        for(int i=0; i<n; i++){
            newSel[i] = sel[i];
        }
        newSel[n] = x;
        return newSel;
    }


}