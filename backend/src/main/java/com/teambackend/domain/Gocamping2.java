package com.teambackend.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Gocamping2")
public class Gocamping2 {

    @Id    //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    private String facltNm;
    private String addr1;
    private String lineIntro;
    @Column(length =2000)
    private String intro;
    private String tel;
    @Column(length =2000)
    private String featureNm;
    private String induty;
    private String lctCl;
    private String homepage;
    @Column(length =1000)
    private String resveUrl;
    @Column(length =1000)
    private String firstImageUrl;
    private String animalCmgCl; //애완동물출입
    private String eqpmnLendCl; //캠핑장비대여
    private String exprnProgrm; //체험프로그램명
    private String resveCl; //예약 구분
    private String operPdCl; //운영기간
    private String operDeCl; //운영일
    private String posblFcltyCl; //주변이용가능시설
    private String prmisnDe; //인허가일자
    private String sbrsCl; //부대시설
    private String brazierCl; //화로대
    private String toiletCo; //화장실 개수
    private String swrmCo; //샤워실 개수
    private String wtrplCo; //개수대 개수
    private String trlerAcmpnyAt; //개인 트레일러 동반 여부(Y:사용, N:미사용)
    private String caravAcmpnyAt; //개인 카라반 동반 여부(Y:사용, N:미사용)
    private String manageNmpr; //상주관리인원
    private String gnrlSiteCo; //주요시설 일반야영장
    private String autoSiteCo; //주요시설 자동차야영장
    private String glampSiteCo; //주요시설 글램핑
    private String caravSiteCo; //주요시설 카라반
    private String indvdlCaravSiteCo; //주요시설 개인 카라반
    private String siteBottomCl1; //잔디
    private String siteBottomCl2; //파쇄석
    private String siteBottomCl3; //테크
    private String siteBottomCl4; //자갈
    private String siteBottomCl5; //맨흙
    private String mapX; //경도
    private String mapY; //위도
    private String doNm; //시도
    private String sitedStnc; //사이트크기

    private String themaEnvrnCl; // 테마 환경
    private String contentId; // 콘텐츠 ID
    private String sigunguNm; // 시군구








}
