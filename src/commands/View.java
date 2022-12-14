package commands;

import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class View extends CommandAction{

    public View(UserInputData currentUser, ActionInputData currectCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSeasons) {
        super(currentUser, currectCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSeasons);
    }

    public void doView() {
        if(currentUser.getHistory().containsKey(currentCommand.getTitle())) {
            String title = currentCommand.getTitle();
            currentUser.getHistory().put(title, currentUser.getHistory().get(title) + 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was viewed with total views of " + currentUser.getHistory().get(currentCommand.getTitle()));
            arrayResult.add(jsonObject);
        }
        else {
            currentUser.getHistory().put(currentCommand.getTitle(), 1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was viewed with total views of " + currentUser.getHistory().get(currentCommand.getTitle()));
            arrayResult.add(jsonObject);
        }
    }
}
