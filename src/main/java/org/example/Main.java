package org.example;

import org.example.moodtracker.moodentry;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        moodentry moodentry = new moodentry();
        Scanner myscanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            try {
                System.out.println("\nWelcome to Vincedacode Moodtracker");
                System.out.println("Pick an option:");
                System.out.println("1. Insert new mood");
                System.out.println("2. Get all moods");
                System.out.println("3. Find mood");
                System.out.println("4. Update mood");
                System.out.println("5. Delete mood");
                System.out.println("6. Exit");
                System.out.print("Your choice: ");

                int userchoice = myscanner.nextInt();
                myscanner.nextLine();

                switch (userchoice) {
                    case 1:
                        moodentry.insertNewMood();
                        break;
                    case 2:
                        moodentry.showMoods();
                        break;
                    case 3:
                        moodentry.findMoodByUsername();
                        break;
                    case 4:
                        moodentry.updateMoodById();
                        break;
                    case 5:
                        moodentry.deleteMood();
                        break;
                    case 6:
                        loop = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Please choose a valid option (1-6).");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Please enter a number.");
                myscanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        myscanner.close(); //
    }
}
