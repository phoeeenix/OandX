// table [WIERSZ][KOLUMNA] !!! GRA po wygraniu gra dalej. CZY JEST MOŻLIWOŚĆ WPISYWANIA TABELA[KOLUMNA][WIERSZ]?
package OandX;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] gameTable = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };
    static boolean playerWinner;
    static int columnNumber;
    static int rawNumber;
    static boolean freeFieldVar;

    public static void main(String[] args) {
        printTable();
        startGame();
    }

    static public void startGame() {
        int number;
        Player chosenPlayer;
        System.out.println("Give a name for Player 1");
        String name = scanner.next();
        Player player1 = new Player(name, 'X');
        System.out.println("Select a 2nd player \n1. Computer \n2. Human \nEnter a number.");
        number = scanner.nextInt();
        if (number == 1) {
            chosenPlayer = new Player("Computer", 'O');
            for (int i = 0; i < 5; i++) {
                if (playerWinner == true)
                    break;
                else {
                    putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
                    checkWin(player1);
                }
                if (playerWinner == true)
                    break;
                else {
                    blockPlayer(player1, chosenPlayer);
                    printTable();
                    checkWin(chosenPlayer);
                }
            }
        }
        if (number == 2) {
            System.out.println("Give a name for Player 2");
            name = scanner.next();
            chosenPlayer = new Player(name, 'O');
            for (int i = 0; i < 5; i++) {
                if (playerWinner == true)
                    break;
                else {
                    putSignQuestions(player1);     //V jak to uogólnić player[wybór nr gracza?].putQuestions() - metoda(Player player)
                    checkWin(player1);
                }
                if (playerWinner == true)
                    break;
                else {
                    putSignQuestions(chosenPlayer);
                    checkWin(chosenPlayer);
                }
            }
        }
        if (playerWinner == true) {
            System.out.println();
            System.out.println("\nTHIS IS THE END OF THE GAME. THANK YOU");
            return; // return void - zwraca pustke I WTEDY WYCHODZI WCZESNIEJ Z METODY
        } else {
            putSignQuestions(player1);
            checkLastMove(player1);
        }
        System.out.println("\nTHIS IS THE END OF THE GAME. THANK YOU");
    }

    static public void printTable() {
        System.out.println("\nTABLE DESIGN:\n---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + gameTable[i][0] + " " + gameTable[i][1] + " " + gameTable[i][2] + " |");
        }
        System.out.println("---------");
    }

    static public void enterCoordinates(Player player) {  // TRZEBA ZROBIĆ OBSŁUGĘ WYJĄTKÓW - ARRAYINDEXOUTOFBOUND
        System.out.println("\n" + player.getName() + ", Give a raw number where you want to put a sign");
        //int
        rawNumber = scanner.nextInt();
        System.out.println("\n" + player.getName() + ", Give a column number where you want to put a sign");
        //int
        columnNumber = scanner.nextInt();
    }

    static public void markField(Player player) {
        gameTable[rawNumber][columnNumber] = player.getSymbol();
    }

    static public void markComputerField(Player player, int rawNumber, int columnNumber) {
        gameTable[rawNumber][columnNumber] = player.getSymbol();
    }

    static public void computerMove(Player player) {
        generateNumber();
        while (checkFreeField() == false)
            generateNumber();
        markField(player);
    }

    static public void generateNumber() {
        Random generator = new Random();
        rawNumber = generator.nextInt(3);
        columnNumber = generator.nextInt(3);
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

    static public boolean checkFreeField() {
        if (gameTable[rawNumber][columnNumber] != '_') {
            System.out.println("This field is taken! Please fill an empty field.");
            freeFieldVar = false;
        } else
            freeFieldVar = true;
        return freeFieldVar;
    }

    static public boolean checkWin(Player player) {
        char s = player.getSymbol();
        for (int i = 0; i < 3; i++) {
            int j = 0, diagonal = 2;
            if (gameTable[i][j] == s && gameTable[i][j + 1] == s && gameTable[i][j + 2] == s) {
                System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
                break;
            } else if (gameTable[j][i] == s && gameTable[j + 1][i] == s && gameTable[j + 2][i] == s) {
                System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
                break;
            } else if (gameTable[j][j] == s && gameTable[j + 1][j + 1] == s && gameTable[j + 2][j + 2] == s ||
                    gameTable[j + diagonal][j] == s && gameTable[j + 1][j + 1] == s && gameTable[j + diagonal][j] == s) {
                System.out.println("\nCONGRATULATIONS! \nPlayer " + player.getName() + " is a WINNER");
                playerWinner = true;
                break;
            }
        }
        return playerWinner;
    }

    static public void checkLastMove(Player player) {
        checkWin(player);
        if (playerWinner == false)
            System.out.println("\nIt is a draw. Nobody wins");
    }

    static public boolean blockPlayer(Player player, Player computerPlayer) {
        boolean moveBlocked = false;
        //V CZY DA RADĘ W TEN SPOSÓB? CZY JEST FUNKCJA RANDOM int 0 || 2? - boolean i wstaw 0 lub 2 - przypisywać
        for (int i = 0; i < 3; i++) {  // sout żeby sprawdzić że warunek się WYKONAŁ! w przyszlości log4J
            if (gameTable[i][0] != '_' && gameTable[i][1] != ' ' && gameTable[i][2] == '_') {
                markComputerField(computerPlayer, i, 2); // czemu jak i to znika opis - 2 parametr? bo zmienna?
                moveBlocked = true;
                break;
            }
            // return; ?
            else if (gameTable[0][i] != '_' && gameTable[1][i] != '_' && gameTable[2][i] == ' ') {
                markComputerField(computerPlayer, 2, i);
                moveBlocked = true;
                break;
            } else if (gameTable[i][0] == '_' && gameTable[i][1] != '_' && gameTable[i][2] != '_') {
                markComputerField(computerPlayer, i, 0);
                moveBlocked = true;
                break;
            } else if (gameTable[0][i] == '_' && gameTable[1][i] != '_' && gameTable[2][i] != '_') {
                markComputerField(computerPlayer, 0, i);
                moveBlocked = true;
                break;
            } else if (gameTable[i][0] != '_' && gameTable[i][1] == '_' && gameTable[i][2] != '_') { // NIE DZIAŁA
                markComputerField(computerPlayer, i, 1);
                moveBlocked = true;
                break;
            } else if (gameTable[0][i] != '_' && gameTable[1][i] == '_' && gameTable[2][i] != '_') {
                markComputerField(computerPlayer, 1, i);
                moveBlocked = true;
                break;
            }
        }
        if (moveBlocked == false)
            if (gameTable[0][0] != '_' && gameTable[1][1] == '_' && gameTable[2][2] != '_')
                markComputerField(computerPlayer, 1, 1);
            else if (gameTable[0][2] != '_' && gameTable[1][1] == '_' && gameTable[2][0] != '_')
                markComputerField(computerPlayer, 1, 1);
            else
                computerMove(computerPlayer);
        return moveBlocked;
    }

    public static class Player {

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

//public static class ComputerPlayer {} // extends Player? - mogłoby być?

}
