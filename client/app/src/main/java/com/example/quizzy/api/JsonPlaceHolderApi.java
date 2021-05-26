package com.example.quizzy.api;

import com.example.quizzy.dto.ResultsDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("report")
    Call<ResultsDto> getResults();
}
