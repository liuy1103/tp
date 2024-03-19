package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String foodFilePath;
    private String activityFilePath;
    public Storage(String filePath, String foodFilePath, String activityFilePath) {
        this.filePath = filePath;
        this.foodFilePath = foodFilePath;
        this.activityFilePath = activityFilePath;
    }

    public ArrayList<Favourites> loadFavourites() throws FileNotFoundException {
        ArrayList<Favourites> loadedFavourites = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Favourites favourites = Parser.parseFavourites(line);
                if (favourites != null) {
                    loadedFavourites.add(favourites);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list ~");
            new File(file.getParent()).mkdirs();
        }

        return loadedFavourites;
    }

    public void saveFavourites(ArrayList<Favourites> favourites) throws IOException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Favourites favourite: favourites) {
                writer.write(favourite.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public ArrayList<Food> loadFood() throws FileNotFoundException {
        ArrayList<Food> loadedFood = new ArrayList<>();
        File file = new File(foodFilePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Food food = Parser.parseFood(line);
                loadedFood.add(food);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list ~");
            new File(file.getParent()).mkdirs();
        }
        return loadedFood;
    }

    public ArrayList<Activity> loadActivity() throws FileNotFoundException {
        ArrayList<Activity> loadedActivity = new ArrayList<>();
        File file = new File(activityFilePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Activity activity = Parser.parseActivity(line);
                loadedActivity.add(activity);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list ~");
            new File(file.getParent()).mkdirs();
        }
        return loadedActivity;
    }
}