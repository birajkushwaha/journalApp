package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.Cache.AppCache;
import com.general_Biraj.journalApp.apiResponse.WeatherResponse;
import com.general_Biraj.journalApp.constant.PlaceHolder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
public class WeatherService {

    @Autowired
    private AppCache appCache;
    @Value("${weather.api.key}")
    private String apikey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if(weatherResponse !=null){
            return  weatherResponse;
        }
        else{
            String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.CITY, city).replace(PlaceHolder.API_KEY, apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body !=null) {
                redisService.set("Weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
