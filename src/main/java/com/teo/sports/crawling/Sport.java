package com.teo.sports.crawling;

import static java.lang.System.out;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Sport {

    public static void main(String[] args) {
        String url = "https://sports.news.naver.com/wfootball/schedule/index?year=2022&month=12&category=epl";

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.schedule_month_table > table > tbody > tr");
//            Element element = elements.get(0).getElementById("_monthlyScheduleList");
            out.println("-----------------------------------------------------");
//            out.println(element.text());
//            out.println(document);
            if (elements.isEmpty()) {
                out.println("비어있음");
            }

            for (Element e : elements) {
                String content = e.text();
                out.println("elements 갯수 : " + elements.size());
                out.println("elements 추출 : " + elements.get(0));
                out.println("내용 : " + content);
            }
            out.println("-----------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
