package com.example.ssl_sample_server.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server/ssl")
public class SslController {
  
  private Logger log = LoggerFactory.getLogger(SslController.class);

  @GetMapping(value = "/test")
  public ResponseEntity<?> sample(@RequestParam String echoParam) {

    log.info("================== call_api ==================");

    //응답 데이터 구성
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put("echoParam", echoParam);

    return ResponseEntity.ok().body(responseMap);
  }
}
