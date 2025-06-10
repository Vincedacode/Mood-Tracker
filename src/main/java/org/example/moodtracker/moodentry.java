package org.example.moodtracker;

import java.util.Scanner;

public class moodentry {
    mooddao mooddao = new mooddao();
    private final Scanner myscanner = new Scanner(System.in);

    public void insertNewMood() {
        System.out.println("Enter your username: ");
        String username = myscanner.nextLine();

        System.out.println("Enter new mood: ");
        String mood = myscanner.nextLine();

        System.out.println("Enter note: ");
        String note = myscanner.nextLine();

        System.out.println("Enter tags separated by commas (e.g., happy, excited): ");
        String[] tags = myscanner.nextLine().split(",");

        mooddao.insertMood(username, mood, note, tags);
    }

    public void showMoods() {
        mooddao.showAllMoods();
    }

    public void findMoodByUsername(){
        System.out.println("Enter your username to search: ");
        String username = myscanner.nextLine();

        mooddao.findMoodByUsername(username);
    }

    public void updateMoodById() {
        System.out.println("Enter the ID of the mood you want to update:");
        String id = myscanner.nextLine();

        System.out.println("Enter new mood (leave blank to keep current):");
        String newMood = myscanner.nextLine();

        System.out.println("Enter new note (leave blank to keep current):");
        String newNote = myscanner.nextLine();

        System.out.println("Enter new tags (comma separated, leave blank to keep current):");
        String tagInput = myscanner.nextLine();
        String[] newTags = tagInput.isEmpty() ? new String[]{} : tagInput.split(",");

        mooddao.updateMood(id, newMood, newNote, newTags);
    }


    public void deleteMood(){
        System.out.println("Enter ID of mood to delete: ");
        String id = myscanner.nextLine();

        mooddao.deleteMoodById(id);
    }
}
