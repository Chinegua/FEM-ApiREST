package com.example.chinegua.myapplication;



import com.example.chinegua.myapplication.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


@SuppressWarnings("Unused")
interface RESTAPIService {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/rest/v2/name/{countryName}")
    Call<List<Country>> getCountryByName(@Path("countryName") String countryName);

    @GET("/rest/v2/alpha/alpha/{countryCode}")
    Call<Country> getCountryByCode(@Path("countryCode") String countryCode);

}
