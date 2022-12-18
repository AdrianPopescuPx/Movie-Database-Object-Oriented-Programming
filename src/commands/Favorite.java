package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Favorite extends CommandAction {


    public Favorite(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSerials, List<MovieInputData> allMovies) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public void doFavorite() {
        if (getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            if(!getCurrentUser().getFavoriteMovies().contains(getCurrectCommand().getTitle())) {
                getCurrentUser().getFavoriteMovies().add(getCurrectCommand().getTitle());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", getNumberOfCommand());
                jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was added as favourite");
                arrayResult.add(jsonObject);
            }
            else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", getNumberOfCommand());
                jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " is already in favourite list");
                arrayResult.add(jsonObject);
            }
        }
        else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " is not seen");
            arrayResult.add(jsonObject);
        }
    }
}
