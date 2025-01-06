import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    static String userMove;
    static String computerMove;

    Random random = new Random();

    static int []score = new int[2];

    RockPaperScissors(){
        score [0] = 0;
        score [1] = 0;
    }
    public static void main(String[] args) {
        RockPaperScissors ob = new RockPaperScissors();
        for (int i = 0; i <3 ; i++) {
            ob.takeMoves();
            System.out.println("You: "+userMove);
            System.out.println("Computer: "+computerMove);
            ob.checkForWin();
            ob.showScore();
        }
        ob.declareScore();
    }
    void takeMoves() {
        //taking computer move
        int n = random.nextInt(3);
        if (n == 0) computerMove = "Rock";
        else if (n == 1) computerMove = "Paper";
        else computerMove = "Scissors";

        //taking user move
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rock paper scissors??\nPlease start your choice with a capital letter eg- \"Scissors\"");
        userMove = scanner.nextLine();
    }
    void checkForWin(){
        if (userMove.equals(computerMove)) {
            System.out.println("Tie");
            return ;
        } else {
            if (userMove.equals("Rock")) {
                if (computerMove.equals("Scissors")) {
                    score[0]++;
                } else {
                    score[1]++;
                }
            }else if (userMove.equals("Paper")) {
                if (computerMove.equals("Scissors")) {
                    score[1]++;
                } else {
                    score[0]++;
                }
            }else {
                if (computerMove.equals("Rock")) {
                    score[1]++;
                } else {
                    score[0]++;
                }
            }
        }
    }
    void showScore(){
        System.out.println("User: "+score[0]+" Computer: "+score[1]);
    }
    void declareScore(){
        if (score[0]>score[1]) {
            System.out.println("You Win!");
        } else if (score[0]<score[1]) {
            System.out.println("You lost");
        } else {
            System.out.println("Its a tie, play again!");
        }
    }
}
