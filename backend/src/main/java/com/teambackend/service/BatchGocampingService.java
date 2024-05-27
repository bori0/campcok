package com.teambackend.service;

import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.GocampingDTO2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Log4j2
public class BatchGocampingService {

    @Autowired
    private Environment env;

    @Autowired
    private GocampingService gocampingService;

    @Autowired
    private DataCopyService dataCopyService;


    @PersistenceContext
    private EntityManager entityManager;

    // 실제 데이터를 등록하는 메서드 (가정)
    public Long register(GocampingDTO gocampingDTO) {
        // 데이터베이스에 데이터를 저장하는 로직 구현
        log.info("Registering: " + gocampingDTO);
        return 1L; // 등록된 ID 반환 (예시)
    }

    @Scheduled(fixedRate = 600000) // 600,000 milliseconds = 10 minutes
    //@Scheduled(cron = "0 5 0 * * *") // 매일 00시 05분에 실행
    @Transactional // 이 부분을 추가합니다.
    public void fetchAndRegisterGocampingData() {

        entityManager.flush(); // 강제로 변경사항을 데이터베이스에 반영
        entityManager.clear(); // 영속성 컨텍스트 초기화
        gocampingService.removeAll(); // 데이터베이스테이블삭제

        String campingUrl = env.getProperty("external.api.url");
        String serviceKey = env.getProperty("external.api.key");
        // 직접 URL 구성// API 요청에 사용할 URL을 구성
        String urlString = campingUrl + "?serviceKey=" + serviceKey
                + "&numOfRows=4000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            conn.disconnect();

            // JSON 파싱
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject responseObj = jsonObject.getJSONObject("response");
            JSONObject bodyObj = responseObj.getJSONObject("body");
            JSONArray itemsArray = bodyObj.getJSONObject("items").getJSONArray("item");

            // 각 아이템에 대해 처리
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);
                GocampingDTO gocampingDTO = GocampingDTO.builder()

                        .facltNm(item.getString("facltNm"))
                        .addr1(item.getString("addr1"))
                        .lineIntro(item.getString("lineIntro"))
                        .intro(item.getString("intro"))
                        .tel(item.getString("tel"))
                        .featureNm(item.getString("featureNm"))
                        .induty(item.getString("induty"))
                        .lctCl(item.getString("lctCl"))
                        .homepage(item.getString("homepage"))
                        .resveUrl(item.getString("resveUrl"))
                        .firstImageUrl(item.getString("firstImageUrl"))
                        .animalCmgCl(item.getString("animalCmgCl"))
                        .eqpmnLendCl(item.getString("eqpmnLendCl"))
                        .exprnProgrm(item.getString("exprnProgrm"))
                        .resveCl(item.getString("resveCl"))
                        .operPdCl(item.getString("operPdCl"))
                        .operDeCl(item.getString("operDeCl"))
                        .posblFcltyCl(item.getString("posblFcltyCl"))

                        .prmisnDe(item.getString("prmisnDe"))
                        .sbrsCl(item.getString("sbrsCl"))
                        .brazierCl(item.getString("brazierCl"))
                        .toiletCo(item.getString("toiletCo"))
                        .swrmCo(item.getString("swrmCo"))
                        .wtrplCo(item.getString("wtrplCo"))
                        .trlerAcmpnyAt(item.getString("trlerAcmpnyAt"))
                        .caravAcmpnyAt(item.getString("caravAcmpnyAt"))
                        .manageNmpr(item.getString("manageNmpr"))
                        .gnrlSiteCo(item.getString("gnrlSiteCo"))
                        .autoSiteCo(item.getString("autoSiteCo"))

                        .glampSiteCo(item.getString("glampSiteCo"))
                        .caravSiteCo(item.getString("caravSiteCo"))
                        .indvdlCaravSiteCo(item.getString("indvdlCaravSiteCo"))
                        .siteBottomCl1(item.getString("siteBottomCl1"))
                        .siteBottomCl2(item.getString("siteBottomCl2"))
                        .siteBottomCl3(item.getString("siteBottomCl3"))
                        .siteBottomCl4(item.getString("siteBottomCl4"))
                        .siteBottomCl5(item.getString("siteBottomCl5"))
                        .mapX(item.getString("mapX"))
                        .mapY(item.getString("mapY"))
                        .doNm(item.getString("doNm"))
                        .sitedStnc(item.getString("sitedStnc"))
                        .themaEnvrnCl(item.getString("themaEnvrnCl"))
                        .contentId(item.getString("contentId"))
                        .sigunguNm(item.getString("sigunguNm"))



//                        .induty(item.getString("induty"))
//                        .themaEnvrnCl(item.getString("themaEnvrnCl"))


//                        .contentId(item.getString("contentId"))
//                        .tourEraCl(item.getString("tourEraCl"))
//                        .allar(item.getString("allar"))
//                        .caravInnerFclty(item.getString("caravInnerFclty"))
//                        .glampInnerFclty(item.getString("glampInnerFclty"))
//                        .sbrsEtc(item.getString("sbrsEtc"))



