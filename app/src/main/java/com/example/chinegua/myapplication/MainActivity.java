package com.example.chinegua.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chinegua.myapplication.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity {

        private TextView tvRespuesta;
        private RESTAPIService apiService;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvRespuesta = (TextView) findViewById(R.id.tvRespuesta);
            tvRespuesta.setText("");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://restcountries.eu")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(RESTAPIService.class); //---------
            obtenerInfo();
        }

        public void obtenerInfo(){

            Call<List<Country>> call_async = apiService.getCountryByName("SPAIN");
            Log.i("MiW","INNNNNN");

            call_async.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    List<Country> countryList = response.body();
                    if (null != countryList) {
                        for (Country country : countryList) {
                            tvRespuesta.append(country.toString() + "\n\n");
                        }
                        Log.i("MiW", "obtenerInfoPais => respuesta=" + countryList);
                    } else {

                    }
                }
                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "ERROR: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                    Log.e("MiW", t.getMessage());
                }
            });
        }
    }

