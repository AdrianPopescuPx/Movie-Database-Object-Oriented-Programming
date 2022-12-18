package commands;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.List;

public class CommandAction {

    private int numberOfCommand;
    private String commandType;
    private String actionType;

    private UserInputData currentUser;
    private ActionInputData currentCommand;
    List<ActionInputData> allComands;
    List<MovieInputData> allMovies;

    JSONArray arrayResult = new JSONArray();
    List<SerialInputData> allSerials;


    public CommandAction(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands, List<SerialInputData> allSerials, List<MovieInputData> allMovies) {
        this.commandType = commandType;
        this.currentUser = currentUser;
        this.actionType = actionType;
        this.currentCommand = currentCommand;
        this.numberOfCommand = numberOfCommand;
        this.arrayResult = arrayResult;
        this.allComands = allComands;
        this.allSerials = allSerials;
        this.allMovies = allMovies;
    }

    public CommandAction() {

    }

    public ActionInputData getCurrectCommand() {
        return currentCommand;
    }

    public void setCurrectCommand(ActionInputData currectCommand) {
        this.currentCommand = currectCommand;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public UserInputData getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInputData currentUser) {
        this.currentUser = currentUser;
    }

    public int getNumberOfCommand() {
        return numberOfCommand;
    }

    public void setNumberOfCommand(int numberOfCommand) {
        this.numberOfCommand = numberOfCommand;
    }
    public JSONArray getArrayResult() {
        return arrayResult;
    }

    public void setArrayResult(JSONArray arrayResult) {
        this.arrayResult = arrayResult;
    }

    public void doCommand() {
        if(commandType.equals("favorite")) {
            Favorite favorite = new Favorite(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            favorite.doFavorite();
        }
        else if(commandType.equals("view")) {
            View view = new View(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            view.doView();
        }
        else if(commandType.equals("rating")) {
            Rating rating = new Rating(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            rating.doRating();
        }
    }

}
