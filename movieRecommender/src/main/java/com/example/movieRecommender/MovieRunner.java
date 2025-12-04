package com.example.movieRecommender;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MovieRunner implements CommandLineRunner {

    private final MovieRecommenderService watchmode;

    public MovieRunner(MovieRecommenderService watchmode) {
        this.watchmode = watchmode;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a genre: ");
        String genre = scanner.nextLine();

        var movies = watchmode.getTopMoviesByGenre(genre);

        System.out.println("Top ten movies:");
        movies.stream().limit(10).forEach(System.out::println);
    }
}
