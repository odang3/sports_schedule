package com.teo.sports.crawling;

import static java.lang.System.out;


public class Sport {

    public static void main(String[] args) {
        String url = "https://sports.news.naver.com/wfootball/schedule/index?year=2022&month=12&category=epl";

        try {
//            Element element = elements.get(0).getElementById("_monthlyScheduleList");
            out.println("-----------------------------------------------------");
//            out.println(element.text());
//            out.println(document);
            out.println("-----------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
