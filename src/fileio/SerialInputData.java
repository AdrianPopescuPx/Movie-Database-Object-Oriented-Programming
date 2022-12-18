package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a tv show, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class SerialInputData extends ShowInput {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;

    private final Map<Integer, Integer> seasonsRating = new HashMap<>();
    public SerialInputData(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public void setSeasonsRating(int key, int value) {
        seasonsRating.put(key, value);
    }

    public double getSeasonsRating() {
        double sum = 0;
        double contor = 0;
        for(int i = 0; i < seasonsRating.size(); ++i) {
            sum += seasonsRating.get(i);
            contor++;
        }
        return sum / contor;
    }
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
