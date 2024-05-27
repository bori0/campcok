package com.teambackend.repository;

import com.teambackend.domain.OpenWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenWeatherRepository extends JpaRepository<OpenWeather, Long> {

}