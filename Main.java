
// wypisywać tabela 3 wierszowa, sproboj ponownie pole zajęyete, sprawdzenie kto wygrał (petla w petli?)
// z menu na poczatku, gra z uzytkownikiem czy z komputerem (logika komputera poprzez Random?, jezeli sa gdzie 2x to wstaw tam kółlko
// blokowanie gracza. KOD MA SIĘ KOMPILOWAĆ NONSTOP!
// table [WIERSZ][KOLUMNA] !!!
package OandX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] gameTable = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    static boolean playerWinner;
    static int columnNumber;
    static int rawNumber;
    static boolean freeFieldVar;

    public static void main(String[] args) {

        String name;
/*
        int columnNumber = 0;
        int rawNumber = 0;
*/

        // System.out.println(Arrays.deepToString(gameTable)); // hashcode - id obiektu, deepToString przy tablicy 2-wym
        // Arrays.toString przy tablicy 1-wym.

        //  wypisanie w postaci macierzy: pętla - 3 wierszza do wypisania iterować po tablicy typu 1-wymiarową,
        // wypisać jako wiersz || petla 1-9, sprawdzi poprzez modulo == 0 -> wypisz nowa linie

        printTable();
        startGame();
        /*System.out.println("Give a name for Player 1");
        name = scanner.next();
        Player player1 = new Player(name, 'X');
        System.out.println("Give a name for Player 2");
        name = scanner.next();
        Player player2 = new Player(name, 'O');
        Player computer = new Player("Computer" , 'Y');
        startGame();
        System.out.println("OK");
        for (int i = 0; i < 5; i++) {
            putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
            checkWin(player1);
            if (playerWinner == true)
                break;
            putSignQuestions(player2);
            checkWin(player2);
            if (playerWinner == true)
                break;
        }
        putSignQuestions(player1);
        checkLastMove(player1);
        System.out.println("/nTHIS IS THE END OF THE GAME. THANK YOU");
        */
    }

    static public void startGame(){
        int number;
        String name;
        Player chosenPlayer;
        System.out.println("Give a name for Player 1");
        name = scanner.next();
        Player player1 = new Player(name, 'X');
        System.out.println("Select a 2nd player \n1. Computer \n2. Human \nEnter a number.");
        number = scanner.nextInt();
        if (number == 1) {
            chosenPlayer = new Player("Computer", 'O');
            for (int i = 0; i < 5; i++) {
                if (playerWinner == true)
                    break;
                putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
                checkWin(player1);
                if (playerWinner == true)
                    break;
                blockPlayer(player1, chosenPlayer);
                printTable();
                checkWin(chosenPlayer);
                //if (playerWinner == true)
                //    break;
            }
        }
        if (number == 2) {
            System.out.println("Give a name for Player 2");
            name = scanner.next();
            chosenPlayer = new Player(name, 'O');
            for (int i = 0; i < 5; i++) {
                if (playerWinner == true)
                    break;
                putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
                checkWin(player1);
                if (playerWinner == true)
                    break;
                putSignQuestions(chosenPlayer);
                checkWin(chosenPlayer);
                //if (playerWinner == true)
                //   break;
            }
        }
            putSignQuestions(player1);
            checkLastMove(player1);
        System.out.println("\nTHIS IS THE END OF THE GAME. THANK YOU");
    }

    static public void printTable(){
        System.out.println("\nTABLE DESING:\n");
        for (int i = 0; i < 3; i++) {
            System.out.println(gameTable[i][0] + " " + gameTable[i][1] + " " + gameTable[i][2]);
        }
        System.out.println();
    }

    static public void enterCoordinates(Player player){  // TRZEBA ZROBIĆ OBSŁUGĘ WYJĄTKÓW - ARRAYINDEXOUTOFBOUND
        System.out.println("\n" + player.getName() + ", Give a column number where you want to put a sign");
        //int
        columnNumber = scanner.nextInt();
        System.out.println("\n" + player.getName() + ", Give a raw number where you want to put a sign");
        //int
        rawNumber = scanner.nextInt();
    }

    static public void markField(Player player){
        gameTable[columnNumber][rawNumber] = player.getSymbol();
    }

    static public void markComputerField(Player player, int columnNumber, int rawNumber){
        gameTable[columnNumber][rawNumber] = player.getSymbol();
    }

    static public void computerMove(Player player){
        generateNumber();
        while (checkFreeField() == false)
            generateNumber();
        markField(player);
    }

    static public void generateNumber(){
        Random generator = new Random();
        columnNumber = generator.nextInt(3);
        rawNumber = generator.nextInt(3);
    }

