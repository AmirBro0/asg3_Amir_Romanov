package utils;

import model.MediaContent;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static List<MediaContent> sortByDuration(List<MediaContent> list) {
        return list.stream()
                .sorted((a, b) ->
                        Integer.compare(a.getDuration(), b.getDuration()))
                .toList();
    }


    public static List<MediaContent> sortByTitle(List<MediaContent> list) {
        return list.stream()
                .sorted(Comparator.comparing(MediaContent::getTitle))
                .toList();
    }

    public static List<MediaContent> sortByTypeThenDuration(List<MediaContent> list) {
        return list.stream()
                .sorted(
                        Comparator
                                .comparing(MediaContent::getType)
                                .thenComparing(MediaContent::getDuration)
                )
                .toList();
    }
}
