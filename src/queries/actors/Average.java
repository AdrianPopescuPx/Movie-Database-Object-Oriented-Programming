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

public class Average extends Actors{

    public Average(ActionInputData currentCommand, List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<ActorInputData> allActors, int numberOfCommand, JSONArray arrayResult) {
        super(currentCommand, allSerials, allMovies, allActors, numberOfCommand, arrayResult);
    }

    public void doAverage() {
        ArrayList<String> actorsResult = new ArrayList<>();
        for(int i = 0; i < 1; ++i) {
            for(int ac = 0; ac < allActors.size(); ++ac) {
                if(actorsResult.contains(allActors.get(ac).getName())) {
                    continue;
                }
                double currentMedia = 0;
                int index = 0;
                int contor = 0;
                for(int m = 0; m < allActors.get(ac).getFilmography().size(); ++m) {
                    // trec prin filmele / serialele actorului, inainte sa adaug la currentMedia trb sa aflu daca e film sau serial
                    boolean movie = false;
                    for(int mo = 0; mo < allMovies.size(); ++mo) {
                        if(allMovies.get(mo).getTitle().equals(allActors.get(ac).getFilmography().get(m))) {
                            index = mo;
                            movie = true;
                        }
                    }
                    if(movie) {
                        if(allMovies.get(index).getRatings() > 0) {
                            currentMedia += allMovies.get(index).getRatings();
                            contor++;
                        }
                    }
                    else {
                        boolean serial = false;
                        for(int q = 0; q < allSerials.size(); ++q) {
                            if(allActors.get(ac).getFilmography().get(m).equals(allSerials.get(q).getTitle())) {
                                index = q;
                                serial = true;
                            }
                        }
                        if(serial) {
                            if(allSerials.get(index).getSeasonsRating() > 0) {
                                currentMedia += allSerials.get(index).getSeasonsRating();
                                contor++;
                            }
                        }
                    }
                }
                if (contor == 0) {
                    allActors.get(ac).setTotalRatings(0);
                }
                else {
                    allActors.get(ac).setTotalRatings(currentMedia / contor);
                }
            }
        }
        allActors.sort(new Comparator<ActorInputData>() {
            @Override
            public int compare(ActorInputData o1, ActorInputData o2) {
                if(currentCommand.getSortType().equals("asc")) {
                    if(Double.compare(o1.getTotalRatings(), o2.getTotalRatings()) == 0) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return Double.compare(o1.getTotalRatings(), o2.getTotalRatings());
                }
                else {
                    if (Double.compare(o1.getTotalRatings(), o2.getTotalRatings()) == 0) {
                        return -o1.getName().compareTo(o2.getName());
                    }
                    return -Double.compare(o1.getTotalRatings(), o2.getTotalRatings());
                }
            }
        });

        int takingActors = currentCommand.getNumber();
        for (int i = 0; i < allActors.size() && takingActors > 0; ++i) {
            if (Double.compare(allActors.get(i).getTotalRatings(), 0.0) != 0) {
                actorsResult.add(allActors.get(i).getName());
                --takingActors;
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", numberOfCommand);
        jsonObject.put("message", "Query result: " + actorsResult);
        arrayResult.add(jsonObject);
    }
}
