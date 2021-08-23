import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void exitProgram(){
        System.out.println("Thanks for trying out my Game");
        System.out.println("                          -Asif");
        System.exit(0);
    }
    static int totalScore = 0;
    static int score = 10;
    static int[] guessed_char = new int[200];
    public static boolean interact(String one){
        boolean FreeAche = false;
        System.out.print("Please Guess a character: ");
        Scanner sc = new Scanner(System.in);
        char inp ;
        while(true){
            inp = sc.next().charAt(0);
            if(guessed_char[inp] == 1){
                System.out.print("This character is already guessed\nGuess a new one: ");
            }
            else{
                guessed_char[inp] = 1;
                break;
            }
        }
        boolean acheKi = false;
        for(int i = 0; i < one.length(); i++){
            if(one.charAt(i) == inp){
                acheKi = true;
                break;
            }
        }
        if(acheKi == false){
            guessed_char[inp] = 0;
            score--;
            System.out.println("This latter is not present in the word :-(");
        }
        else{
            System.out.println("Correct!!");
            score++;
        }
        try{
            Thread.sleep(1000);
        }catch (Exception error){
            System.out.println("Thread neutralization failed");
            error.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.println("                                                    Score:" + score);
        System.out.print("You are guessing: ");
        for(int i = 0; i < one.length(); i++){
            if(guessed_char[one.charAt(i)] == 1){
                System.out.print(one.charAt(i));
            }
            else{
                FreeAche = true;
                System.out.print('_');
            }
        }
        System.out.println("");
        if(FreeAche == false){
            System.out.println("Congratulations !! \nYou have guessed the movie!!");
            System.out.println("Your score is "+ score);
        }
        return FreeAche;
    }
    public static void playAGame(String one){
        score = 10;
        for(int i = 0; i < 200; i++) guessed_char[i] = 0;
        guessed_char[' '] = 1;
        boolean free = true;
        System.out.println("                                                    Score:" + score);
        System.out.print("You are guessing ");
        for(int i = 0; i < one.length(); i++){
            if(guessed_char[one.charAt(i)] == 1){
                System.out.print(one.charAt(i));
            }
            else{
                System.out.print('_');
            }
        }
        System.out.println("");
        while(free) {
            if(score <= 0){
                System.out.println("Sorry.. You Failed the game!!");
                System.out.println("Watch some movies dude -_-");
                break;
            }
            free = interact(one);
        }
        while(true) {
            System.out.println("\n\n\n\n\nPress M to go to Main Menu");
            Scanner sc = new Scanner(System.in);
            char ap = sc.next().charAt(0);
            if (ap == 'M' || ap == 'm')
                return;
        }
    }
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.println("***********Welcome to GuessTheMovie***********\n");
        ArrayList<String>movies = new ArrayList<String>();
        try{
            System.out.println("Getting MovieList from Cloud ...");
            URL url = new URL("https://s3.amazonaws.com/video.udacity-data.com/topher/2017/December/5a3732ea_movies/movies.txt");
            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNextLine()){
                String newMovie = sc.nextLine();
                movies.add(newMovie);
            }
        }catch (IOException error){
            System.out.println("Link Doesn't exist or you are not connected!!");
            error.printStackTrace();
        }
        Scanner console = new Scanner(System.in);
        int sz = movies.size()-1;
        System.out.print("\033[H\033[2J");
        while(true) {
            System.out.println("                                              Total Score:" + totalScore);
            System.out.println("Enter in integer from 1 to 2: ");
            System.out.println("1.Guess a new Movie.");
            System.out.println("2.Exit the game");
            int choice = console.nextInt();
            System.out.print("\033[H\033[2J");
            if(choice == 1){
                int idx = (int)(Math.random()*(sz+1));
                playAGame(movies.get(idx));
                totalScore += score;
                System.out.print("\033[H\033[2J");
            }
            else if(choice == 2){
                exitProgram();
            }
            else{
                System.out.println("Wrong Input!!");
            }
        }
    }
}  