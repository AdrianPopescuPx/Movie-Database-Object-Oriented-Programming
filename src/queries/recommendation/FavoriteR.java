package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.Comparator;
import java.util.List;

public class FavoriteR extends Recommendation{
    public FavoriteR(List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, ActionInputData currentCommand, int numberOfCommand, JSONArray arrayResult) {
        super(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
    }

    public void doFavoriteR() {

        List<MovieInputData> copyMovies = allMovies;
        List<SerialInputData> copySerial = allSerials;
        for(MovieInputData currentMovie: copyMovies) {
            for(UserInputData currentUser: allUsers) {
                if(currentUser.getFavoriteMovies().contains(currentMovie.getTitle())) {
                    currentMovie.addFavoriteApparition(1);
                }
            }
        }
        copyMovies.sort(new Comparator<MovieInputData>() {
            @Override
            public int compare(MovieInputData o1, MovieInputData o2) {
                if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                    return -o1.getTitle().compareTo(o2.getTitle());
                }
                return -Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
            }
        });
        for(SerialInputData currentSerial: copySerial) {
            for(UserInputData currentUser: allUsers) {
                if(currentUser.getFavoriteMovies().contains(currentSerial.getTitle())) {
                    currentSerial.addFavoriteApparition(1);
                }
            }
        }
        copySerial.sort(new Comparator<SerialInputData>() {
            @Override
            public int compare(SerialInputData o1, SerialInputData o2) {
                if(Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions()) == 0) {
                    return -o1.getTitle().compareTo(o2.getTitle());
                }
                return -Double.compare(o1.getFavoriteApparitions(), o2.getFavoriteApparitions());
            }
        });
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
            boolean found = false;
            String result = null;
            for(MovieInputData currentMovie: copyMovies) {
                if(!allUsers.get(userIndex).getHistory().containsKey(currentMovie.getTitle())) {
                    result = currentMovie.getTitle();
                    found = true;
                    break;
                }
            }
            if(!found) {
                for(SerialInputData currentSerial: copySerial) {
                    if(!allUsers.get(userIndex).getHistory().containsKey(currentSerial.getTitle())) {
                        result = currentSerial.getTitle();
                        found = true;
                        break;
                    }
                }
            }
            if(found) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "FavoriteRecommendation result: " + result);
                arrayResult.add(jsonObject);
            }
            else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "FavoriteRecommendation cannot be applied!");
                arrayResult.add(jsonObject);
            }
        }
    }
}
