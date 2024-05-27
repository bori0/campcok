package com.teambackend.service;


import com.teambackend.domain.Gocamping;
import com.teambackend.dto.*;


public interface DetailService {
    DetailResponseDTO<Gocamping> searchData(SearchDTO searchDTO, PageRequestDTO pageRequestDTO);

    DetailResponseDTO<Gocamping> getList(PageRequestDTO pageRequestDTO);

   DetailResponseDTO<Gocamping> findByDetailFilters(DetailDTO detailDTO, PageRequestDTO pageRequestDTO);

    DetailResponseDTO<Gocamping> regionData(RegionDTO regionDTO, PageRequestDTO pageRequestDTO);
}
