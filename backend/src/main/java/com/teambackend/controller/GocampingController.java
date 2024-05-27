package com.teambackend.controller;

import com.teambackend.dto.GocampingDTO;
import com.teambackend.dto.OpenWeatherDTO;
import com.teambackend.dto.PageRequestDTO;
import com.teambackend.dto.PageResponseDTO;
import com.teambackend.service.GocampingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/gocamping")
public class GocampingController {
    private final GocampingService gocampingService;

    @GetMapping("/{tno}")
    public GocampingDTO get(@PathVariable(name ="tno") Long tno) {
        log.info("???????????????????????????????" );
        return gocampingService.get(tno);
    }

//    @GetMapping("/list")
//    public PageResponseDTO<GocampingDTO> list(PageRequestDTO pageRequestDTO ) {
//
//        log.info("** -- " + pageRequestDTO + " -- **" );
//
//        return gocampingService.list(pageRequestDTO);
//
//    }

    @GetMapping("/list")
    public ResponseEntity<List<GocampingDTO>> listAll() {
        log.info("??????????????????" );
        List<GocampingDTO> dtoList = gocampingService.listAll();
        return ResponseEntity.ok(dtoList);
    }



//    @GetMapping("/list")
//    public PageResponseDTO<GocampingDTO> list(PageRequestDTO pageRequestDTO ) {
//
//        log.info("** -- " + pageRequestDTO + " -- **" );
//
//        return gocampingService.list(pageRequestDTO);
//
//    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody GocampingDTO gocampingDTO){
        log.info("******  TodoDTO:  ******   " + gocampingDTO);

        Long tno = gocampingService.register(gocampingDTO);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable(name="tno") Long tno,
            @RequestBody GocampingDTO gocampingDTO) {

        gocampingDTO.setTno(tno);

        log.info("*****  Modify: ****** " + gocampingDTO);

        gocampingService.modify(gocampingDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove( @PathVariable(name="tno") Long tno ){

        log.info("***** Remove: ****** " + tno);

        gocampingService.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }
}
