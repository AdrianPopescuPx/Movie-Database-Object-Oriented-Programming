package Queries.actors;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import org.json.simple.JSONArray;

import java.util.List;

public class Actors {
    ActionInputData currentCommand;
    List<SerialInputData> allSerials;
    List<MovieInputData> allMovies;
    List<ActorInputData> allActors;
    int numberOfCommand;
    JSONArray arrayResult = new JSONArray();

    public Actors(ActionInputData currentCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<ActorInputData> allActors, int numberOfCommand, JSONArray arrayResult) {
        this.currentCommand = currentCommand;
        this.allActors = allActors;
        this.arrayResult = arrayResult;
        this.allMovies = allMovies;
        this.allSerials = allSerials;
        this.numberOfCommand = numberOfCommand;
    }

    public void doQuery() {
        if(currentCommand.getCriteria().equals("average")) {
            Average average = new Average(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
            average.doAverage();
        }
        else if(currentCommand.getCriteria().equals("awards")) {

        }
        else if(currentCommand.getCriteria().equals("filter_description")) {

        }
    }
}
