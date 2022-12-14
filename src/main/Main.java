package main;

import checker.Checkstyle;
import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.CommandAction;
import common.Constants;
import entertainment.Season;
import fileio.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        for(int i = 0; i < input.getCommands().size(); ++i) {
            int numberOfCommand = i + 1;
            String actionType = input.getCommands().get(i).getActionType();
            String commandType = input.getCommands().get(i).getType();
            if(actionType.equals("command")) {
                ActionInputData currectCommand = input.getCommands().get(i);
                UserInputData currentUser = null;
                for (int j = 0; j < input.getUsers().size(); ++j) {
                    if (input.getUsers().get(j).getUsername().equals(currectCommand.getUsername())) {
                        currentUser = input.getUsers().get(j);
                        break;
                    }
                }
                List<ActionInputData> allComands = input.getCommands();
                List<SerialInputData> allSeasons = input.getSerials();
                CommandAction command = new CommandAction(currentUser, currectCommand, actionType, commandType, numberOfCommand, arrayResult, allComands, allSeasons);
                command.doCommand();
            }
        }
        fileWriter.closeJSON(arrayResult);
    }
}
