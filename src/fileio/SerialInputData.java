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

    int favoriteApparitions = 0;

    int views = 0;

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

    public void addFavoriteApparition(int number) {
        favoriteApparitions += number;
    }
    public int getFavoriteApparitions() {
        return favoriteApparitions;
    }

    public int getSerialDuration() {
       int suma = 0;
        if(seasons.isEmpty()) {
            return 0;
        }
        for(int i = 0; i < seasons.size(); ++i) {
            suma += seasons.get(i).getDuration();
        }
        return suma;
    }
    public double getSeasonsRating() {
        int contor = 0;
        double suma = 0;
        if(seasons.isEmpty()) {
            return 0;
        }
        for(int i = 0; i < seasons.size(); ++i) {
                suma += seasons.get(i).calculateSeasonRating();
                contor++;
        }
            return suma / contor;
    }

    public void addViews(int number) {
        views += number;
    }

    public int getViews() {
        return views;
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
