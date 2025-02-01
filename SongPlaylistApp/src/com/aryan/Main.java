package com.aryan;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();
    private static Song currentSong = null;  // Track current song being played

    public static void main(String[] args) {

        Album album = new Album("IV", "YYHS");
        album.addSong("Brown Rang", 4.5);
        album.addSong("Gabru", 3.5);
        album.addSong("Angrezi Beat", 5.0);
        albums.add(album);

        album = new Album("The Marshall Mathers LP", "Eminem");
        album.addSong("The Real Slim Shady", 4.44);
        album.addSong("Stan", 6.44);
        album.addSong("The Way I Am", 4.51);
        albums.add(album);

        album = new Album("GNX", "Kendrick Lamar");
        album.addSong("Wacced Out Murals", 4.15);
        album.addSong("Squabble Up", 3.45);
        album.addSong("Luther", 5.30);
        album.addSong("Man at the Garden", 4.25);
        album.addSong("Hey Now", 3.55);
        albums.add(album);

        LinkedList<Song> playList_1 = new LinkedList<>();
        albums.get(0).addToPlayList("Brown Rang", playList_1);
        albums.get(0).addToPlayList("Gabru", playList_1);
        albums.get(1).addToPlayList("The Real Slim Shady", playList_1);
        albums.get(1).addToPlayList("Stan", playList_1);
        albums.get(2).addToPlayList("Wacced Out Murals", playList_1);
        albums.get(2).addToPlayList("Squabble Up", playList_1);

        play(playList_1);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if (playList.size() == 0) {
            System.out.println("This playlist has no songs.");
        } else {
            currentSong = listIterator.next();  // Set the first song as the current song
            System.out.println("Now playing: " + currentSong);
            printMenu();
        }

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;

                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        currentSong = listIterator.next();
                        System.out.println("Now playing: " + currentSong);
                    } else {
                        System.out.println("No song available, reached the end of the list.");
                        forward = false;
                    }
                    break;

                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        currentSong = listIterator.previous();
                        System.out.println("Now playing: " + currentSong);
                    } else {
                        System.out.println("We are at the first song.");
                        forward = false;
                    }
                    break;

                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            currentSong = listIterator.previous();
                            System.out.println("Now playing: " + currentSong);
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list.");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            currentSong = listIterator.next();
                            System.out.println("Now playing: " + currentSong);
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list.");
                        }
                    }
                    break;

                case 4:
                    printList(playList);
                    break;

                case 5:
                    printMenu();
                    break;

                case 6:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            currentSong = listIterator.next();
                            System.out.println("Now playing: " + currentSong);
                        } else {
                            if (listIterator.hasPrevious())
                                currentSong = listIterator.previous();
                            System.out.println("Now playing: " + currentSong);
                        }
                    }
                    break;

                case 7:
                    printAvailableSongs();
                    break;

                case 8:
                    playCurrentSong();  // Play the current song again
                    break;
            }
        }
    }

    private static void playCurrentSong() {
        if (currentSong != null) {
            System.out.println("Replaying: " + currentSong);
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    private static void printMenu() {
        System.out.println("Available options:\nPress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list all songs\n" +
                "5 - print all available options\n" +
                "6 - delete current song\n" +
                "7 - show all available songs with album\n" +
                "8 - play current song again");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("-----------------");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("-----------------");
    }

    private static void printAvailableSongs() {
        System.out.println("Available Songs:");
        for (Album album : albums) {
            System.out.println("Album: " + album.getName() + " by " + album.getArtist());
            for (Song song : album.getSongs()) {
                System.out.println(" - " + song);
            }
        }
    }
}
