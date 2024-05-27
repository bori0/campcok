package com.teambackend.repository;

import com.teambackend.domain.OpenWeather;
import com.teambackend.domain.OpenWeather2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenWeather2Repository extends JpaRepository<OpenWeather2, Long> {

}