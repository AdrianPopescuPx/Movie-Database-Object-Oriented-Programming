package commands;

import fileio.ActionInputData;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.List;

public class CommandAction {

    int numberOfCommand;
    String commandType;
    String actionType;

    UserInputData currentUser;
    ActionInputData currentCommand;
    List<ActionInputData> allComands;

    JSONArray arrayResult = new JSONArray();


    public CommandAction(UserInputData currentUser, ActionInputData currentCommand, String actionType, String commandType, int numberOfCommand, JSONArray arrayResult, List<ActionInputData> allComands) {
        this.commandType = commandType;
        this.currentUser = currentUser;
        this.actionType = actionType;
        this.currentCommand = currentCommand;
        this.numberOfCommand = numberOfCommand;
        this.arrayResult = arrayResult;
        this.allComands = allComands;
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
            Favorite favorite = new Favorite(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands);
            favorite.doFavorite();
        }
        else if(commandType.equals("view")) {
            View view = new View(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands);
            view.doView();
        }
        else if(commandType.equals("rating")) {
            Rating rating = new Rating(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands);
            rating.doRating();
        }
    }

}
