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

public class VideoFavorite extends Videos{
    public VideoFavorite(ActionInputData currentCommand, int numberOfCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, JSONArray arrayResult) {
        super(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
    }

    public void doVideoFavorite() {
        if(currentCommand.getObjectType().equals("movies")) {
            ArrayList<String> result = new ArrayList<>();
            for(MovieInputData currentMovie: allMovies) {
                for(UserInputData currentUser: allUsers) {
                    if(currentUser.getFavoriteMovies().contains(currentMovie.getTitle())) {
                        currentMovie.addFavoriteApparition(1);
                    }
                }
            }
            allMovies.sort(new Comparator<MovieInputData>() {
                @Override
                public int compare(MovieInputData o1, MovieInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
                    }
                    else {
                        if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
                    }
                }
            });
            for(MovieInputData currentMovie: allMovies) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentMovie.getFavoriteApparitions() > 0) {
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
            for(SerialInputData currentShow: allSerials) {
                for(UserInputData currentUser: allUsers) {
                    if(currentUser.getFavoriteMovies().contains(currentShow.getTitle())) {
                        currentShow.addFavoriteApparition(1);
                    }
                }
            }
            allSerials.sort(new Comparator<SerialInputData>() {
                @Override
                public int compare(SerialInputData o1, SerialInputData o2) {
                    if(currentCommand.getSortType().equals("asc")) {
                        if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                        return Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
                    }
                    else {
                        if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                            return -o1.getTitle().compareTo(o2.getTitle());
                        }
                        return -Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
                    }
                }
            });
            for(SerialInputData currentShow: allSerials) {
                if(result.size() == currentCommand.getNumber()) {
                    break;
                }
                if(currentShow.getFavoriteApparitions() > 0) {
                    String checkYear = currentCommand.getFilters().get(0).get(0);
                    String checkGenre = currentCommand.getFilters().get(1).get(0);
                    if(checkYear == null && checkGenre == null) {
                        result.add(currentShow.getTitle());
                    }
                    else if(checkYear == null && checkGenre != null){
                        boolean genreOk = false;
                        if(currentShow.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk) {
                            result.add(currentShow.getTitle());
                        }
                    }
                    else if(checkYear != null && checkGenre == null) {
                        boolean yearOk = false;
                        String movieYear = String.valueOf(currentShow.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        if(yearOk) {
                            result.add(currentShow.getTitle());
                        }
                    }
                    else {
                        boolean yearOk = false;
                        boolean genreOk = false;
                        String movieYear = String.valueOf(currentShow.getYear());
                        if(movieYear.equals(checkYear)) {
                            yearOk = true;
                        }
                        if(currentShow.getGenres().contains(checkGenre)) {
                            genreOk = true;
                        }
                        if(genreOk && yearOk) {
                            result.add(currentShow.getTitle());
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
