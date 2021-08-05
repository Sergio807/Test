package lesson7.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/{locationKey}
    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String API_KEY = "xM5vEbffLQ2X0E8zdiARl3NxyQLUEODA";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";


    public static final OkHttpClient okHttpClient = new OkHttpClient();
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather (String selectedCityKey, Period period) throws IOException {
        switch (period){
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(selectedCityKey)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();
                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();
                Response oneDayForecastsResponse = okHttpClient.newCall(request).execute();
                System.out.println(oneDayForecastsResponse.body().string());
        }
    }
    private String detectCityKey(String selectCity) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println(responseString);
        return responseString;


       // http://dataservice.accuweather.com/locations/v1/cities/autocomplete



    }

}
