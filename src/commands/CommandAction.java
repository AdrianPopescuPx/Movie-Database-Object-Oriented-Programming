package commands;

import fileio.UserInputData;

public class CommandAction {

    String commandType;
    String actionType;

    UserInputData currentUser;

    public CommandAction(UserInputData currentUser, String actionType, String commandType) {
        this.commandType = commandType;
        this.currentUser = currentUser;
        this.actionType = actionType;
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
        if(actionType.equals("favorite")) {

        }
        else if(actionType.equals("view")) {

        }
        else if(actionType.equals("rating")) {

        }
    }

}
