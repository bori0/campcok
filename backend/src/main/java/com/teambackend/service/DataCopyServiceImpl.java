package com.teambackend.service;

import com.teambackend.domain.Gocamping;
import com.teambackend.domain.Gocamping2;
import com.teambackend.domain.OpenWeather2;
import com.teambackend.dto.*;
import com.teambackend.repository.Gocamping2Repository;
import com.teambackend.repository.GocampingRepository;
import com.teambackend.repository.OpenWeather2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor  //생성자 자동 주입
public class DataCopyServiceImpl  implements DataCopyService {

    //자동 주입 대상은 final
    private final ModelMapper modelMapper;
    private final Gocamping2Repository gocamping2Repository;
    private final OpenWeather2Repository openWeather2Repository;


    //#1. 등록(insert)
    @Override
    public Long registerGocamping(GocampingDTO2 gocampingDTO2) {
        log.info("등록!!! .........");

        Gocamping2 gocamping2 = modelMapper.map(gocampingDTO2, Gocamping2.class);

        Gocamping2 saveGocamping2 = gocamping2Repository.save(gocamping2);

        return saveGocamping2.getTno();
    }

    @Override
    public Long registerOpenweather(OpenWeatherDTO2 openWeatherDTO2) {
        log.info("등록!!! .........");

        OpenWeather2 openWeather2 = modelMapper.map(openWeatherDTO2, OpenWeather2.class);

        OpenWeather2 saveOpenweather2 = openWeather2Repository.save(openWeather2);

        return saveOpenweather2.getTno();
    }



}