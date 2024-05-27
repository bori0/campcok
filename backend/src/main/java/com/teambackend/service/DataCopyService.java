package com.teambackend.service;

import com.teambackend.dto.GocampingDTO2;
import com.teambackend.dto.OpenWeatherDTO;
import com.teambackend.dto.OpenWeatherDTO2;

public interface DataCopyService {

    //#1. 등록 기능
    Long registerGocamping(GocampingDTO2 gocampingDTO2);

    Long registerOpenweather(OpenWeatherDTO2 openWeatherDTO2);
}
