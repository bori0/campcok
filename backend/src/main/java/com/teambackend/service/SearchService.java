package com.teambackend.service;


import com.teambackend.domain.Gocamping;
import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.PageRequestDTO;
import com.teambackend.dto.SearchResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SearchService {
    //목록데이터
    SearchResponseDTO<Gocamping> getList(PageRequestDTO pageRequestDTO);

    // 서비스 조회 기능 처리
    GocampingDTO get(String contentId);

    SearchResponseDTO<Gocamping> getbeach(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getisland(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getmountain(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getforest(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getvalley(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getriver(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getlake(PageRequestDTO pageRequestDTO);

    SearchResponseDTO<Gocamping> getdowntown(PageRequestDTO pageRequestDTO);

}
