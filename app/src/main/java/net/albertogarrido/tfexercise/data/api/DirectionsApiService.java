package net.albertogarrido.tfexercise.data;

import com.google.gson.GsonBuilder;

import net.albertogarrido.tfexercise.data.responsemodels.DirectionsResponse;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DirectionsApiService {

    String API_KEY = "AIzaSyBG9sdPACNw2svFE7QqIApXVxDU3hFNV_E";
    String DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/";

    @GET("directions")
    Call<DirectionsResponse> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination
            //TODO add mode (driving -default-, walking, transit)
    );

    OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    // interceptor to log queries
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    // interceptor to avoid passing the API_KEY in every query
                    // also it adds the path segment for json response
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();

                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addPathSegment("json")
                                    .addQueryParameter("key", API_KEY)
                                    .build();

                            Request.Builder requestBuilder = original.newBuilder().url(url);

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    });

    GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
            new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()
            ).create());


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(DIRECTIONS_URL)
            .client(httpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .build();


}
