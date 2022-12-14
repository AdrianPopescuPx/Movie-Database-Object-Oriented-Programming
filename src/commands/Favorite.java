package commands;

import fileio.ActionInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Favorite extends CommandAction {


    public Favorite(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands);
    }

    public void doFavorite() {
        if (currentUser.getHistory().containsKey(currentCommand.getTitle())) {
            if(!currentUser.getFavoriteMovies().contains(currentCommand.getTitle())) {
                currentUser.getFavoriteMovies().add(currentCommand.getTitle());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was added as favourite");
                arrayResult.add(jsonObject);
            }
            else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "error -> " + currentCommand.getTitle() + " is already in favourite list");
                arrayResult.add(jsonObject);
            }
        }
        else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "error -> " + currentCommand.getTitle() + " is not seen");
            arrayResult.add(jsonObject);
        }
    }
}