// CZY METODA STATYCZNA (CZYLI NIE POTRZEBUJĄCA KONSTRUKTORA DO WYWOŁANIA, TAK?) MOŻE BYĆ PRIVATE, DEFAULT?
    static public void putSignQuestions(Player player) { // bo Main jest metodą statyczną!
        freeFieldVar = false;
        while (freeFieldVar == false) {
            enterCoordinates(player);
            checkFreeField();
        }
        markField(player);
        //System.out.println(Arrays.deepToString(gameTable));
        printTable();
    }

    static public boolean checkFreeField(){
        if (gameTable[columnNumber][rawNumber] != ' ') {
            System.out.println("This field is taken! Please fill an empty field.");
            freeFieldVar = false;
        }
        else
            freeFieldVar = true;
        return freeFieldVar;
    }

    static public boolean checkWin(Player player) {  // jak to sprawdzić inaczej? pętla w pętli?
        // ponieważ teraz kod nie jest rozszerzalny, co w przypadku tablicy 100x100 itp. ?
            char s = player.getSymbol();
            boolean playerWinner = false;
      /*      if (

                gameTable[0][0] == s && gameTable[1][0] == s && gameTable[2][0] == s ||
                gameTable[0][1] == s && gameTable[1][1] == s && gameTable[2][1] == s ||
                gameTable[0][2] == s && gameTable[1][2] == s && gameTable[2][2] == s ||
                gameTable[0][0] == s && gameTable[0][1] == s && gameTable[0][2] == s ||
                gameTable[1][0] == s && gameTable[1][1] == s && gameTable[1][2] == s ||
                gameTable[2][0] == s && gameTable[2][1] == s && gameTable[2][2] == s ||
                gameTable[0][0] == s && gameTable[1][1] == s && gameTable[2][2] == s ||
                gameTable[0][2] == s && gameTable[1][1] == s && gameTable[2][0] == s


                    ) {
                System.out.println("Player 1 " + player.getName() + "is a WINNER");
                playerWinner = true;
            }*/

        /*
        for (int j = 0; j < 3; j++) {
            int i = 0, j = 0;
            if (gameTable[j][i] == s && gameTable[j + 1][i] == s && gameTable[j + 2][i] == s ||
                gameTable[j][i + 1] == s && gameTable[j + 1][i + 1] == s && gameTable[j + 2][i + 1] == s ||
                gameTable[j][i + 2] == s && gameTable[j + 1][i + 2] == s && gameTable[j + 1][i + 2] == s ||) {
                System.out.println("CONGRATULATIONS! /nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
            }
        }
*/
           for (int i = 0; i < 3; i++) {
                int j = 0, diagonal = 2;
                if (gameTable[i][j] == s && gameTable[i][j + 1] == s && gameTable[i][j + 2] == s) {
                    System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                    playerWinner = true;
                    break;
                }
                else if(gameTable[j][i] == s && gameTable[j + 1][i] == s && gameTable[j + 2][i] == s) {
                    System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                    playerWinner = true;
                    break;
                }
                else if(gameTable[j][j] == s && gameTable[j + 1][j + 1] == s && gameTable[j + 2][j + 2] == s ||
                        gameTable[j + diagonal][j] == s && gameTable[j + 1][j + 1] == s && gameTable[j + diagonal][j] == s) {
                    System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                    playerWinner = true;
                    break;
                }
            }
            return playerWinner;
        }
   /*     for (int i = 0; i < 3; i++) {
            if (gameTable[i][i] == s && gameTable[i ][] == s && gameTable[][] == s ||
                    gameTable[][] == s && gameTable[][] == s && gameTable[][] == s) {
                System.out.println("CONGRATULATIONS! /nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
            }
        }
        *//*if (playerWinner == true)
            break;                *//* //CZY JEST MOŻLIWOŚĆ PRZERWAĆ PĘTLE TU??????????
            System.out.println();
            return playerWinner;
        }*/

       /* public void checkPlayer2() {
            char s = player2.symbol;
            boolean player2Winner = false;
            if (gameTable[0][0] == s && gameTable[1][0] = s && gameTable[2][0] == s ||
                    gameTable[0][1] == s && gameTable[1][1] = s && gameTable[2][1] == s ||
                    gameTable[0][2] == s && gameTable[1][2] = s && gameTable[2][2] == s ||
                    gameTable[0][0] == s && gameTable[0][1] = s && gameTable[0][2] == s ||
                    gameTable[1][0] == s && gameTable[1][1] = s && gameTable[1][2] == s ||
                    gameTable[2][0] == s && gameTable[2][1] = s && gameTable[2][2] == s ||
                    gameTable[0][0] == s && gameTable[1][1] = s && gameTable[2][2] == s ||
                    gameTable[0][2] == s && gameTable[1][1] = s && gameTable[2][0] == s) {
                System.out.println("Player 2 " + player2.name + "is a WINNER");
                return player2Winner = true;
            }
        }
*/
       static public void checkLastMove(Player player){
            checkWin(player);
            if ( playerWinner == false )
                System.out.println("\nIt is a draw. Nobody wins");
        }

    public void movePlayer1() {     // jak umieścić to w klasie player i odwołać się do tablicy gameTable (modyfikatory?_)
        //player1.putSignQuestions();     // jak to uogólnić player[wybór nr gracza?].putQuestions()
        //gameTable[columnNumber][rawNumber] = player1.symbol;
        //System.out.println(gameTable.toString());
        //checkPlayer1();
    }

    public void movePlayer2() {
        //player2.putSignQuestions();
        //gameTable[columnNumber][rawNumber] = player2.symbol;
        //System.out.println(gameTable.toString());
        //checkPlayer2();
    }

        /*
        public void play() {
            for (int i = 0; i < 5 ; i++) {
                player1.putSignqQuestions();
                System.out.println(gameTable);
                player2.putSignqQuestions();
                showTable();
            }
            player1.putSignqQuestions();
            showTable();
        }*/

    static public boolean blockPlayer(Player player, Player computerPlayer){
        boolean moveBlocked = false;
        //int a = 0;
        //int b = 2;
        //int c = (a + b)/2;
        // CZY DA RADĘ W TEN SPOSÓB? CZY JEST FUNKCJA RANDOM int 0 || 2?
        // if (gameTable[a][a] != ' ' || gameTable[a][b] != ' ' || gameTable[b][a] != ' ' || gameTable[b][b] != ' ')
        //    markComputerField(player,);
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] != ' ' && gameTable[i][1] != ' ' && gameTable[i][2] == ' ') {
                markComputerField(computerPlayer, i, 2); // czemu jak i to 2 parametr znika opis? bo zmienna?
                moveBlocked = true;
                break;
            }
            // return; ?
            else if (gameTable[0][i] != ' ' && gameTable[1][i] != ' ' && gameTable[2][i] == ' ') {
                markComputerField(computerPlayer, 2, i);
                moveBlocked = true;
                break;
            }
            else if (gameTable[i][0] == ' ' && gameTable[i][1] != ' ' && gameTable[i][2] != ' ') {
                markComputerField(computerPlayer, i, 0);
                moveBlocked = true;
                break;
            }
            else if (gameTable[0][i] == ' ' && gameTable[1][i] != ' ' && gameTable[2][i] != ' ') {
                markComputerField(computerPlayer, 0, i);
                moveBlocked = true;
                break;
            }
        }
        if (moveBlocked == false)
            if (gameTable[0][0] != ' ' && gameTable[1][1] == ' ' && gameTable[2][2] != ' ')
                markComputerField(computerPlayer, 1, 1);
            else if (gameTable[0][2] != ' ' && gameTable[1][1] == ' ' && gameTable[2][0] != ' ')
                markComputerField(computerPlayer, 1, 1);
            else
                computerMove(computerPlayer);
        return moveBlocked;
    }

    public static class Player { // static - klasa niezależna, non-static potrzebuje parenta, żeby móc ją stworzyć

        String name;
        char symbol;

        Player(String name, char symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public char getSymbol() {
            return symbol;
        }
    }

/*    public static class ComputerPlayer { // extends Player?

        public ComputerPlayer(){
            String name = "Computer";
            char symbol = 'V';
        }
    }*/

}
