package Queries.videos;

import fileio.*;
import org.json.simple.JSONArray;

import java.util.List;

public class Videos {

    ActionInputData currentCommand;
    int numberOfCommand;

    List<SerialInputData> allSerials;

    List<MovieInputData> allMovies;

    List<UserInputData> allUsers;

    JSONArray arrayResult = new JSONArray();

    public Videos(ActionInputData currentCommand, int numberOfCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, JSONArray arrayResult) {
        this.currentCommand = currentCommand;
        this.numberOfCommand = numberOfCommand;
        this.allMovies = allMovies;
        this.allSerials = allSerials;
        this.arrayResult = arrayResult;
        this.allUsers = allUsers;
    }

    public void doQuery() {
        if(currentCommand.getCriteria().equals("ratings")) {
            VideoRatings videoRatings = new VideoRatings(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
            videoRatings.doVideoRatings();
        }
        else if(currentCommand.getCriteria().equals("favorite")) {
            VideoFavorite videoFavorite = new VideoFavorite(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
            videoFavorite.doVideoFavorite();
        }
        else if(currentCommand.getCriteria().equals("longest")) {
            VideoLongest videoLongest = new VideoLongest(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
            videoLongest.doVideoLongest();
        }
        else if(currentCommand.getCriteria().equals("most_viewed")) {

        }
    }
}
