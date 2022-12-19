package Queries.actors;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Average extends Actors{

    public Average(ActionInputData currentCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<ActorInputData> allActors, int numberOfCommand, JSONArray arrayResult) {
        super(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
    }

    public void doAverage() {
        ArrayList<String> actorsResult = new ArrayList<>();
        double maxMedia = 0;
        int maxIndex = 0;
        for(int i = 0; i < currentCommand.getNumber(); ++i) {
            double media = 0;
            for(int j = 0; j < allActors.size(); ++j) {
                int index = 0;
                for(int m = 0; m < allActors.get(j).getFilmography().size(); ++m) {
                    boolean movie = false;
                    for(int q = 0; q < allMovies.size(); ++q) {
                        if(allMovies.get(q).getTitle().equals(allActors.get(i).getFilmography().get(m))) {
                            movie = true;
                            index = q;
                            break;
                        }
                    }
                    if(movie && allMovies.get(index).getRatings() > 0) {
                        media += allMovies.get(index).getRatings();
                    }
                    else {
                        for(int q = 0; q < allSerials.size(); ++q) {
                            if(allSerials.get(q).getTitle().equals(allActors.get(i).getFilmography().get(m))) {
                                index = q;
                                break;
                            }
                        }
                        if(allSerials.get(index).getSeasonsRating() > 0) {
                            media += allSerials.get(index).getSeasonsRating();
                        }
                    }
                }
                if(!actorsResult.contains(allActors.get(j)) && media > maxMedia) {
                    maxMedia = media;
                    maxIndex = j;
                }
            }
            if (maxMedia > 0) {
                actorsResult.add(allActors.get(maxIndex).getName());
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", numberOfCommand);
        jsonObject.put("message", "Query result: " + actorsResult);
        arrayResult.add(jsonObject);
    }
}
