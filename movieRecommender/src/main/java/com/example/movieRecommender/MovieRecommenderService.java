package com.example.movieRecommender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class MovieRecommenderService {

    @Value("${watchmode.api.key}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();

    public List<String> getTopMoviesByGenre(String genre) {

        String url = "https://api.watchmode.com/v1/list-titles/?apiKey=" + apiKey
                + "&genres=" + genre
                + "&types=movie"
                + "&sort_by=popularity_desc";

        Map response = rest.getForObject(url, Map.class);
        List<Map<String, Object>> titles = (List<Map<String, Object>>) response.get("titles");

        return titles.stream()
                .map(m -> (String) m.get("title"))
                .toList();
    }
}

