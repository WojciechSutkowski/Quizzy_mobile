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
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("report")
    Call<ResultsDto> getResults();

    @POST("category")
    Call<CategoryDto> postCategory(@Body CategoryDto categoryDto);

    @GET("question/{id}")
    Call<QuestionDto> getQuestion(@Path("id") int id);

    @PUT("calculate")
    Call<Void> putAnswers(@Body AnswersDto answersDto);

}
