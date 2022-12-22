package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.*;

public class Popular extends Recommendation{

    public Popular(List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, ActionInputData currentCommand, int numberOfCommand, JSONArray arrayResult) {
        super(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
    }

    public void doPopular() {

        for(MovieInputData currentMovie: allMovies) {
            for(UserInputData currentUser: allUsers) {
                if(currentUser.getHistory().containsKey(currentMovie.getTitle())) {
                    currentMovie.addViews(currentUser.getHistory().get(currentMovie.getTitle()));
                }
            }
        }
        for(SerialInputData currentSerial: allSerials) {
            for(UserInputData currentUser: allUsers) {
                if(currentUser.getHistory().containsKey(currentSerial.getTitle())) {
                    currentSerial.addViews(currentUser.getHistory().get(currentSerial.getTitle()));
                }
            }
        }
        Map<String, Integer> map =  new HashMap<>();
        ArrayList<String> allGenres = new ArrayList<>();
        String[] stringsToAdd = {"Action", "Adventure", "Drama", "Comedy", "Crime", "Romance", "War", "History", "Thriller", "Mystery", "Family", "Horror", "Fantasy", "Science Fiction", "Sci-fi & Fantasy", "Animation", "Kids", "Western", "Tv Movie"};
        allGenres.addAll(Arrays.asList(stringsToAdd));
        map.put("Action", 0);
        map.put("Adventure", 0);
        map.put("Drama", 0);
        map.put("Comedy", 0);
        map.put("Crime", 0);
        map.put("Romance", 0);
        map.put("War", 0);
        map.put("History", 0);
        map.put("Thriller", 0);
        map.put("Mystery", 0);
        map.put("Family", 0);
        map.put("Horror", 0);
        map.put("Fantasy", 0);
        map.put("Science Fiction", 0);
        map.put("Action & Adventure", 0);
        map.put("Sci-fi & Fantasy", 0);
        map.put("Animation", 0);
        map.put("Kids", 0);
        map.put("Western", 0);
        map.put("Tv Movie", 0);
        for(int i = 0; i < allGenres.size(); ++i) {
            for(MovieInputData currentMovie: allMovies) {
                if(currentMovie.getGenres().contains(allGenres.get(i))) {
                    map.put(allGenres.get(i), map.get(allGenres.get(i)) + currentMovie.getViews());
                }
            }
            for(SerialInputData currentSerial: allSerials) {
                if(currentSerial.getGenres().contains(allGenres.get(i))) {
                    map.put(allGenres.get(i), map.get(allGenres.get(i)) + currentSerial.getViews());
                }
            }
        }
        List<Map.Entry<String, Integer>> nlist = new ArrayList<>(map.entrySet());
        nlist.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int userIndex = 0;
        for(int i = 0; i < allUsers.size(); ++i) {
            if(allUsers.get(i).getUsername().equals(currentCommand.getUsername())) {
                userIndex = i;
                break;
            }
        }
        if(allUsers.get(userIndex).getSubscriptionType().equals("BASIC")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "PopularRecommendation cannot be applied!");
            arrayResult.add(jsonObject);
        }
        else {
            String result = null;
            boolean found = false;
            for(int i = 0; i < nlist.size() && found == false; ++i) {
                for(MovieInputData currentMovie: allMovies) {
                    if(currentMovie.getGenres().contains(nlist.get(i).getKey()) && !allUsers.get(userIndex).getHistory().containsKey(currentMovie.getTitle())) {
                        result = currentMovie.getTitle();
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    for(SerialInputData currentSerial: allSerials) {
                        if(currentSerial.getGenres().contains(nlist.get(i).getKey()) && !allUsers.get(userIndex).getHistory().containsKey(currentSerial.getTitle())) {
                            result = currentSerial.getTitle();
                            found = true;
                            break;
                        }
                    }
                }
            }
            if(found) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "PopularRecommendation result: " + result);
                arrayResult.add(jsonObject);
            }
        }
    }

}
