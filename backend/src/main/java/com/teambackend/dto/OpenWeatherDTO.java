package com.teambackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherDTO {

    private Long tno;

    private String weatherCounty;
    private String urlKRName;
    private String weatherName;
    private String urlStringLat;
    private String urlStringLon;
    private String date;
    private String weatherDescription;
    private String weatherIcon;

    private String dayTemp;
    private String maxTemp;
    private String minTemp;

    private String humidity;
    private String weatherMain;
    private String windSpeed;
    private String clouds;


}
