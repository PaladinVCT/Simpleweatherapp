package by.lebedev.simpleweatherapp.di.module

import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.ui.today.TodayPresenter
import by.lebedev.simpleweatherapp.ui.today.TodayPresenterDefault
import by.lebedev.simpleweatherapp.ui.today.TodayView
import by.lebedev.simpleweatherapp.utils.Constants.Companion.BASE_URL
import by.lebedev.simpleweatherapp.utils.WeatherUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideOkHTTPClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): ApiWeatherInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiWeatherInterface::class.java)
    }

    @Provides
    fun provideWeatherUtils(): WeatherUtils {
        return WeatherUtils.instance
    }
}