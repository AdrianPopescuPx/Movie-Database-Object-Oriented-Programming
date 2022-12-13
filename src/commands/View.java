package commands;

import fileio.ActionInputData;
import fileio.UserInputData;

public class View extends CommandAction{

    public View(UserInputData currentUser, ActionInputData currectCommand, String actionType, String commandType) {
        super(currentUser, currectCommand, actionType, commandType);
    }

    public void doView() {
        if(currentUser.getHistory().containsKey(currectCommand.getTitle())) {
            String title = currectCommand.getTitle();
            currentUser.getHistory().put(title, currentUser.getHistory().get(title) + 1);
        }
        else {
            currentUser.getHistory().put(currectCommand.getTitle(), 1);
        }
    }
}
