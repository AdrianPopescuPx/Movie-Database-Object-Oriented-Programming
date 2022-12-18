package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class View extends CommandAction{

    public View(UserInputData currentUser, ActionInputData currectCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSerials, List<MovieInputData> allMovies) {
        super(currentUser, currectCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public void doView() {
        if(getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            String title = getCurrectCommand().getTitle();
            getCurrentUser().getHistory().put(title, getCurrentUser().getHistory().get(title) + 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was viewed with total views of " + getCurrentUser().getHistory().get(getCurrectCommand().getTitle()));
            arrayResult.add(jsonObject);
        }
        else {
            getCurrentUser().getHistory().put(getCurrectCommand().getTitle(), 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was viewed with total views of " + getCurrentUser().getHistory().get(getCurrectCommand().getTitle()));
            arrayResult.add(jsonObject);
        }
    }
}
