package com.teambackend.service;

import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.GocampingDTO2;
import com.teambackend.dto.OpenWeatherDTO;
import com.teambackend.dto.OpenWeatherDTO2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class BatchOpenWeatherService {

    @Autowired
    private Environment env;

    @Autowired
    private OpenWeatherService openWeatherService;

    @Autowired
    private DataCopyService dataCopyService;

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedRate = 600000) // 600,000 milliseconds = 10 minutes
//    @Scheduled(cron = "0 7 0 1 * *") // 매달 01일 00시 07분에 실행
    @Transactional // 이 부분을 추가합니다.
    public void fetchAndRegisterWeatherData() {

        entityManager.flush(); // 강제로 변경사항을 데이터베이스에 반영
        entityManager.clear(); // 영속성 컨텍스트 초기화
        openWeatherService.removeAll(); // 데이터베이스테이블삭제

        String campingUrl = env.getProperty("external.api.weatherurl");
        String serviceKey = env.getProperty("external.api.weatherurl");

        String[] urlKRName = {"서울", "인천", "강릉", "춘천", "홍성", "수원", "대전", "청주", "대구", "부산", "포항", "창원", "울산", "안동", "광주", "전주", "목포", "여수", "제주"};

        String urlStringDefalut = campingUrl;
        String[] urlStringLat = {"37.5683", "37.45", "37.7556", "37.8747", "36.6009", "37.2911", "36.3333", "36.6372", "35.8703", "35.1028", "36.0322", "35.2281", "35.5372", "36.5656", "35.1547", "35.8219", "34.7936", "34.7546", "33.5097"};
        String[] urlStringLon = {"126.9778", "126.4161", "128.8961", "127.7342", "126.665", "127.0089", "127.4167", "127.4897", "128.5911", "129.0403", "129.365", "128.6811", "129.3167", "128.725", "126.9156", "127.1489", "126.3886", "127.6599", "126.5219"};
        String urlStringCnt = "30";
        String urlStringappid = serviceKey;
        String[] urlString = new String[urlStringLat.length];


        for (int i = 0; i < urlStringLat.length; i++) {
            urlString[i] = urlStringDefalut + "lat=" + urlStringLat[i] + "&" + "lon=" + urlStringLon[i] + "&" + "cnt=" + urlStringCnt + "&" + urlStringappid;

            try {
                URL url = new URL(urlString[i]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                StringBuilder response = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }

                conn.disconnect();

                // JSON 파싱
                JSONObject jsonObject = new JSONObject(response.toString());

                JSONObject weathercityInfo = jsonObject.getJSONObject("city");
                JSONArray weatherList = jsonObject.getJSONArray("list");

                for (int j = 0; j < weatherList.length(); j++) {
                    JSONObject weatherData = weatherList.getJSONObject(j);

                    // 도시와 나라
                    String weatherName = weathercityInfo.getString("name");
                    String weatherCounty = weathercityInfo.getString("country");

                    // 날짜와 시간 정보
                    long dt = weatherData.getLong("dt");
                    Date date = new Date(dt * 1000); // Unix 시간을 일반적인 시간으로 변환

                    // 기온 정보
                    JSONObject temp = weatherData.getJSONObject("temp");
                    double dayTemp = temp.getDouble("day");
                    double minTemp = temp.getDouble("min");
                    double maxTemp = temp.getDouble("max");

                    // 날씨 정보
                    JSONArray weatherArray = weatherData.getJSONArray("weather");
                    JSONObject weatherInfo = weatherArray.getJSONObject(0);
                    String weatherMain = weatherInfo.getString("main");
                    String weatherDescription = weatherInfo.getString("description");
                    String weatherIcon = weatherInfo.getString("icon");

                    // 기타 정보
                    int pressure = weatherData.getInt("pressure");
                    int humidity = weatherData.getInt("humidity");
                    double windSpeed = weatherData.getDouble("speed");
                    int clouds = weatherData.getInt("clouds");
                    double rain = weatherData.optDouble("rain", 0); // 비가 없을 수도 있으므로 기본값으로 0을 설정

                    OpenWeatherDTO openWeatherDTO = OpenWeatherDTO.builder()
                            .weatherCounty(weatherCounty)
                            .urlKRName(urlKRName[i])
                            .weatherName(weatherName)
                            .urlStringLat(urlStringLat[i])
                            .urlStringLon(urlStringLon[i])
                            .weatherDescription(weatherDescription)
                            .weatherIcon(weatherIcon)
                            .date(String.valueOf(date))
                            .dayTemp(String.valueOf(dayTemp - 273.15))
                            .maxTemp(String.valueOf(maxTemp - 273.15))
                            .minTemp(String.valueOf(minTemp - 273.15))
                            .humidity(String.valueOf(humidity))
                            .weatherMain(weatherMain)
                            .windSpeed(String.valueOf(windSpeed))
                            .clouds(String.valueOf(clouds))
                            .build();

                    Long tno = openWeatherService.register(openWeatherDTO);

                    log.info("Registered: " + openWeatherDTO);
                    log.info("---------------------------");
                }

                for (int j = 0; j < weatherList.length(); j++) {
                    JSONObject weatherData = weatherList.getJSONObject(j);

                    // 도시와 나라
                    String weatherName = weathercityInfo.getString("name");
                    String weatherCounty = weathercityInfo.getString("country");

                    // 날짜와 시간 정보
                    long dt = weatherData.getLong("dt");
                    Date date = new Date(dt * 1000); // Unix 시간을 일반적인 시간으로 변환

                    // 기온 정보
                    JSONObject temp = weatherData.getJSONObject("temp");
                    double dayTemp = temp.getDouble("day");
                    double minTemp = temp.getDouble("min");
                    double maxTemp = temp.getDouble("max");

                    // 날씨 정보
                    JSONArray weatherArray = weatherData.getJSONArray("weather");
                    JSONObject weatherInfo = weatherArray.getJSONObject(0);
                    String weatherMain = weatherInfo.getString("main");
                    String weatherDescription = weatherInfo.getString("description");
                    String weatherIcon = weatherInfo.getString("icon");

                    // 기타 정보
                    int pressure = weatherData.getInt("pressure");
                    int humidity = weatherData.getInt("humidity");
                    double windSpeed = weatherData.getDouble("speed");
                    int clouds = weatherData.getInt("clouds");
                    double rain = weatherData.optDouble("rain", 0); // 비가 없을 수도 있으므로 기본값으로 0을 설정

                    OpenWeatherDTO2 openWeatherDTO2 = OpenWeatherDTO2.builder()
                            .weatherCounty(weatherCounty)
                            .urlKRName(urlKRName[i])
                            .weatherName(weatherName)
                            .urlStringLat(urlStringLat[i])
                            .urlStringLon(urlStringLon[i])
                            .weatherDescription(weatherDescription)
                            .weatherIcon(weatherIcon)
                            .date(String.valueOf(date))
                            .dayTemp(String.valueOf(dayTemp - 273.15))
                            .maxTemp(String.valueOf(maxTemp - 273.15))
                            .minTemp(String.valueOf(minTemp - 273.15))
                            .humidity(String.valueOf(humidity))
                            .weatherMain(weatherMain)
                            .windSpeed(String.valueOf(windSpeed))
                            .clouds(String.valueOf(clouds))
                            .build();

                    Long tno = dataCopyService.registerOpenweather(openWeatherDTO2);

                    log.info("Registered: " + openWeatherDTO2);
                    log.info("---------------------------");
                }


            } catch (IOException | JSONException e) {
                log.error("Error fetching and registering weather data", e);
            }
        }
    }
}