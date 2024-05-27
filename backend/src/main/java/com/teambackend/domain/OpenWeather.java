package com.teambackend.domain;


import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Openweather")
public class OpenWeather {


    @Id    //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
