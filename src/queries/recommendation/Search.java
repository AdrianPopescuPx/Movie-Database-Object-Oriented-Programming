package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Search extends Recommendation{

    public Search(final List<SerialInputData> allSerials, final List<MovieInputData> allMovies, final List<UserInputData> allUsers, final ActionInputData currentCommand, final int numberOfCommand, final JSONArray arrayResult) {
        super(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
    }

    public final void doSearch() {
        ArrayList<String> result = new ArrayList<>();
        allMovies.sort(new Comparator<MovieInputData>() {
            @Override
            public int compare(MovieInputData o1, MovieInputData o2) {
                    if (Double.compare(o1.getRatings(), o2.getRatings()) == 0) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                    return Double.compare(o1.getRatings(), o2.getRatings());
                }
        });
        allSerials.sort(new Comparator<SerialInputData>() {
            @Override
            public int compare(SerialInputData o1, SerialInputData o2) {
                if (Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating()) == 0) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
                return Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating());
            }
        });
        int userIndex = 0;
        for (int i = 0; i < allUsers.size(); ++i) {
            if (allUsers.get(i).getUsername().equals(currentCommand.getUsername())) {
                userIndex = i;
                break;
            }
        }
        if (allUsers.get(userIndex).getSubscriptionType().equals("BASIC")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "SearchRecommendation cannot be applied!");
            arrayResult.add(jsonObject);
        } else {
            for (MovieInputData currentMovie: allMovies) {
                if (!allUsers.get(userIndex).getHistory().containsKey(currentMovie.getTitle()) && currentMovie.getGenres().contains(currentCommand.getGenre())) {
                    result.add(currentMovie.getTitle());
                }
            }
            for (SerialInputData currentSerial: allSerials) {
                if (!allUsers.get(userIndex).getHistory().containsKey(currentSerial.getTitle()) && currentSerial.getGenres().contains(currentCommand.getGenre())) {
                    result.add(currentSerial.getTitle());
                }
            }
            if (result.size() > 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "SearchRecommendation result: " + result);
                arrayResult.add(jsonObject);
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", numberOfCommand);
                jsonObject.put("message", "SearchRecommendation cannot be applied!");
                arrayResult.add(jsonObject);
            }
        }
    }
}
