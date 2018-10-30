
// wypisywać tabela 3 wierszowa, sproboj ponownie pole zajęyete, sprawdzenie kto wygrał (petla w petli?)
// z menu na poczatku, gra z uzytkownikiem czy z komputerem (logika komputera poprzez Random, jezeli sa gdzie 2x to wstaw tam kółlko
// blokowanie gracza. KOD MA SIĘ KOMPILOWAĆ NONSTOP!

package OandX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] gameTable = {
            {'x', ' ', ' '},
            {' ', 'x', ' '},
            {' ', ' ', 'x'}
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
        System.out.println("Give a name for Player 1");
        name = scanner.next();
        Player player1 = new Player(name, 'O');
        System.out.println("Give a name for Player 2");
        name = scanner.next();
        Player player2 = new Player(name, 'X');
        System.out.println("OK");
        for (int i = 0; i < 5; i++) {
            putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
            checkWin(player1);             // TRZEBA ZROBIĆ INSTRUKCJĘ BREAK PRZY WYGRANEJ!
            putSignQuestions(player2);
            checkWin(player2);
        }
        putSignQuestions(player1);
        checkLastMove(player1);
        System.out.println("/nTHIS IS THE END OF THE GAME. THANK YOU");
    }

    static public void printTable(){
        for (int i = 0; i < 3; i++) {
            System.out.println(gameTable[i][0] + " " + gameTable[i][1] + " " + gameTable[i][2]);
        }
    }

    static public void enterCoordinates(Player player){  // TRZEBA ZROBIĆ OBSŁUGĘ WYJĄTKÓW - ARRAYINDEXOUTOFBOUND
        System.out.println(player.getName() + " Give a column number where you want to put a sign");
        //int
        columnNumber = scanner.nextInt();
        System.out.println(player.getName() + " Give a raw number where you want to put a sign");
        //int
        rawNumber = scanner.nextInt();
    }

    static public void markField(Player player){
        gameTable[columnNumber][rawNumber] = player.getSymbol();
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
        if (gameTable[columnNumber][rawNumber] == 'O' || gameTable[columnNumber][rawNumber] == 'X' ) {
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
            if (

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
            }
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
         /*  for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 ; j++) {
                if (gameTable[i][j] == s) {
                    System.out.println("CONGRATULATIONS! /nPlayer " + player.getName() + " is a WINNER");
                    playerWinner = true;
                }
                else if(gameTable[j][i] == s) {
                    System.out.println("CONGRATULATIONS! /nPlayer " + player.getName() + " is a WINNER");
                    playerWinner = true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][i] == s && gameTable[i ][] == s && gameTable[][] == s ||
                    gameTable[][] == s && gameTable[][] == s && gameTable[][] == s) {
                System.out.println("CONGRATULATIONS! /nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
            }
        }*/

            System.out.println();
            return playerWinner;
        }

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
                System.out.println("It is a draw. Nobody wins");
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
}
