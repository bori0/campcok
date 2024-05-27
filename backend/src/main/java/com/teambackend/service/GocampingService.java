package com.teambackend.service;

import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.OpenWeatherDTO;
import com.teambackend.dto.PageRequestDTO;
import com.teambackend.dto.PageResponseDTO;

import java.util.List;

public interface GocampingService {

    //crud, pageing
    //#1. 등록 기능
    Long register(GocampingDTO gocampingDTO);

    //#2. 조회
    GocampingDTO get(Long tno);


    //#3. 업데이트(=갱신)
    void modify(GocampingDTO gocampingDTO);

    //#4. 삭제
    void remove(Long tno);

    //#5. 페이징
    PageResponseDTO<GocampingDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<GocampingDTO> getList(PageRequestDTO pageRequestDTO);


    //#6. 테이블삭제
    void removeAll();

    //#7.노페이징
    List<GocampingDTO> listAll();

}
