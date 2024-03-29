package com.teo.sports;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SportsApplicationTests {
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:\\Users\\showm\\Downloads\\chromedriver-win64\\chromedriver.exe";

    @Test
    void contextLoads() {
        //드라이버 설정
        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
        options.addArguments("--headless");
        options.addArguments("--user-agent=Chrome/116.0.5845.180");

        //위에서 설정한 옵션은 담은 드라이버 객체 생성
        //옵션을 설정하지 않았을 때에는 생략 가능하다.
        //WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
        WebDriver driver = new ChromeDriver(options);

        //이동을 원하는 url
//        String url = "https://sports.news.naver.com/wfootball/schedule/index";
        String url = "https://naver.com";

        //WebDriver을 해당 url로 이동한다.
        driver.get(url);

        //브라우저 이동시 생기는 로드시간을 기다린다.
        //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        //class="division" 인 모든 태그를 가진 WebElement리스트를 받아온다.
        //WebElement는 html의 태그를 가지는 클래스이다.
        WebElement scriptElement = driver.findElement(By.cssSelector("script[type='text/javascript']"));
        String scriptContent = scriptElement.getAttribute("innerHTML");
        // JSON 파싱
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(scriptContent);

        // scheduleData 값을 추출
        Map<String, Object> scheduleData = (Map<String, Object>) jsonMap.get("scheduleData");
        System.out.println(scheduleData);


//        for (int i = 0; i < el1.size(); i++) {
//            //division 클래스의 객체 중 "뉴스"라는 텍스트를 가진 WebElement를 클릭한다.
//            if(el1.get(i).getText().equals("뉴스")) {
//                el1.get(i).click();
//                break;
//            }
//        }
//
//        //1초 대기
//        try {Thread.sleep(1000);} catch (InterruptedException e) {}
//
//        //버튼을 클릭했기 때문에 브라우저는 뉴스창으로 이동돼 있다.
//        //이동한 뉴스 창의 IT/과학 뉴스 헤드라인을 가져온다.
//
//        //iT/과학뉴스를 담은 div
//        WebElement el2 = driver.findElement(By.id("section_it"));
//
//        //div속에서 strong태그를 가진 모든 element를 받아온다.
//        List<WebElement> el3 = el2.findElements(By.tagName("strong"));
//
//        int count = 0;


        try {
            //드라이버가 null이 아니라면
            if(driver != null) {
                //드라이버 연결 종료
                driver.close(); //드라이버 연결 해제

                //프로세스 종료
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
