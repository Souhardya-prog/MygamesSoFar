package TicTacToe;

import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
    static Scanner sc = new Scanner(System.in);
    static char board [][]=new char[5][5];
    static String score=" ";
    static char usermove = 'x';
    static char compmove = 'o';

    public static void main(String[] args) {
        TicTacToe person = new TicTacToe();
        person.board();
        person.getBoard();
        do {
            computerMove();
            checkScore();
            if (!score.equals(" "))break;
            person.getBoard();
            person.UserMove();
            checkScore();
        }while (score.equals(" "));

        person.getBoard();
        System.out.println(score);
    }
    public void board(){
        board[0][0]= ' ';
        board[0][1]= '|';
        board[0][2]= ' ';
        board[0][3]= '|';
        board[0][4]= ' ';

        board[1][0]= '-';
        board[1][1]= '+';
        board[1][2]= '-';
        board[1][3]= '+';
        board[1][4]= '-';

        board[2][0]= ' ';
        board[2][1]= '|';
        board[2][2]= ' ';
        board[2][3]= '|';
        board[2][4]= ' ';

        board[3][0]= '-';
        board[3][1]= '+';
        board[3][2]= '-';
        board[3][3]= '+';
        board[3][4]= '-';

        board[4][0]= ' ';
        board[4][1]= '|';
        board[4][2]= ' ';
        board[4][3]= '|';
        board[4][4]= ' ';
    }
    public void getBoard() {
        for (int i = 0; i< board.length;i++){
            for (int j = 0; j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    public void UserMove(){
        position(usermove, "Human");
    }
    public static void computerMove(){
        System.out.println("Computers turn:");
        position(compmove, "Computer");
    }
    public static void position(char a, String name){
        int p;
        boolean cond;
        do {
            if (name.equals("Human")) {
                System.out.println("Which position? 1 to 9");
                p = sc.nextInt();
            }
            else{
                Random random = new Random();
                p= random.nextInt(9) + 1;
            }
            cond = checkAvailability(p);
        }while((!cond || !(p >= 1 && p <= 9)));
        System.out.println(p);
        changePosition(p, a);
    }
    public static boolean checkAvailability(int pos){
        switch (pos) {
            case 1:

                if (board[0][0] == ' '){
                    return true;}
                break;
            case 2:
                if (board[0][2] == ' '){
                    return true;}
                break;
            case 3:
                if (board[0][4] == ' '){
                    return true;}
                break;
            case 4:
                if (board[2][0] == ' '){
                    return true;}
                break;
            case 5:
                if (board[2][2] == ' '){
                    return true;}
            break;
            case 6:
                if (board[2][4] == ' ') {
                return true;}
            break;
            case 7:
                if (board[4][0] == ' '){
                    return true;}
                break;
            case 8:
                if (board[4][2] == ' '){
                    return true;}
                break;
            case 9:
                if (board[4][4] == ' '){
                    return true;}
                break;
            default:
                //System.out.println("Enter between 1 and 9");
                return false;

        }
        System.out.println("No space");
        return false;
    }
    public static void changePosition(int pos, char a){
        switch (pos){
            case 1:board[0][0]=a;
                break;
            case 2:board[0][2]=a;
                break;
            case 3:board[0][4]=a;
                break;
            case 4:board[2][0]=a;
                break;
            case 5:board[2][2]=a;
                break;
            case 6:board[2][4]=a;
                break;
            case 7:board[4][0]=a;
                break;
            case 8:board[4][2]=a;
                break;
            case 9:board[4][4]=a;
                break;
            default:System.out.println("Invalid try again");
        }
    }
    public static void checkScore(){
        char check[] = new char[9];
        int a = 0;
        for (int i = 0; i< board.length;i+=2){
            for (int j = 0; j<board[i].length;j+=2){
                check[a]=board[i][j];
                a++;
            }
            System.out.println();
        }
        for(int i =0; i<3;i++) {
            //row wise
            if (check[i*3] != ' ' && check[i*3] == check[i*3 + 1] && check[i*3 + 1] == check[i*3 + 2]) {
                score = (check[i] == 'x') ? "You win" : "computer wins";
                return;
            }
        }
        for(int i=0; i<3;i++){
            //coloumn wise
            if(((check[i]!=' '&&check[i]==check[i+3])&&(check[i]==check[i+6]))) {
                score=(check[i]=='x')?"You win":"computer wins";
                return;
            }
        }
        //diagonal
        if(check[0]!=' '&&check[0]==check[4]&&check[4]==check[8]){
            score=(check[0]=='x')?"You win":"computer wins";
            return;
        }
        if(check[2]!=' '&&check[2]==check[4]&&check[4]==check[6]){
            score=(check[2]=='x')?"You win":"computer wins";
            return;
        }
        //for tie
        int tie=0;
        for (int i= 0; i< check.length;i++){
            if (check[i]!=' ' ){
                tie++;
            }
        }
        if ((!(score.equals("You win") || score.equals("computer wins")))&&tie==9){
            score="Tie";
        }
    }
}
