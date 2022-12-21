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

public class VideoMostViewed extends Videos{
    public VideoMostViewed(ActionInputData currentCommand, int numberOfCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, JSONArray arrayResult) {
        super(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
    }

    public void doVideoMostViewed() {
        if(currentCommand.getObjectType().equals("movies")) {
            ArrayList<String> result = new ArrayList<>();
            for(MovieInputData currentMovie: allMovies) {
                for(UserInputData currentUser: allUsers) {
                    if(currentUser.getHistory().containsKey(currentMovie.getTitle())) {
                        currentMovie.addViews(currentUser.getHistory().get(currentMovie.getTitle()));
                    }
                }
            }
            allMovies.sort(new Comparator<MovieInputData>() {
                @Override
                public int compare(MovieInputData o1, MovieInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getViews(), o2.getViews()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getViews(), o2.getViews());
                    }
                    else {
                        if(Double.compare(o1.getViews(), o2.getViews()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getViews(), o2.getViews());
                    }
                }
            });
            for(MovieInputData currentMovie: allMovies) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentMovie.getViews() > 0) {
                    String checkYear = currentCommand.getFilters().get(0).get(0);
                    String checkGenre = currentCommand.getFilters().get(1).get(0);
                    if(checkYear == null && checkGenre == null) {
                        result.add(currentMovie.getTitle());
                    }
                    else if(checkYear == null && checkGenre != null){
                        boolean genreOk = false;
                        if(currentMovie.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk) {
                            result.add(currentMovie.getTitle());
                        }
                    }
                    else if(checkYear != null && checkGenre == null) {
                        boolean yearOk = false;
                        String movieYear = String.valueOf(currentMovie.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        if(yearOk) {
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
            for(SerialInputData currentSerial: allSerials) {
                for(UserInputData currentUser: allUsers) {
                    if(currentUser.getHistory().containsKey(currentSerial.getTitle())) {
                        currentSerial.addViews(currentUser.getHistory().get(currentSerial.getTitle()));
                    }
                }
            }
            allSerials.sort(new Comparator<SerialInputData>() {
                @Override
                public int compare(SerialInputData o1, SerialInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getViews(), o2.getViews()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getViews(), o2.getViews());
                    }
                    else {
                        if(Double.compare(o1.getViews(), o2.getViews()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getViews(), o2.getViews());
                    }
                }
            });
            for(SerialInputData currentSerial: allSerials) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentSerial.getViews() > 0) {
                    String checkYear = currentCommand.getFilters().get(0).get(0);
                    String checkGenre = currentCommand.getFilters().get(1).get(0);
                    if(checkYear == null && checkGenre == null) {
                        result.add(currentSerial.getTitle());
                    }
                    else if(checkYear == null && checkGenre != null){
                        boolean genreOk = false;
                        if(currentSerial.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk) {
                            result.add(currentSerial.getTitle());
                        }
                    }
                    else if(checkYear != null && checkGenre == null) {
                        boolean yearOk = false;
                        String movieYear = String.valueOf(currentSerial.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        if(yearOk) {
                            result.add(currentSerial.getTitle());
                        }
                    }
                    else {
                        boolean yearOk = false;
                        boolean genreOk = false;
                        String movieYear = String.valueOf(currentSerial.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
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
