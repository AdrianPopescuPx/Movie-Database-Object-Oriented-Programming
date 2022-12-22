package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Standard extends Recommendation{

    public Standard(List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, ActionInputData currentCommand, int numberOfCommand, JSONArray arrayResult) {
        super(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
    }

    public void doStandard() {
        String result = null;
        int userIndex = 0;
        for(int i = 0; i < allUsers.size(); ++i) {
            if(allUsers.get(i).getUsername().equals(currentCommand.getUsername())) {
                userIndex = i;
                break;
            }
        }
        boolean found = false;
        for(MovieInputData currentMovie: allMovies) {
            if(allUsers.get(userIndex).getHistory().containsKey(currentMovie.getTitle())) {
                continue;
            }
            else {
                found = true;
                result = currentMovie.getTitle();
                break;
            }
        }
        if(!found) {
            for(SerialInputData currentSerial: allSerials) {
                if(allUsers.get(userIndex).getHistory().containsKey(currentSerial.getTitle())) {
                    continue;
                }
                else {
                    found = true;
                    result = currentSerial.getTitle();
                    break;
                }
            }
        }
        if(found) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "StandardRecommendation result: " + result);
            arrayResult.add(jsonObject);
        }
        else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "StandardRecommendation cannot be applied!");
            arrayResult.add(jsonObject);
        }
    }
}
