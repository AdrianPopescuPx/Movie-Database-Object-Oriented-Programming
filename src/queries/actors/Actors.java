package queries.actors;

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

    public Actors(final ActionInputData currentCommand, final List<SerialInputData> allSerials, final List<MovieInputData> allMovies, final List<ActorInputData> allActors, final int numberOfCommand, final JSONArray arrayResult) {
        this.currentCommand = currentCommand;
        this.allActors = allActors;
        this.arrayResult = arrayResult;
        this.allMovies = allMovies;
        this.allSerials = allSerials;
        this.numberOfCommand = numberOfCommand;
    }

    public final void doQuery() {
        if (currentCommand.getCriteria().equals("average")) {
            Average average = new Average(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
            average.doAverage();
        } else if (currentCommand.getCriteria().equals("awards")) {
            Awards awards = new Awards(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
            awards.doAwards();
        } else if (currentCommand.getCriteria().equals("filter_description")) {
            FilterDescription filterDescription = new FilterDescription(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
            filterDescription.doFilterDescription();
        }
    }
}
