package services;

import controller.MediaController;
import exception.EpisodeNotAllowedException;
import model.*;
import repository.interfaces.IEpisodeRepository;

import java.util.Scanner;

public class MediaStreamingApp {

    private final MediaController controller;
    private final IEpisodeRepository episodeRepo;
    private final Scanner sc = new Scanner(System.in);

    public MediaStreamingApp(MediaController controller,
                             IEpisodeRepository episodeRepo) {
        this.controller = controller;
        this.episodeRepo = episodeRepo;
    }

    public void open() {

        boolean running = true;
        System.out.println("=== MEDIA STREAMING APPLICATION ===");

        while (running) {
            try {
                printMenu();
                int choice = readInt("Choose option: ");

                switch (choice) {

                    case 1 -> addMovie();
                    case 2 -> addSeries();
                    case 3 -> addEpisode();
                    case 4 -> getMedia();
                    case 5 -> getAll();
                    case 6 -> deleteMedia();
                    case 7 -> viewEpisodes();
                    case 8 -> updateMedia();
                    case 0 -> {
                        running = false;
                        System.out.println("Application closed.");
                    }
                    default -> System.out.println("Invalid option");
                }

            } catch (RuntimeException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                
                1. Add movie
                2. Add series
                3. Add episode to series
                4. Get media by id
                5. Get all media
                6. Delete media
                7. View episodes of series
                8. Update media
                0. Exit
                """);
    }

    private void addMovie() {
        String title = readString("Movie title: ");
        int duration = readInt("Duration (minutes): ");

        int id = controller.createMedia(new Movie(0, title, duration));
        System.out.println("Movie created with id = " + id);
    }

    private void addSeries() {
        String title = readString("Series title: ");

        int id = controller.createMedia(new Series(0, title));
        System.out.println("Series created with id = " + id);
    }

    private void addEpisode() {
        int seriesId = readInt("Series id: ");

        MediaContent media = controller.getMediaObject(seriesId);
        if (!(media instanceof Series)) {
            throw new EpisodeNotAllowedException();
        }

        String name = readString("Episode name: ");
        int minute = readInt("Episode duration: ");

        episodeRepo.addEpisode(new Episodes(name, minute), seriesId);
        System.out.println("Episode added");
    }

    private void getMedia() {
        int id = readInt("Media id: ");
        System.out.println(controller.getMedia(id));
    }

    private void getAll() {
        System.out.println(controller.getAllMedias());
    }

    private void deleteMedia() {
        int id = readInt("Media id: ");
        controller.deleteMedia(id);
        System.out.println("Media deleted");
    }

    // ===== helpers =====

    private int readInt(String msg) {
        System.out.print(msg);
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
    private void viewEpisodes() {
        int seriesId = readInt("Series id: ");

        MediaContent media = controller.getMediaObject(seriesId);

        if (!(media instanceof Series)) {
            throw new EpisodeNotAllowedException();
        }

        var episodes = episodeRepo.getEpisodesBySeriesId(seriesId);


        if (episodes.isEmpty()) {
            System.out.println("No episodes found for this series.");
            return;
        }

        System.out.println("Episodes:");
        for (Episodes e : episodes) {
            System.out.println(e);
        }
    }
    private void updateMedia() {
        int id = readInt("Media id: ");


        MediaContent media = controller.getMediaObject(id);


        String newTitle = readString("New title: ");
        media.setTitle(newTitle);


        if (media instanceof Movie movie) {
            int newDuration = readInt("New duration (minutes): ");
            movie = new Movie(movie.getId(), newTitle, newDuration);
            controller.updateMedia(movie);
        }

        else if (media instanceof Series series) {
            controller.updateMedia(series);
        }

        System.out.println("Media updated successfully.");
    }
}
