//package com.teambackend.api;
//
//
//import com.teambackend.dto.GocampingDTO;
//import com.teambackend.dto.OpenWeatherDTO;
//import com.teambackend.service.GocampingService;
//import com.teambackend.service.OpenWeatherService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Date;
//
//@RestController
//@RequiredArgsConstructor
//@Log4j2
//@RequestMapping("/openWeather")
//public class OpenWeatherAPI {
//
//
//
//    @Autowired
//    private OpenWeatherService openWeatherService;
//
//    @GetMapping("/list/1")
//    public void testRegister1() {
//
//        String[] urlKRName = {"인천", "서울", "춘천", "강릉", "수원", "홍성", "청주", "안동", "대전", "포항", "전주", "대구", "울산", "목포", "광주", "여수", "창원", "부산", "제주"};
//
//
//
//        String urlStringDefalut = "https://pro.openweathermap.org/data/2.5/forecast/climate?" ;
//        String[] urlStringLat = {"37.45", "37.5683", "37.8747", "37.7556", "37.2911", "36.6009", "36.6372", "36.5656", "36.3333", "36.0322", "35.8219", "35.8703", "35.5372", "34.7936", "35.1547", "34.7546", "35.2281", "35.1028", "33.5097"};
//        String[] urlStringLon = {"126.4161", "126.9778", "127.7342", "128.8961", "127.0089", "126.665", "127.4897", "128.725", "127.4167", "129.365", "127.1489", "128.5911", "129.3167", "126.3886", "126.9156", "127.6599", "128.6811", "129.0403", "126.5219"};
//        String urlStringCnt = "30";
//        String urlStringappid = [키입력];
//        String[] urlString = new String[urlStringLat.length];
//
//        for(int i = 0 ; i < urlStringLat.length ; i++){
//            urlString[i] = urlStringDefalut + "lat="+urlStringLat[i]+"&" +"lon=" + urlStringLon[i] + "&" + "cnt=" +urlStringCnt+ "&" + urlStringappid ;
//
//
//            try {
//                URL url = new URL(urlString[i]);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Accept", "application/json");
//
//                if (conn.getResponseCode() != 200) {
//                    throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
//                }
//
//                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//                StringBuilder response = new StringBuilder();
//                String output;
//                while ((output = br.readLine()) != null) {
//                    response.append(output);
//                }
//
//                conn.disconnect();
//
//                // JSON 파싱
//                JSONObject jsonObject = new JSONObject(response.toString());
//
//                JSONObject weathercityInfo = jsonObject.getJSONObject("city");
//                JSONArray weatherList = jsonObject.getJSONArray("list");
//                for (int j = 0; j < weatherList.length(); j++) {
//                    JSONObject weatherData = weatherList.getJSONObject(j);
//
//                    //도시와 나라
//                    String weatherName = weathercityInfo.getString("name");
//                    String weatherCounty = weathercityInfo.getString("country");
//
//                    // 날짜와 시간 정보
//                    long dt = weatherData.getLong("dt");
//                    Date date = new Date(dt * 1000); // Unix 시간을 일반적인 시간으로 변환
//
//                    // 기온 정보
//                    JSONObject temp = weatherData.getJSONObject("temp");
//                    double dayTemp = temp.getDouble("day");
//                    double minTemp = temp.getDouble("min");
//                    double maxTemp = temp.getDouble("max");
//
//                    // 날씨 정보
//                    JSONArray weatherArray = weatherData.getJSONArray("weather");
//                    JSONObject weatherInfo = weatherArray.getJSONObject(0);
//                    String weatherMain = weatherInfo.getString("main");
//                    String weatherDescription = weatherInfo.getString("description");
//                    String weatherIcon = weatherInfo.getString("icon");
//
//                    // 기타 정보
//                    int pressure = weatherData.getInt("pressure");
//                    int humidity = weatherData.getInt("humidity");
//                    double windSpeed = weatherData.getDouble("speed");
//                    int clouds = weatherData.getInt("clouds");
//                    double rain = weatherData.optDouble("rain", 0); // 비가 없을 수도 있으므로 기본값으로 0을 설정
//
//                    // 해당 정보를 활용하여 작업을 수행하거나, 필요에 따라 객체에 저장할 수 있습니다.
////
////                    System.out.println("------------dt : " + dt);
////
////                    System.out.println("------------dt : " + dt);
////                    System.out.println("------------date : " + date);
////                    System.out.println("------------dayTemp : " + dayTemp);
////                    System.out.println("------------minTemp : " + minTemp);
////                    System.out.println("------------maxTemp : " + maxTemp);
////
////                    System.out.println("------------weatherMain : " + weatherMain);
////                    System.out.println("------------weatherDescription : " + weatherDescription);
////                    System.out.println("------------weatherIcon : " + weatherIcon);
////
////
////                    System.out.println("------------pressure : " + pressure);
////                    System.out.println("------------humidity : " + humidity);
////                    System.out.println("------------windSpeed : " + windSpeed);
////                    System.out.println("------------clouds : " + clouds);
////                    System.out.println("------------rain : " + rain);
//
//                    System.out.println("--------------------------------");
//
//                    System.out.println("------------나라  : " + weatherCounty);
//                    System.out.println("------------도시이름_kr : " + urlKRName[i]);
//                    System.out.println("------------도시이름 : " + weatherName);
//                    System.out.println("------------도시위도 : " + urlStringLat[i]);
//                    System.out.println("------------도시경도 : " + urlStringLon[i]);
//
//
//                    System.out.println("------------날짜 : " + date);
//
//                    System.out.println("------------상세날씨설명 : " + weatherDescription);
//                    System.out.println("------------날씨 이미지 : " + weatherIcon);
//
//                    System.out.println("------------현재온도 : " + (dayTemp - 273.15));
//                    System.out.println("------------현재최고온도 : " + (maxTemp - 273.15));
//                    System.out.println("------------현재최저온도 : " + (minTemp - 273.15));
//
//                    System.out.println("------------현재습도 : " + humidity);
//                    System.out.println("------------날씨 : " + weatherMain);
//                    System.out.println("------------바람 : " + windSpeed);
//                    System.out.println("------------구름  : " + clouds);
//
//
//                    //                console.log("현재온도 : "+ (resp.main.temp- 273.15) );
//                    //                console.log("현재습도 : "+ resp.main.humidity);
//                    //                console.log("날씨 : "+ resp.weather[0].main );
//                    //                console.log("상세날씨설명 : "+ resp.weather[0].description );
//                    //                console.log("날씨 이미지 : "+ resp.weather[0].icon );
//                    //                console.log("바람   : "+ resp.wind.speed );
//                    //                console.log("나라   : "+ resp.sys.country );
//                    //                console.log("도시이름  : "+ resp.name );
//                    //                console.log("구름  : "+ (resp.clouds.all) +"%" );
//
//
//                    /*     .date(date)*/
//                    OpenWeatherDTO openWeatherDTO = OpenWeatherDTO.builder()
//
//                            .weatherCounty(weatherCounty)
//                            .urlKRName(urlKRName[i])
//                            .weatherName(weatherName)
//                            .urlStringLat(urlStringLat[i])
//                            .urlStringLon(urlStringLon[i])
//                            .weatherDescription(weatherDescription)
//                            .weatherIcon(weatherIcon)
//                            .date(String.valueOf(date))
//                            .dayTemp(String.valueOf(dayTemp- 273.15))
//                            .maxTemp(String.valueOf(maxTemp- 273.15))
//                            .minTemp(String.valueOf(minTemp- 273.15))
//                            .humidity(String.valueOf(humidity))
//                            .weatherMain(weatherMain)
//                            .windSpeed(String.valueOf(windSpeed))
//                            .clouds(String.valueOf(clouds))
//                            .build();
//
//                    Long tno = openWeatherService.register(openWeatherDTO);
//
//                    System.out.println("------------나라  : " + weatherCounty);
//                    System.out.println("------------도시이름_kr : " + urlKRName[i]);
//                    System.out.println("------------도시이름 : " + weatherName);
//                    System.out.println("------------도시위도 : " + urlStringLat[i]);
//                    System.out.println("------------도시경도 : " + urlStringLon[i]);
//
//
//                    System.out.println("------------날짜 : " + date);
//
//                    System.out.println("------------상세날씨설명 : " + weatherDescription);
//                    System.out.println("------------날씨 이미지 : " + weatherIcon);
//
//                    System.out.println("------------현재온도 : " + (dayTemp - 273.15));
//                    System.out.println("------------현재최고온도 : " + (maxTemp - 273.15));
//                    System.out.println("------------현재최저온도 : " + (minTemp - 273.15));
//
//                    System.out.println("------------현재습도 : " + humidity);
//                    System.out.println("------------날씨 : " + weatherMain);
//                    System.out.println("------------바람 : " + windSpeed);
//                    System.out.println("------------구름  : " + clouds);
//
//
//
//                }
//
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//
//
//
//
//    }
//
//
//}