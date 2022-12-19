package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Rating extends CommandAction{

    public Rating(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSerials, List<MovieInputData> allMovies) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public void doRating() {
        boolean checkSerial = false;
        SerialInputData currentSerial = null;
        for(int i = 0; i < allSerials.size(); ++i) {
            if(getCurrectCommand().getTitle().equals(allSerials.get(i).getTitle())) {
                checkSerial = true;
                 currentSerial = allSerials.get(i);
                break;
            }
        }
        if(getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            if (!checkSerial) {
                boolean checkRating = false;
                for (int i = 1; i < getNumberOfCommand() - 1; ++i) {
                    String title = allComands.get(i).getTitle();
                    String username = allComands.get(i).getUsername();
                    if (getCurrectCommand().getTitle().equals(title) && getCurrectCommand().getUsername().equals(username)) {
                        if (title.equals(getCurrectCommand().getTitle()) && allComands.get(i).getType().equals(getCurrectCommand().getType())) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id", getNumberOfCommand());
                            jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " has been already rated");
                            arrayResult.add(jsonObject);
                            checkRating = true;
                            break;
                        }
                    }
                }
                if (!checkRating)
                {
                    for(int q = 0; q < allMovies.size(); ++q) {
                        if(allMovies.get(q).getTitle().equals(getCurrectCommand().getTitle())) {
                            allMovies.get(q).setRatings((int) getCurrectCommand().getGrade());
                        }
                    }
                    String str = String.format("%.1f", getCurrectCommand().getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was rated with " + str + " by " + getCurrectCommand().getUsername());
                    arrayResult.add(jsonObject);

                }
            } else {
                boolean checkSeason = false;
                for (int i = 1; i < getNumberOfCommand() - 1; ++i) {
                    String title = allComands.get(i).getTitle();
                    String username = allComands.get(i).getUsername();
                    if (getCurrectCommand().getTitle().equals(title) && getCurrectCommand().getUsername().equals(username)) {
                        if (title.equals(getCurrectCommand().getTitle()) && allComands.get(i).getType().equals(getCurrectCommand().getType()) && getCurrectCommand().getSeasonNumber() == allComands.get(i).getSeasonNumber()) {
                            checkSeason = true;
                        }
                    }
                }
                if (!checkSeason) {
                    currentSerial.addSeasonsRating((int) getCurrectCommand().getGrade());
                    String str = String.format("%.1f", getCurrectCommand().getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was rated with " + str + " by " + getCurrectCommand().getUsername());
                    arrayResult.add(jsonObject);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " has been already rated");
                    arrayResult.add(jsonObject);
                }
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
