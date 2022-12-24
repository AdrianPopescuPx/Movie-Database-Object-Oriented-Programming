package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Favorite extends CommandAction {


    public Favorite(final UserInputData currentUser, final ActionInputData currentCommand, final String actionType, final String commandType, final int numberOfCommand, final JSONArray arrayResult, final List<ActionInputData> allComands, final List<SerialInputData> allSerials, final List<MovieInputData> allMovies) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public final void doFavorite() {
        if (getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            if (!getCurrentUser().getFavoriteMovies().contains(getCurrectCommand().getTitle())) {
                getCurrentUser().getFavoriteMovies().add(getCurrectCommand().getTitle());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", getNumberOfCommand());
                jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was added as favourite");
                getArrayResult().add(jsonObject);
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", getNumberOfCommand());
                jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " is already in favourite list");
                getArrayResult().add(jsonObject);
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " is not seen");
            getArrayResult().add(jsonObject);
        }
    }
}
