package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class View extends CommandAction {

    public View(final UserInputData currentUser, final ActionInputData currectCommand, final String actionType, final String commandType, final int numberOfCommand, final JSONArray arrayResult, final List<ActionInputData> allComands, final List<SerialInputData> allSerials, final List<MovieInputData> allMovies) {
        super(currentUser, currectCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public final void doView() {
        if (getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            String title = getCurrectCommand().getTitle();
            getCurrentUser().getHistory().put(title, getCurrentUser().getHistory().get(title) + 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was viewed with total views of " + getCurrentUser().getHistory().get(getCurrectCommand().getTitle()));
            getArrayResult().add(jsonObject);
        } else {
            getCurrentUser().getHistory().put(getCurrectCommand().getTitle(), 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was viewed with total views of " + getCurrentUser().getHistory().get(getCurrectCommand().getTitle()));
            getArrayResult().add(jsonObject);
        }
    }
}
