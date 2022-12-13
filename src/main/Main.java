package main;

import checker.Checkstyle;
import checker.Checker;
import commands.CommandAction;
import common.Constants;
import fileio.*;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        for(int i = 0; i < input.getCommands().size(); ++i) {
            String actionType = input.getCommands().get(i).getActionType();
            String commandType = input.getCommands().get(i).getType();
            if(actionType.equals("command")) {
                UserInputData currentUser = input.getUsers().get(0);
                CommandAction command = new CommandAction(currentUser, actionType, commandType);
                System.out.println(command.getCurrentUser());
            }
//            else if(actionType.equals("query")) {
//
//            }
//            if(actionType.equals("command") && commandType.equals("favorite")) {
//                for(int j = 0; j < input.getUsers().size(); ++j) {
//                    if (input.getUsers().get(j).getUsername().equals(input.getCommands().get(i).getUsername()) && input.getUsers().get(j).getUsername().equals("tautPolenta7")) {
//                        if (input.getUsers().get(j).getHistory().containsKey("Sherlock: The Final Problem")) { //&& input.getUsers().get(j).getFavoriteMovies().contains("Sherlock: The Final Problem")) {
//                            input.getUsers().get(j).getFavoriteMovies().add(input.getCommands().get(i).getTitle());
//                            System.out.println(input.getUsers().get(j).getFavoriteMovies());
//                        }
//                        else System.out.println("ERROR, SHERLOCK IS ALREADY FAVORITE");
//                    }
//                }
//            }
        }


        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        fileWriter.closeJSON(arrayResult);


    }
}
