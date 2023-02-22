package com.example.drawerfamex.API.api;

import com.example.drawerfamex.API.models.ApiNumeroVersionesRes;
import com.example.drawerfamex.API.models.ApiRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiFamexService {

    String version = null;

    @GET("index.html")
    Call<ApiRespuesta> obtenerVersion();

    @GET("nVersiones/")
    Call<ApiNumeroVersionesRes> obtenerNumeroVersiones();
}
