package com.teambackend.service;


import com.teambackend.domain.Gocamping;
import com.teambackend.dto.*;
import com.teambackend.repository.CamcokRepository;
import com.teambackend.specifications.DetailSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DetailServiceImpl implements DetailService{
    @Autowired
    private CamcokRepository camcokRepository;
    private List<Gocamping> globalRegionFilteredData = new ArrayList<>();

    @Override
    public DetailResponseDTO<Gocamping> searchData(SearchDTO searchDTO, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  // 페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending()
        );

        String searchName = searchDTO.getFacltNm();  // 검색어 추출

        // 검색어가 null인 경우 빈 문자열로 초기화
        if (searchName == null) {
            searchName = "";
        }

        // 쿼리 실행
        Page<Gocamping> result = camcokRepository.findByFacltNmLike(searchName, pageable);

        // 결과 처리
        List<Gocamping> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        // 데이터 넣기
        return DetailResponseDTO.<Gocamping>withAllDetail()
                .dtoList(dtoList)
                .totalCount(totalElements)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public DetailResponseDTO<Gocamping> getList(PageRequestDTO pageRequestDTO) {

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
        return DetailResponseDTO.<Gocamping>withAllDetail()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public DetailResponseDTO<Gocamping> regionData(RegionDTO regionDTO, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  // 페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending()
        );

        String doName = regionDTO.getDoNm();  // 검색어 추출
        String sigunguName = regionDTO.getSigunguNm();  // 검색어 추출

        // 검색어가 null인 경우 빈 문자열로 초기화
        if (doName == null) {
            doName = "";
        }
        if (sigunguName == null) {
            sigunguName = "";
        }

        Page<Gocamping> result = null;
        List<Gocamping> result2 = null;
        if ("전국".equals(doName)) {
            // doName이 "전국"인 경우 모든 데이터를 반환
            result = camcokRepository.findAll(pageable);
            result2 = camcokRepository.findAll();
        } else {
            // doName과 sigunguName에 따라 필터링된 데이터를 반환
            result = camcokRepository.findByRegionLike(doName, sigunguName, pageable);
            result2 = camcokRepository.findByRegionLike2(doName, sigunguName);
        }

        // 결과 처리
        List<Gocamping> dtoList = result.getContent();
        globalRegionFilteredData = result2;
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        // 데이터 넣기
        return DetailResponseDTO.<Gocamping>withAllDetail()
                .dtoList(dtoList)
                .totalCount(totalElements)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public DetailResponseDTO<Gocamping> findByDetailFilters(DetailDTO detailDTO, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  // 페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending()
        );

        List<String> lctCls = new ArrayList<>();
        List<String> indutys = new ArrayList<>();
        List<String> sbrscls = new ArrayList<>();
        List<String> themaenvrncls = new ArrayList<>();
        Integer siteBottomCl1 = null;
        Integer siteBottomCl2 = null;
        Integer siteBottomCl3 = null;
        Integer siteBottomCl4 = null;
        Integer siteBottomCl5 = null;
        String trlerAcmpnyAt = null;
        String caravAcmpnyAt = null;
        String animalCmgCls = null;

        // 필터링 조건 생성
        if (detailDTO.getD200().isD_d201()) lctCls.add("해변");
        if (detailDTO.getD200().isD_d202()) lctCls.add("섬");
        if (detailDTO.getD200().isD_d203()) lctCls.add("산");
        if (detailDTO.getD200().isD_d204()) lctCls.add("숲");
        if (detailDTO.getD200().isD_d205()) lctCls.add("계곡");
        if (detailDTO.getD200().isD_d206()) lctCls.add("강");
        if (detailDTO.getD200().isD_d207()) lctCls.add("호수");
        if (detailDTO.getD200().isD_d208()) lctCls.add("도심");

        if (detailDTO.getD300().isD_d301()) indutys.add("일반야영장");
        if (detailDTO.getD300().isD_d302()) indutys.add("글램핑");
        if (detailDTO.getD300().isD_d303()) indutys.add("카라반");
        if (detailDTO.getD300().isD_d304()) indutys.add("자동차야영장");

        if (detailDTO.getD400().isD_d401()) siteBottomCl1 = 1;
        if (detailDTO.getD400().isD_d402()) siteBottomCl2 = 1;
        if (detailDTO.getD400().isD_d403()) siteBottomCl3 = 1;
        if (detailDTO.getD400().isD_d404()) siteBottomCl4 = 1;
        if (detailDTO.getD400().isD_d405()) siteBottomCl5 = 1;

        if (detailDTO.getD500().isD_d501()) themaenvrncls.add("일출명소");
        if (detailDTO.getD500().isD_d502()) themaenvrncls.add("일몰명소");
        if (detailDTO.getD500().isD_d503()) themaenvrncls.add("수상레저");
        if (detailDTO.getD500().isD_d504()) themaenvrncls.add("항공레저");
        if (detailDTO.getD500().isD_d505()) themaenvrncls.add("스키");
        if (detailDTO.getD500().isD_d506()) themaenvrncls.add("낚시");
        if (detailDTO.getD500().isD_d507()) themaenvrncls.add("액티비티");
        if (detailDTO.getD500().isD_d508()) themaenvrncls.add("봄꽃여행");
        if (detailDTO.getD500().isD_d509()) themaenvrncls.add("여름물놀이");
        if (detailDTO.getD500().isD_d5010()) themaenvrncls.add("가을단풍명소");
        if (detailDTO.getD500().isD_d5011()) themaenvrncls.add("겨울눈꽃명소");

        if (detailDTO.getD600().isD_d601()) sbrscls.add("전기");
        if (detailDTO.getD600().isD_d602()) sbrscls.add("무선인터넷");
        if (detailDTO.getD600().isD_d603()) sbrscls.add("장작판매");
        if (detailDTO.getD600().isD_d604()) sbrscls.add("온수");
        if (detailDTO.getD600().isD_d605()) sbrscls.add("트렘폴린");
        if (detailDTO.getD600().isD_d606()) sbrscls.add("물놀이장");
        if (detailDTO.getD600().isD_d607()) sbrscls.add("놀이터");
        if (detailDTO.getD600().isD_d608()) sbrscls.add("산책로");
        if (detailDTO.getD600().isD_d609()) sbrscls.add("운동장");
        if (detailDTO.getD600().isD_d6010()) sbrscls.add("운동시설");
        if (detailDTO.getD600().isD_d6011()) sbrscls.add("편의점");
        if (detailDTO.getD600().isD_d6012()) sbrscls.add("덤프스테이션");

        if (detailDTO.getD700().isD_d701()) trlerAcmpnyAt = "Y";
        if (detailDTO.getD700().isD_d702()) caravAcmpnyAt = "Y";
        if (detailDTO.getD700().isD_d703()) animalCmgCls = "가능";

        // Specification 조합
        Specification<Gocamping> spec = Specification
                .where(DetailSpecifications.lctClsIn(lctCls))
                .and(DetailSpecifications.indutiesIn(indutys))
                .and(DetailSpecifications.siteBottomClGreaterThanOrEqualTo(siteBottomCl1, "siteBottomCl1"))
                .and(DetailSpecifications.siteBottomClGreaterThanOrEqualTo(siteBottomCl2, "siteBottomCl2"))
                .and(DetailSpecifications.siteBottomClGreaterThanOrEqualTo(siteBottomCl3, "siteBottomCl3"))
                .and(DetailSpecifications.siteBottomClGreaterThanOrEqualTo(siteBottomCl4, "siteBottomCl4"))
                .and(DetailSpecifications.siteBottomClGreaterThanOrEqualTo(siteBottomCl5, "siteBottomCl5"))
                .and(DetailSpecifications.themaEnvrnClsIn(themaenvrncls))
                .and(DetailSpecifications.sbrsClsIn(sbrscls))
                .and(DetailSpecifications.trlerAcmpnyAtEqual(trlerAcmpnyAt))
                .and(DetailSpecifications.caravAcmpnyAtEqual(caravAcmpnyAt))
                .and(DetailSpecifications.animalCmgClsLike(animalCmgCls));

        if (!globalRegionFilteredData.isEmpty()) {
            // regionData로 필터링된 결과가 있는 경우 해당 결과로 추가 필터링
            Specification<Gocamping> regionSpec = DetailSpecifications.regionFilteredIn(globalRegionFilteredData);
            spec = spec.and(regionSpec);
        }

        // 결과 조회
        Page<Gocamping> resultPage = camcokRepository.findAll(spec, pageable);

        long totalElements = resultPage.getTotalElements();
        long totalCount = resultPage.getTotalElements();

        return DetailResponseDTO.<Gocamping>withAllDetail()
                .dtoList(resultPage.getContent())
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

}
