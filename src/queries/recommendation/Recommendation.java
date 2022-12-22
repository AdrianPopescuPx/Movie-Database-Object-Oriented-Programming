package queries.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.List;

public class Recommendation {

    List<SerialInputData> allSerials;
    List<MovieInputData> allMovies;
    List<UserInputData> allUsers;
    ActionInputData currentCommand;
    int numberOfCommand;
    JSONArray arrayResult;

    public Recommendation(List<SerialInputData> allSerials, List<MovieInputData> allMovies, List<UserInputData> allUsers, ActionInputData currentCommand, int numberOfCommand, JSONArray arrayResult) {
        this.allMovies = allMovies;
        this.allSerials = allSerials;
        this.allUsers = allUsers;
        this.currentCommand = currentCommand;
        this.numberOfCommand = numberOfCommand;
        this.arrayResult = arrayResult;
    }

    public void doRecommandation() {
        if(currentCommand.getType().equals("standard")) {
            Standard standard = new Standard(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
            standard.doStandard();
        }
        else if(currentCommand.getType().equals("best_unseen")) {
            BestUnseen bestUnseen = new BestUnseen(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
            bestUnseen.doBestUnseen();
        }
        else if(currentCommand.getType().equals("popular")) {
            Popular popular = new Popular(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
            popular.doPopular();
        }
        else if(currentCommand.getType().equals("favorite")) {
            FavoriteR favoriteR = new FavoriteR(allSerials, allMovies, allUsers, currentCommand, numberOfCommand, arrayResult);
            favoriteR.doFavoriteR();
        }
    }
}
