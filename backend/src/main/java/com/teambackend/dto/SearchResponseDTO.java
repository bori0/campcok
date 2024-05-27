package com.teambackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class SearchResponseDTO<E> {
    // 검색 결과의 데이터 리스트
    private List<E> dtoList;
    // 페이지 번호 리스트
    private List<Integer> pageNumList;
    // 페이지 요청 정보
    private PageRequestDTO pageRequestDTO;
    // 이전 페이지, 다음 페이지가 있는지를 나타냄
    private boolean prev, next;

    private long totalElements;

    //총 검색 결과 개수, 이전 페이지 번호, 다음 페이지 번호, 전체 페이지 개수, 현재 페이지 번호
    private int totalCount, prevPage, nextPage, totalPage, current;

    //withALL: 빌더 메서드 이름을 "withAll"로 지정하여 'SearchResponseDTO' 객체 생성
    @Builder(builderMethodName = "withAll")
    public SearchResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount, long totalEliment) {
        // 검색 결과 리스트를 초기화
        this.dtoList = dtoList;
        // 페이지 요청 정보를 초기화
        this.pageRequestDTO = pageRequestDTO;
        // 총 검색 결과 개수를 초기화
        this.totalCount = (int)totalCount;

        this.totalElements = totalEliment;


        // 현재 페이지 그룹의 끝 페이지 번호 한 페이지 그룹은 10개로 구성
        int end =   (int)(Math.ceil( pageRequestDTO.getPage() / 10.0 )) *  10;
        // 시작 페이지 계산
        int start = end - 9;
        // 전체 페이지의 마지막 번호
        int last =  (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));
        end =  end > last ? last: end;
        // 이전 페이지 존재 여부 start가 1보다 크면 이전 페이지가 있다.
        this.prev = start > 1;
        // 다음 페이지 존재 여부, 현재 페이지 그룹의 끝 페이지가 마지막 페이지보다 작으면 다음 페이지가 있다.
        this.next =  totalCount > end * pageRequestDTO.getSize();
        //페이지 번호 리스트 생성, 'IntStream'을 사용하여 'start'부터 'end' 까지의 페이지 번호 리스트 생성
        this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
        //이전 페이지 번호 설정, 이전 페이지가 있으면 prevPage를 설정
        if(prev) {
            this.prevPage = start -1;
        }
        //다음 페이지 번호 설정, 다음 페이지가 있으면 'nextPage'를 설정
        if(next) {
            this.nextPage = end + 1;
        }
        // 전체 페이지 수와 현재 페이지 번호 설정
        this.totalPage = this.pageNumList.size();
        this.current = pageRequestDTO.getPage();

    }
    public SearchResponseDTO(List<E> dtoList, long totalCount) {
        this.dtoList = dtoList;
        this.totalCount = (int)totalCount;
    }



}
