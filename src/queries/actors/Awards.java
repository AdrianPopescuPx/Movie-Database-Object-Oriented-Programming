package queries.actors;

import actor.ActorsAwards;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Awards extends Actors{

    public Awards(ActionInputData currentCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<ActorInputData> allActors, int numberOfCommand, JSONArray arrayResult) {
        super(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
    }

    public void doAwards() {
        for(int i = 0; i < allActors.size(); ++i) {
            allActors.get(i).calculateAwards();
        }
        allActors.sort(new Comparator<ActorInputData>() {
            @Override
            public int compare(ActorInputData o1, ActorInputData o2) {
                if(currentCommand.getSortType().equals("asc")) {
                    if(Double.compare(o1.calculateAwards(), o2.calculateAwards()) == 0) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return Double.compare(o1.calculateAwards(), o2.calculateAwards());
                }
                else {
                    if(Double.compare(o1.calculateAwards(), o2.calculateAwards()) == 0) {
                        return -o1.getName().compareTo(o2.getName());
                    }
                    return -Double.compare(o1.calculateAwards(), o2.calculateAwards());
                }
            }
        });
        ArrayList<String> actorsResult = new ArrayList<>();
        for(int i = 0; i < allActors.size(); ++i) {
            int contains = currentCommand.getFilters().get(3).size();
            int check = 0;
            for(int c = 0; c < currentCommand.getFilters().get(3).size(); ++c) {
                ActorsAwards current = ActorsAwards.valueOf(currentCommand.getFilters().get(3).get(c));
                if(allActors.get(i).getAwards().containsKey(current)) {
                    check++;
                }
            }
            if(contains == check) {
                actorsResult.add(allActors.get(i).getName());
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", numberOfCommand);
        jsonObject.put("message", "Query result: " + actorsResult);
        arrayResult.add(jsonObject);
    }
}
