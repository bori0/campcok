package com.teambackend.service;


import com.teambackend.domain.Gocamping;
import com.teambackend.domain.OpenWeather;
import com.teambackend.dto.*;
import com.teambackend.repository.GocampingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
public class GocampingServiceImpl implements GocampingService {

    @PersistenceContext
    private EntityManager entityManager;

    //자동 주입 대상은 final
    private final ModelMapper modelMapper;
    private final GocampingRepository gocampingRepository;


    //#1. 등록(insert)
    @Override
    public Long register(GocampingDTO gocampingDTO) {
        log.info("등록!!! .........");

        Gocamping gocamping = modelMapper.map(gocampingDTO, Gocamping.class);

        Gocamping saveGocamping = gocampingRepository.save(gocamping);

        return saveGocamping.getTno();
    }

    @Override
    public GocampingDTO get(Long tno) {



        Optional<Gocamping> result = gocampingRepository.findById(tno);
        Gocamping gocamping = result.orElseThrow();

        GocampingDTO dto = modelMapper.map(gocamping, GocampingDTO.class);

        return dto;
    }

    @Override
    public void modify(GocampingDTO gocampingDTO) {

    }

    @Override
    public void remove(Long tno) {

    }

    @Override
    public PageResponseDTO<GocampingDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending());

        Page<Gocamping> result = gocampingRepository.findAll(pageable);

        List<GocampingDTO> dtoList = result.getContent().stream()
                .map(gocamping -> modelMapper.map(gocamping, GocampingDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<GocampingDTO> responseDTO = PageResponseDTO.<GocampingDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }

    @Override
    public PageResponseDTO<GocampingDTO> getList(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("DELETE FROM Gocamping").executeUpdate();
    }

    //#
    public List<GocampingDTO> listAll() {
        // 모든 데이터를 가져옵니다.
        List<Gocamping> result = gocampingRepository.findAll();

        // 엔티티를 DTO로 변환합니다.
        List<GocampingDTO> dtoList = result.stream()
                .map(gocamping -> modelMapper.map(gocamping, GocampingDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

}

