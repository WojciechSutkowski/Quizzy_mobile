package com.example.quizzy.api;

import com.example.quizzy.QuestionActivity;
import com.example.quizzy.dto.AnswersDto;
import com.example.quizzy.dto.CategoryDto;
import com.example.quizzy.dto.QuestionDto;
import com.example.quizzy.dto.ResultsDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi {

    @GET("report")
    Call<ResultsDto> getResults();

    @POST("category")
    Call<CategoryDto> postCategory(@Body CategoryDto categoryDto);

    @GET("question/{questionNumber}")
    Call<QuestionDto> getQuestion();

    @PUT("calculate")
    Call<AnswersDto> putAnswers(@Body AnswersDto answersDto);

}
