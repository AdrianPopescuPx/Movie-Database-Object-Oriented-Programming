package queries.users;

import fileio.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NumRatings {

    List<UserInputData> allUsers;

    ActionInputData currentCommand;

    int numberOfCommand;

    JSONArray arrayResult = new JSONArray();

    public NumRatings(ActionInputData currentCommand, List<UserInputData> allUsers, int numberOfCommand, JSONArray arrayResult) {
        this.currentCommand = currentCommand;
        this.arrayResult = arrayResult;
        this.numberOfCommand = numberOfCommand;
        this.allUsers = allUsers;
    }

    public void doNumRating() {
        ArrayList<String> result = new ArrayList<>();
        allUsers.sort(new Comparator<UserInputData>() {
            @Override
            public int compare(UserInputData o1, UserInputData o2) {
                if(currentCommand.getSortType().equals("asc")) {
                    if(Double.compare(o1.getRatingNumber(), o2.getRatingNumber()) == 0) {
                        return o1.getUsername().compareTo(o2.getUsername());
                    }
                    return Double.compare(o1.getRatingNumber(), o2.getRatingNumber());
                }
                else {
                    if(Double.compare(o1.getRatingNumber(), o2.getRatingNumber()) == 0) {
                        return -o1.getUsername().compareTo(o2.getUsername());
                    }
                    return -Double.compare(o1.getRatingNumber(), o2.getRatingNumber());
                }
            }
        });
        for(UserInputData currentUser: allUsers) {
            if(result.size() == currentCommand.getNumber()) {
                break;
            }
            if(currentUser.getRatingNumber() > 0) {
                result.add(currentUser.getUsername());
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", numberOfCommand);
        jsonObject.put("message", "Query result: " + result);
        arrayResult.add(jsonObject);
    }
}
