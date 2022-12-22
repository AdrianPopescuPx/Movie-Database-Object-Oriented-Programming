package queries.actors;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterDescription extends Actors{
    public FilterDescription(ActionInputData currentCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<ActorInputData> allActors, int numberOfCommand, JSONArray arrayResult) {
        super(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
    }

    public void doFilterDescription() {
        allActors.sort(new Comparator<ActorInputData>() {
            @Override
            public int compare(ActorInputData o1, ActorInputData o2) {
                if(currentCommand.getSortType().equals("asc")) {
                    return o1.getName().compareTo(o2.getName());
                }
                else {
                    return -o1.getName().compareTo(o2.getName());
                }
            }
        });
        ArrayList<String> actorsResult = new ArrayList<>();

        for(ActorInputData currentActor : allActors) {
            int checker = 0;
            int compare = currentCommand.getFilters().get(2).size();
            int correct = currentCommand.getFilters().get(2).size();
            for(int i = 0; i < currentCommand.getFilters().get(2).size(); ++i) {
                String regexPattern = currentCommand.getFilters().get(2).get(i);
                Pattern pattern = Pattern.compile("\\W(?i)" + regexPattern + "\\W");
                Matcher matcher = pattern.matcher(currentActor.getCareerDescription());
                boolean found = false;
                while (matcher.find()) {
                    found = true;
                }
                if(found) {
                    checker++;
                }
            }
            if(checker == compare) {
                actorsResult.add(currentActor.getName());
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", numberOfCommand);
        jsonObject.put("message", "Query result: " + actorsResult);
        arrayResult.add(jsonObject);
    }
}
