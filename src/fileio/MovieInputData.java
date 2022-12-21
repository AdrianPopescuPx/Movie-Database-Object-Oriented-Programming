package fileio;

import java.util.ArrayList;

/**
 * Information about a movie, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MovieInputData extends ShowInput {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    int views = 0;

    int favoriteApparitions = 0;

    private final ArrayList<Double> ratings = new ArrayList<Double>();
    public MovieInputData(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void addViews(int number) {
        views += number;
    }

    public int getViews() {
        return views;
    }

    public void setRatings(double number) {
        ratings.add(number);
    }

    public double getRatings(){
        double sum = 0;
        double contor = 0;
        if(ratings.isEmpty()) {
            return 0;
        }
        for(int i = 0; i < ratings.size(); ++i) {
            sum += ratings.get(i);
            contor ++;
        }
        return sum / contor;
    }

    public void addFavoriteApparition(int number) {
        favoriteApparitions += number;
    }

    public int getFavoriteApparitions() {
        return favoriteApparitions;
    }
    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
