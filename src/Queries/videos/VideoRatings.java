package Queries.videos;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VideoRatings extends Videos{
    public VideoRatings(ActionInputData currentCommand, int numberOfCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, JSONArray arrayResult) {
        super(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
    }

    public void doVideoRatings() {
        if(currentCommand.getObjectType().equals("movies")) {
            ArrayList<String> result = new ArrayList<>();
            allMovies.sort(new Comparator<MovieInputData>() {
                @Override
                public int compare(MovieInputData o1, MovieInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getRatings(), o2.getRatings()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getRatings(), o2.getRatings());
                    }
                    else {
                        if(Double.compare(o1.getRatings(), o2.getRatings()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getRatings(), o2.getRatings());
                    }
                }
            });
            for(MovieInputData currentMovie: allMovies) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentMovie.getRatings() > 0) {
                    String checkYear = currentCommand.getFilters().get(0).get(0);
                    if(checkYear == null) {
                        boolean genreOk = false;
                        String checkGenre = currentCommand.getFilters().get(1).get(0);
                        if(currentMovie.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk) {
                            result.add(currentMovie.getTitle());
                        }
                    }
                    else {
                        boolean yearOk = false;
                        boolean genreOk = false;
                        String movieYear = String.valueOf(currentMovie.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        String checkGenre = currentCommand.getFilters().get(1).get(0);
                        if(currentMovie.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk && yearOk) {
                            result.add(currentMovie.getTitle());
                        }
                    }
                }
            }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "Query result: " + result);
                arrayResult.add(jsonObject);
        }
        else if(currentCommand.getObjectType().equals("shows")) {
            ArrayList<String> result = new ArrayList<>();
            allSerials.sort(new Comparator<SerialInputData>() {
                @Override
                public int compare(SerialInputData o1, SerialInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating());
                    }
                    else {
                        if(Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating());
                    }
                }
            });
            for(SerialInputData currentSerial : allSerials) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentSerial.getSeasonsRating() > 0) {
                    String checkYear = currentCommand.getFilters().get(0).get(0);
                    if(checkYear == null) {
                        boolean genreOk = false;
                        String checkGenre = currentCommand.getFilters().get(1).get(0);
                        if(currentSerial.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk) {
                            result.add(currentSerial.getTitle());
                        }
                    }
                    else {
                        boolean yearOk = false;
                        boolean genreOk = false;
                        String showYear = String.valueOf(currentSerial.getYear());
                        if(showYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        String checkGenre = currentCommand.getFilters().get(1).get(0);
                        if(currentSerial.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk && yearOk) {
                            result.add(currentSerial.getTitle());
                        }
                    }
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "Query result: " + result);
            arrayResult.add(jsonObject);
        }
    }
}
