package zerobase.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Service
public class DiaryService {
    @Value("${openweathermap.key}")
    private String apiKey;

    public void createDiary(LocalDate date, String text) {
        // openweathermap에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();
    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            BufferedReader br;

            // HTTP Connection 열기
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET 요청
            connection.setRequestMethod("GET");

            // 응답코드 받기
            int responseCode = connection.getResponseCode();

            // 응답코드에 따른 객체 받아서 BufferedReader에 담기
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // 생성된 응답 객체 String화 진행
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            return response.toString();
        } catch (Exception e) {
            return "failed to response";
        }
    }
}
