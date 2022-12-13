package commands;

import fileio.UserInputData;

public class Favorite extends CommandAction {

    public Favorite(UserInputData currentUser, String actionType, String commandType) {
        super(currentUser, actionType, commandType);
    }

    public void setActionType(String actionType, String commandType) {
        this.actionType = actionType;
        this.commandType = commandType;
    }
}
