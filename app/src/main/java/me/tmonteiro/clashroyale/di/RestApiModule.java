package me.tmonteiro.clashroyale.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.tmonteiro.clashroyale.api.CardAPI;
import me.tmonteiro.clashroyale.api.CardDetailAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestApiModule {

    public final static String API_URL = "http://www.clashapi.xyz/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        //TODO: Review
//        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
//        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    CardAPI providesCardApi(Retrofit retrofit) {
        return retrofit.create(CardAPI.class);
    }

    @Provides
    @Singleton
    CardDetailAPI providesDetailCardApi(Retrofit retrofit) {
        return retrofit.create(CardDetailAPI.class);
    }

}
