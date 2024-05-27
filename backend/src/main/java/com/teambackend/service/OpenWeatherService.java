package com.teambackend.service;

import com.teambackend.dto.*;

import java.util.List;

public interface OpenWeatherService {

    //crud, pageing
    //#1. 등록 기능
    Long register(OpenWeatherDTO openWeatherDTO);

    //#2. 조회
    OpenWeatherDTO get2(Long tno);


    //#3. 업데이트(=갱신)
    void modify(OpenWeatherDTO openWeatherDTO);

    //#4. 삭제
    void remove(Long tno);


    //#5. 페이징
    PageResponseDTO<OpenWeatherDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<OpenWeatherDTO> getList(PageRequestDTO pageRequestDTO);

    //#6. 테이블삭제
    void removeAll();

    //#7.노페이징
    List<OpenWeatherDTO> listAll();

}
