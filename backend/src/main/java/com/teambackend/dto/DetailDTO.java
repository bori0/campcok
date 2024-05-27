package com.teambackend.dto;

import lombok.Data;

@Data
public class DetailDTO {
        private D200 d200;
        private D300 d300;
        private D400 d400;
        private D500 d500;
        private D600 d600;
        private D700 d700;
        private RegionDTO regionDTO;

        @Data
        public static class D200 {
            private boolean d_d201;
            private boolean d_d202;
            private boolean d_d203;
            private boolean d_d204;
            private boolean d_d205;
            private boolean d_d206;
            private boolean d_d207;
            private boolean d_d208;
        }

        @Data
        public static class D300 {
            private boolean d_d301;
            private boolean d_d302;
            private boolean d_d303;
            private boolean d_d304;
        }

        @Data
        public static class D400 {
            private boolean d_d401;
            private boolean d_d402;
            private boolean d_d403;
            private boolean d_d404;
            private boolean d_d405;
            private boolean d_d406;
            private boolean d_d407;
        }

        @Data
        public static class D500 {
            private boolean d_d501;
            private boolean d_d502;
            private boolean d_d503;
            private boolean d_d504;
            private boolean d_d505;
            private boolean d_d506;
            private boolean d_d507;
            private boolean d_d508;
            private boolean d_d509;
            private boolean d_d5010;
            private boolean d_d5011;
        }

        @Data
        public static class D600 {
            private boolean d_d601;
            private boolean d_d602;
            private boolean d_d603;
            private boolean d_d604;
            private boolean d_d605;
            private boolean d_d606;
            private boolean d_d607;
            private boolean d_d608;
            private boolean d_d609;
            private boolean d_d6010;
            private boolean d_d6011;
            private boolean d_d6012;
        }

        @Data
        public static class D700 {
            private boolean d_d701;
            private boolean d_d702;
            private boolean d_d703;
        }




}
