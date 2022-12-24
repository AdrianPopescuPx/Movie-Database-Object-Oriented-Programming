package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;

public class Rating extends CommandAction{

    public Rating(final UserInputData currentUser, final ActionInputData currentCommand, final String actionType, final String commandType, final int numberOfCommand, final JSONArray arrayResult, final List<ActionInputData> allComands, final List<SerialInputData> allSerials, final List<MovieInputData> allMovies) {
        super(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
    }

    public final void doRating() {
        boolean checkSerial = false;
        SerialInputData currentSerial = null;
        for (int i = 0; i < getAllSerials().size(); ++i) {
            if (getCurrectCommand().getTitle().equals(getAllSerials().get(i).getTitle())) {
                checkSerial = true;
                currentSerial = getAllSerials().get(i);
                break;
            }
        }
        if (getCurrentUser().getHistory().containsKey(getCurrectCommand().getTitle())) {
            if (!checkSerial) {
                boolean checkRating = false;
                for (int i = 1; i < getNumberOfCommand() - 1; ++i) {
                    String title = getAllComands().get(i).getTitle();
                    String username = getAllComands().get(i).getUsername();
                    if (getCurrectCommand().getTitle().equals(title) && getCurrectCommand().getUsername().equals(username)) {
                        if (title.equals(getCurrectCommand().getTitle()) && getAllComands().get(i).getType().equals(getCurrectCommand().getType())) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id", getNumberOfCommand());
                            jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " has been already rated");
                            getArrayResult().add(jsonObject);
                            checkRating = true;
                            break;
                        }
                    }
                }
                if (!checkRating) {
                    for (int q = 0; q < getAllMovies().size(); ++q) {
                        if (getAllMovies().get(q).getTitle().equals(getCurrectCommand().getTitle())) {
                            getAllMovies().get(q).setRatings(getCurrectCommand().getGrade());
                        }
                    }
                    getCurrentUser().addRatingNumber();
                    String str = String.format("%.1f", getCurrectCommand().getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was rated with " + str + " by " + getCurrectCommand().getUsername());
                    getArrayResult().add(jsonObject);

                }
            } else {
                boolean checkSeason = false;
                for (int i = 1; i < getNumberOfCommand() - 1; ++i) {
                    String title = getAllComands().get(i).getTitle();
                    String username = getAllComands().get(i).getUsername();
                    if (getCurrectCommand().getTitle().equals(title) && getCurrectCommand().getUsername().equals(username)) {
                        if (title.equals(getCurrectCommand().getTitle()) && getAllComands().get(i).getType().equals(getCurrectCommand().getType()) && getCurrectCommand().getSeasonNumber() == getAllComands().get(i).getSeasonNumber()) {
                            checkSeason = true;
                        }
                    }
                }
                if (!checkSeason) {
                    getCurrentUser().addRatingNumber();
                    currentSerial.getSeasons().get(getCurrectCommand().getSeasonNumber() - 1).addRating(getCurrectCommand().getGrade());
                    String str = String.format("%.1f", getCurrectCommand().getGrade());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "success -> " + getCurrectCommand().getTitle() + " was rated with " + str + " by " + getCurrectCommand().getUsername());
                    getArrayResult().add(jsonObject);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", getNumberOfCommand());
                    jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " has been already rated");
                    getArrayResult().add(jsonObject);
                }
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getNumberOfCommand());
            jsonObject.put("message", "error -> " + getCurrectCommand().getTitle() + " is not seen");
            getArrayResult().add(jsonObject);
        }
    }
}
