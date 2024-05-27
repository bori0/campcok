package com.teambackend.controller;

import com.teambackend.dto.OpenWeatherDTO;
import com.teambackend.dto.PageRequestDTO;
import com.teambackend.dto.PageResponseDTO;
import com.teambackend.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/openWeather")
public class WeatherController {

    private final OpenWeatherService service;

    @GetMapping("/{tno}")
    public OpenWeatherDTO get(@PathVariable(name ="tno") Long tno) {

        return service.get2(tno);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OpenWeatherDTO>> listAll() {
        log.info("??????????????????" );
        List<OpenWeatherDTO> dtoList = service.listAll();
        return ResponseEntity.ok(dtoList);
    }


//    @GetMapping("/list")
//    public PageResponseDTO<OpenWeatherDTO> list(PageRequestDTO pageRequestDTO ) {
//
//        log.info("*weather* -- " + pageRequestDTO + " -- **" );
//        return service.list(pageRequestDTO);
//
//    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody OpenWeatherDTO openWeatherDTO){
        log.info("******  weather:  ******   " + openWeatherDTO);

        Long tno = service.register(openWeatherDTO);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable(name="tno") Long tno,
            @RequestBody OpenWeatherDTO openWeatherDTO) {

        openWeatherDTO.setTno(tno);

        log.info("*****  Modify: ****** " + openWeatherDTO);

        service.modify(openWeatherDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove( @PathVariable(name="tno") Long tno ){

        log.info("***** Remove: ****** " + tno);

        service.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }
}
