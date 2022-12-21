package Queries.videos;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.List;

public class VideoLongest extends Videos{
    public VideoLongest(ActionInputData currentCommand, int numberOfCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, JSONArray arrayResult) {
        super(currentCommand, numberOfCommand, allSerials, allMovies, allUsers, arrayResult);
    }
}
