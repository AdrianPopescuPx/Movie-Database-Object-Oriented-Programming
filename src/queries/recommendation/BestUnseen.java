package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.Comparator;
import java.util.List;

public class BestUnseen extends Recommendation{

    public BestUnseen(final List<SerialInputData> allSerials, final List<MovieInputData> allMovies, final List<UserInputData> allUsers, final ActionInputData currentCommand, final int numberOfCommand, final JSONArray arrayResult) {
        super(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
    }

    public final void doBestUnseen() {
        String result = null;
        allMovies.sort(new Comparator<MovieInputData>() {
            @Override
            public int compare(MovieInputData o1, MovieInputData o2) {
                    return -Double.compare(o1.getRatings(), o2.getRatings());
            }
        });

        allSerials.sort(new Comparator<SerialInputData>() {
            @Override
            public int compare(SerialInputData o1, SerialInputData o2) {
                    return -Double.compare(o1.getSeasonsRating(), o2.getSeasonsRating());
                }
        });

        int userIndex = 0;
        for (int i = 0; i < allUsers.size(); ++i) {
            if(allUsers.get(i).getUsername().equals(currentCommand.getUsername())) {
                userIndex = i;
                break;
            }
        }
        boolean found = false;
        for (MovieInputData currentMovie: allMovies) {
            if(allUsers.get(userIndex).getHistory().containsKey(currentMovie.getTitle())) {
                continue;
            } else {
                found = true;
                result = currentMovie.getTitle();
                break;
            }
        }
        if (!found) {
            for (SerialInputData currentSerial: allSerials) {
                if(allUsers.get(userIndex).getHistory().containsKey(currentSerial.getTitle())) {
                    continue;
                } else {
                    found = true;
                    result = currentSerial.getTitle();
                    break;
                }
            }
        }
        if (found) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "BestRatedUnseenRecommendation result: " + result);
            arrayResult.add(jsonObject);
        }
        else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", numberOfCommand);
            jsonObject.put("message", "BestRatedUnseenRecommendation cannot be applied!");
            arrayResult.add(jsonObject);
        }
    }

}
