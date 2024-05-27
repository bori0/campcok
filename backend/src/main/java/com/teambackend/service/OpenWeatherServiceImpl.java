package com.teambackend.service;

import com.teambackend.domain.OpenWeather;
import com.teambackend.dto.*;
import com.teambackend.repository.OpenWeatherRepository;
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
public class OpenWeatherServiceImpl implements OpenWeatherService {


    @PersistenceContext
    private EntityManager entityManager;

    //자동 주입 대상은 final
    private final ModelMapper modelMapper;
    private final OpenWeatherRepository openWeatherRepository;

    @Override
    public Long register(OpenWeatherDTO openWeatherDTO) {
        log.info("등록!!! .........");

        OpenWeather openWeather = modelMapper.map(openWeatherDTO, OpenWeather.class);

        OpenWeather saveOpenWeather = openWeatherRepository.save(openWeather);

        return saveOpenWeather.getTno();
    }

    @Override
    public OpenWeatherDTO get2(Long tno) {

        Optional<OpenWeather> result = openWeatherRepository.findById(tno);
        //Optional :래퍼 클래스
        //          이것을 사용하지 않으면 매번 if문을 이욯새 null인지 체크한 후 사용
        //          이것을 이용하면 null 체크를 위한 if문 없이도 NullPointerException이 발생하지
        //          않음


        OpenWeather openWeather = result.orElseThrow();

        OpenWeatherDTO openWeatherDTO = modelMapper.map(openWeather, OpenWeatherDTO.class);

        return openWeatherDTO;
    }

    @Override
    public void modify(OpenWeatherDTO openWeatherDTO) {

    }


    @Override
    public void remove(Long tno) {

    }

    @Override
    public PageResponseDTO<OpenWeatherDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending());

        Page<OpenWeather> result = openWeatherRepository.findAll(pageable);

        List<OpenWeatherDTO> dtoList = result.getContent().stream()
                .map(openWeather -> modelMapper.map(openWeather, OpenWeatherDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<OpenWeatherDTO> responseDTO = PageResponseDTO.<OpenWeatherDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }


    @Override
    public PageResponseDTO<OpenWeatherDTO> getList(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("DELETE FROM OpenWeather ").executeUpdate();
    }


    public List<OpenWeatherDTO> listAll() {
        // 모든 데이터를 가져옵니다.
        List<OpenWeather> result = openWeatherRepository.findAll();

        // 엔티티를 DTO로 변환합니다.
        List<OpenWeatherDTO> dtoList = result.stream()
                .map(openWeather -> modelMapper.map(openWeather, OpenWeatherDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }


}