                        .build();
                Long tno = gocampingService.register(gocampingDTO);

                log.info("Registered: " + gocampingDTO);
                log.info("---------------------------");
            }

            // 각 아이템에 대해 처리
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);
                GocampingDTO2 gocampingDTO2 = GocampingDTO2.builder()

                        .facltNm(item.getString("facltNm"))
                        .addr1(item.getString("addr1"))
                        .lineIntro(item.getString("lineIntro"))
                        .intro(item.getString("intro"))
                        .tel(item.getString("tel"))
                        .featureNm(item.getString("featureNm"))
                        .induty(item.getString("induty"))
                        .lctCl(item.getString("lctCl"))
                        .homepage(item.getString("homepage"))
                        .resveUrl(item.getString("resveUrl"))
                        .firstImageUrl(item.getString("firstImageUrl"))
                        .animalCmgCl(item.getString("animalCmgCl"))
                        .eqpmnLendCl(item.getString("eqpmnLendCl"))
                        .exprnProgrm(item.getString("exprnProgrm"))
                        .resveCl(item.getString("resveCl"))
                        .operPdCl(item.getString("operPdCl"))
                        .operDeCl(item.getString("operDeCl"))
                        .posblFcltyCl(item.getString("posblFcltyCl"))

                        .prmisnDe(item.getString("prmisnDe"))
                        .sbrsCl(item.getString("sbrsCl"))
                        .brazierCl(item.getString("brazierCl"))
                        .toiletCo(item.getString("toiletCo"))
                        .swrmCo(item.getString("swrmCo"))
                        .wtrplCo(item.getString("wtrplCo"))
                        .trlerAcmpnyAt(item.getString("trlerAcmpnyAt"))
                        .caravAcmpnyAt(item.getString("caravAcmpnyAt"))
                        .manageNmpr(item.getString("manageNmpr"))
                        .gnrlSiteCo(item.getString("gnrlSiteCo"))
                        .autoSiteCo(item.getString("autoSiteCo"))

                        .glampSiteCo(item.getString("glampSiteCo"))
                        .caravSiteCo(item.getString("caravSiteCo"))
                        .indvdlCaravSiteCo(item.getString("indvdlCaravSiteCo"))
                        .siteBottomCl1(item.getString("siteBottomCl1"))
                        .siteBottomCl2(item.getString("siteBottomCl2"))
                        .siteBottomCl3(item.getString("siteBottomCl3"))
                        .siteBottomCl4(item.getString("siteBottomCl4"))
                        .siteBottomCl5(item.getString("siteBottomCl5"))
                        .mapX(item.getString("mapX"))
                        .mapY(item.getString("mapY"))
                        .doNm(item.getString("doNm"))
                        .sitedStnc(item.getString("sitedStnc"))
                        .themaEnvrnCl(item.getString("themaEnvrnCl"))
                        .contentId(item.getString("contentId"))
                        .sigunguNm(item.getString("sigunguNm"))

//                        .induty(item.getString("induty"))
//                        .themaEnvrnCl(item.getString("themaEnvrnCl"))


//                        .contentId(item.getString("contentId"))
//                        .tourEraCl(item.getString("tourEraCl"))
//                        .allar(item.getString("allar"))
//                        .caravInnerFclty(item.getString("caravInnerFclty"))
//                        .glampInnerFclty(item.getString("glampInnerFclty"))
//                        .sbrsEtc(item.getString("sbrsEtc"))

                        .build();

                Long tno = dataCopyService.registerGocamping(gocampingDTO2);

                log.info("Registered: " + gocampingDTO2);
                log.info("---------------------------");
            }


        } catch (IOException | JSONException e) {
            log.error("Error fetching and registering data", e);
        }
    }
}