//package com.teambackend.api;
//
//import com.teambackend.dto.GocampingDTO;
//import com.teambackend.service.GocampingService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//@RestController
//@RequiredArgsConstructor
//@Log4j2
//@RequestMapping("/gocamping")
//public class GocampingAPI {
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private GocampingService gocampingService;
//
//    @GetMapping("/list/1")
//    public void testRegister1() {
//
//        String campingUrl = env.getProperty("external.api.url");
//        String serviceKey = env.getProperty("external.api.key");
//        // 직접 URL 구성// API 요청에 사용할 URL을 구성
//        String urlString = campingUrl + "?serviceKey=" + serviceKey
//                + "&numOfRows=3826&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
//
//
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//            StringBuilder response = new StringBuilder();
//            String output;
//            while ((output = br.readLine()) != null) {
//                response.append(output);
//            }
//
//            conn.disconnect();
//
//            // JSON 파싱
//            JSONObject jsonObject = new JSONObject(response.toString());
//            JSONObject responseObj = jsonObject.getJSONObject("response");
//            JSONObject bodyObj = responseObj.getJSONObject("body");
//            JSONArray itemsArray = bodyObj.getJSONObject("items").getJSONArray("item");
//
//
//            // 각 아이템에 대해 처리
//            for (int i = 0; i < itemsArray.length(); i++) {
//                JSONObject item = itemsArray.getJSONObject(i);
//
//                GocampingDTO gocampingDTO = GocampingDTO.builder()
//
//                        .facltNm(item.getString("facltNm"))
//                        .addr1(item.getString("addr1"))
//                        .lineIntro(item.getString("lineIntro"))
//                        .intro(item.getString("intro"))
//                        .tel(item.getString("tel"))
//                        .featureNm(item.getString("featureNm"))
//                        .induty(item.getString("induty"))
//                        .lctCl(item.getString("lctCl"))
//                        .homepage(item.getString("homepage"))
//                        .resveUrl(item.getString("resveUrl"))
//                        .firstImageUrl(item.getString("firstImageUrl"))
//                        .animalCmgCl(item.getString("animalCmgCl"))
//                        .eqpmnLendCl(item.getString("eqpmnLendCl"))
//                        .exprnProgrm(item.getString("exprnProgrm"))
//                        .resveCl(item.getString("resveCl"))
//                        .operPdCl(item.getString("operPdCl"))
//                        .operDeCl(item.getString("operDeCl"))
//                        .posblFcltyCl(item.getString("posblFcltyCl"))
//
//                        .prmisnDe(item.getString("prmisnDe"))
//                        .sbrsCl(item.getString("sbrsCl"))
//                        .brazierCl(item.getString("brazierCl"))
//                        .toiletCo(item.getString("toiletCo"))
//                        .swrmCo(item.getString("swrmCo"))
//                        .wtrplCo(item.getString("wtrplCo"))
//                        .trlerAcmpnyAt(item.getString("trlerAcmpnyAt"))
//                        .caravAcmpnyAt(item.getString("caravAcmpnyAt"))
//                        .manageNmpr(item.getString("manageNmpr"))
//                        .gnrlSiteCo(item.getString("gnrlSiteCo"))
//                        .autoSiteCo(item.getString("autoSiteCo"))
//
//                        .glampSiteCo(item.getString("glampSiteCo"))
//                        .caravSiteCo(item.getString("caravSiteCo"))
//                        .indvdlCaravSiteCo(item.getString("indvdlCaravSiteCo"))
//                        .siteBottomCl1(item.getString("siteBottomCl1"))
//                        .siteBottomCl2(item.getString("siteBottomCl2"))
//                        .siteBottomCl3(item.getString("siteBottomCl3"))
//                        .siteBottomCl4(item.getString("siteBottomCl4"))
//                        .siteBottomCl5(item.getString("siteBottomCl5"))
//                        .mapX(item.getString("mapX"))
//                        .mapY(item.getString("mapY"))
//
//                        .build();
//                Long tno = gocampingService.register(gocampingDTO);
//
//
//
////                JSONObject item = itemsArray.getJSONObject(i);
////                String facltNm = item.getString("facltNm"); //이름
////                String addr1 = item.getString("addr1"); //주소
////                String lineIntro = item.getString("lineIntro"); //한줄소개
////                String intro = item.getString("intro"); //소개
////                String tel = item.getString("tel"); //번호
////
////
////
////                String featureNm = item.getString("featureNm"); //특징
////                String teindutyl = item.getString("induty"); //업종
////                String lctCl = item.getString("lctCl"); //입지구분
////
////                String homepage = item.getString("homepage"); //홈페이지
////                String resveUrl = item.getString("resveUrl"); //예약 페이지
////
////                String firstImageUrl = item.getString("firstImageUrl"); //예약이미지
//
//
//                System.out.println("---------------------------");
//            }
//
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//}