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
    private List<ActionInputData> allComands;
    private List<MovieInputData> allMovies;

    private JSONArray arrayResult = new JSONArray();

    public final ActionInputData getCurrentCommand() {
        return currentCommand;
    }

    public final List<ActionInputData> getAllComands() {
        return allComands;
    }

    public final List<MovieInputData> getAllMovies() {
        return allMovies;
    }

    public final List<SerialInputData> getAllSerials() {
        return allSerials;
    }

    private List<SerialInputData> allSerials;


    public CommandAction(final UserInputData currentUser, final ActionInputData currentCommand, final String actionType, final String commandType, final int numberOfCommand, final JSONArray arrayResult, final List<ActionInputData> allComands, final List<SerialInputData> allSerials, final List<MovieInputData> allMovies) {
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

    public final ActionInputData getCurrectCommand() {
        return currentCommand;
    }


    public final String getCommandType() {
        return commandType;
    }

    public final String getActionType() {
        return actionType;
    }

    public final UserInputData getCurrentUser() {
        return currentUser;
    }


    public final int getNumberOfCommand() {
        return numberOfCommand;
    }

    public final JSONArray getArrayResult() {
        return arrayResult;
    }

    public final void doCommand() {
        if (commandType.equals("favorite")) {
            Favorite favorite = new Favorite(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            favorite.doFavorite();
        } else if (commandType.equals("view")) {
            View view = new View(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            view.doView();
        } else if (commandType.equals("rating")) {
            Rating rating = new Rating(currentUser, currentCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSerials, allMovies);
            rating.doRating();
        }
    }

}
