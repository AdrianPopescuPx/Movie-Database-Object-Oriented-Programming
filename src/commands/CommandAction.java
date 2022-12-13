package commands;

import fileio.ActionInputData;
import fileio.UserInputData;

public class CommandAction {

    String commandType;
    String actionType;

    UserInputData currentUser;
    ActionInputData currectCommand;

    public CommandAction(UserInputData currentUser, ActionInputData currectCommand, String actionType, String commandType) {
        this.commandType = commandType;
        this.currentUser = currentUser;
        this.actionType = actionType;
        this.currectCommand = currectCommand;
    }

    public CommandAction() {

    }

    public ActionInputData getCurrectCommand() {
        return currectCommand;
    }

    public void setCurrectCommand(ActionInputData currectCommand) {
        this.currectCommand = currectCommand;
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

    public void doCommand() {
        if(commandType.equals("favorite")) {
            Favorite favorite = new Favorite(currentUser, currectCommand, actionType, commandType);
            favorite.doFavorite();
        }
        else if(commandType.equals("view")) {
            View view = new View(currentUser, currectCommand, actionType, commandType);
            view.doView();
        }
        else if(commandType.equals("rating")) {

        }
    }

}
