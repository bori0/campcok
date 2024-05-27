package com.teambackend.service;


import com.teambackend.domain.Gocamping;
import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.PageRequestDTO;
import com.teambackend.dto.SearchResponseDTO;
import com.teambackend.repository.CamcokRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SearchServiceImpl implements SearchService{

    private  final CamcokRepository camcokRepository;
    @Override
    public SearchResponseDTO<Gocamping> getList(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectList(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public GocampingDTO get(String contentId) {
        return null;
    }


    @Override
    public SearchResponseDTO<Gocamping> getbeach(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectbeach(pageable);
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기
        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getisland(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectisland(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getmountain(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectmountain(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getforest(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectforest(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getvalley(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectvalley(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getriver(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectriver(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getlake(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectlake(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public SearchResponseDTO<Gocamping> getdowntown(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<Gocamping> result = camcokRepository.selectdowntown(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<Gocamping>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }


}
