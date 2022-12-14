package commands;

import fileio.ActionInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Rating extends CommandAction{

    public Rating(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSeasons) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSeasons);
    }

    public void doRating() {
        int checkSeason = 0;
        for(int i = 0; i < allSeasons.size(); ++i) {
            if(currentCommand.getTitle().equals(allSeasons.get(i).getTitle())) {
                checkSeason = allSeasons.get(i).getNumberSeason();
            }
        }
        if(currentUser.getHistory().containsKey(currentCommand.getTitle())) {
            if (checkSeason == 0) {
                boolean checkRating = false;
                for (int i = 1; i < numberOfCommand - 1; ++i) {
                    String title = allComands.get(i).getTitle();
                    String username = allComands.get(i).getUsername();
                    if (currentCommand.getTitle().equals(title) && currentCommand.getUsername().equals(username)) {
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
                if (!checkRating) {
                    String str = String.format("%.1f", currentCommand.getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", numberOfCommand);
                    jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was rated with " + str + " by " + currentCommand.getUsername());
                    arrayResult.add(jsonObject);
                }
            } else {
                for (int i = 1; i < numberOfCommand - 1; ++i) {
                    String title = allComands.get(i).getTitle();
                    String username = allComands.get(i).getUsername();
                    if (currentCommand.getTitle().equals(title) && currentCommand.getUsername().equals(username)) {
                        if (title.equals(currentCommand.getTitle()) && allComands.get(i).getType().equals(currentCommand.getType())) {
                            checkSeason--;
                        }
                    }
                }
                if (checkSeason >= 1) {
                    String str = String.format("%.1f", currentCommand.getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", numberOfCommand);
                    jsonObject.put("message", "success -> " + currentCommand.getTitle() + " was rated with " + str + " by " + currentCommand.getUsername());
                    arrayResult.add(jsonObject);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", numberOfCommand);
                    jsonObject.put("message", "error -> " + currentCommand.getTitle() + " has been already rated");
                    arrayResult.add(jsonObject);
                }
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
