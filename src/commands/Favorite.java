package commands;

import fileio.ActionInputData;
import fileio.UserInputData;

public class Favorite extends CommandAction {


    public Favorite(UserInputData currentUser, ActionInputData currectCommand, String actionType, String commandType) {
        super(currentUser, currectCommand, actionType, commandType);
    }

    public void doFavorite() {
        if (currentUser.getHistory().containsKey(currectCommand.getTitle())) {
            if(!currentUser.getFavoriteMovies().contains(currectCommand.getTitle())) {
                currentUser.getFavoriteMovies().add(currectCommand.getTitle());
                System.out.println("TITLE ADDED");
            }
            else System.out.println("Title aready there!");
        }
    }
}
