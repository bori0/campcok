package com.teambackend.controller;


import com.teambackend.domain.Gocamping;
import com.teambackend.dto.*;
import com.teambackend.service.DetailService;
import com.teambackend.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:3000")
public class CamcokSearchController {
    private final SearchService searchService;
    private final DetailService detailService;
    private DetailDTO savedDetailDTO;
    private SearchDTO saveSearchDTO;

    private RegionDTO saveRegionDTO;

    @GetMapping(value = "/list", produces = "application/json")
    public SearchResponseDTO<Gocamping> list(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getList(pageRequestDTO);
    }

    @PostMapping(value = "/details", produces = "application/json")
    public ResponseEntity<?> receiveDetails(@RequestBody DetailDTO detailDTO) {
        log.info("Received DetailDTO: {}", detailDTO);
        savedDetailDTO = detailDTO;
        return ResponseEntity.ok().body(detailDTO);
    }

    @GetMapping(value = "/list/details", produces = "application/json")
    public DetailResponseDTO<Gocamping> detailList(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return detailService.findByDetailFilters(savedDetailDTO, pageRequestDTO);
    }

    @GetMapping(value = "/list/beach", produces = "application/json")
    public SearchResponseDTO<Gocamping> beach(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getbeach(pageRequestDTO);
    }

    @GetMapping(value = "/list/island", produces = "application/json")
    public SearchResponseDTO<Gocamping> island(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getisland(pageRequestDTO);
    }

    @GetMapping(value = "/list/mountain", produces = "application/json")
    public SearchResponseDTO<Gocamping> mountain(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getmountain(pageRequestDTO);
    }

    @GetMapping(value = "/list/forest", produces = "application/json")
    public SearchResponseDTO<Gocamping> forest(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getforest(pageRequestDTO);
    }

    @GetMapping(value = "/list/valley", produces = "application/json")
    public SearchResponseDTO<Gocamping> valley(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getvalley(pageRequestDTO);
    }

    @GetMapping(value = "/list/river", produces = "application/json")
    public SearchResponseDTO<Gocamping> river(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getriver(pageRequestDTO);
    }

    @GetMapping(value = "/list/lake", produces = "application/json")
    public SearchResponseDTO<Gocamping> lake(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getlake(pageRequestDTO);
    }

    @GetMapping(value = "/list/downtown", produces = "application/json")
    public SearchResponseDTO<Gocamping> downtown(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return searchService.getdowntown(pageRequestDTO);
    }

    @PostMapping(value = "/searchpage", produces = "application/json")
    public ResponseEntity<?> searchInput(@RequestBody SearchDTO searchDTO) {
        log.info("Received DetailDTO: {}", searchDTO); // 디버깅 메시지
        saveSearchDTO = searchDTO;
        return ResponseEntity.ok().body(searchDTO);
    }

    @GetMapping(value = "/list/search", produces = "application/json")
    public DetailResponseDTO<Gocamping> searchData(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return detailService.searchData(saveSearchDTO , pageRequestDTO);
    }

    @PostMapping(value = "/regionPage", produces = "application/json")
    public ResponseEntity<?> regionInput(@RequestBody RegionDTO regionDTO) {
        log.info("Received DetailDTO: {}", regionDTO); // 디버깅 메시지
        saveRegionDTO = regionDTO;
        return ResponseEntity.ok().body(regionDTO);
    }

    @GetMapping(value = "/list/region", produces = "application/json")
    public DetailResponseDTO<Gocamping> regionData(PageRequestDTO pageRequestDTO){
        log.info("list.................." + pageRequestDTO);
        return detailService.regionData(saveRegionDTO , pageRequestDTO);
    }
}
