package com.teambackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



//공공데이터 날씨
@EnableScheduling
@SpringBootApplication
public class TeambackendApplication {
    private static final Logger log = LoggerFactory.getLogger(TeambackendApplication.class);


        public static void main(String[] args) {
            SpringApplication.run(TeambackendApplication.class, args);
        }
    }





