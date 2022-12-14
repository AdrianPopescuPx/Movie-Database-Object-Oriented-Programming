package commands;

import fileio.ActionInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Rating extends CommandAction{

    public Rating(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands);
    }

    public void doRating() {
        boolean checkRating = false;
        for (int i = 1; i < numberOfCommand - 1; ++i) {
            String title = allComands.get(i).getTitle();
            if (currentCommand.getTitle().equals(title)) {
                if (title.equals(currentCommand.getTitle()) && allComands.get(i).getType().equals(currentCommand.getType())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", numberOfCommand);
                    jsonObject.put("message", "error -> " + currentCommand.getTitle() + " has been already rated");
                    arrayResult.add(jsonObject);
                    checkRating = true;
                    break;
                }
            }
        }
        if(!checkRating) {
            String str = String.format("%.1f", currentCommand.getGrade());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was rated with " + str + " by " + currentCommand.getUsername());
            arrayResult.add(jsonObject);
        }
    }
}
