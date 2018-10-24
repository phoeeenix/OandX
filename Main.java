package OandX;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String name;
        int columnNumber = 0;
        int rawNumber = 0;
        char[][] gameTable = { {' ' , ' ' , ' '} , {' ' , ' ' , ' '} , {' ' , ' ' , ' '} };


        Scanner scanner = new Scanner(System.in);

        //System.out.println(gameTable); // hashcode?
        System.out.println("Give a name for Player 1");
        name = scanner.next();
        Player player1 = new Player(name, 'O');
        System.out.println("Give a name for Player 2");
        name = scanner.next();
        Player player2 = new Player(name, 'X');
        System.out.println("OK");
        for (int i = 0; i < 5 ; i++) {
            player1.putSignQuestions();     // jak to uogólnić player[wybór nr gracza?].putQuestions()
            gameTable[columnNumber][rawNumber] = player1.symbol;
            System.out.println(gameTable.toString());  // jak wyświetlić macierz, poprzez formatter, czy można łatwiej?
            checkPlayer1();
            player2.putSignQuestions();
            gameTable[columnNumber][rawNumber] = player2.symbol;
            System.out.println(gameTable.toString());
            checkPlayer2();
        }
        player1.putSignQuestions();
        gameTable[columnNumber][rawNumber] = player1.symbol;
        System.out.println(gameTable.toString());

        public void checkPlayer1(){
            char s = player1.symbol;
            if (gameTable[0][0] == s && gameTable[1][0] = s && gameTable[2][0] == s ||
                gameTable[0][1] == s && gameTable[1][1] = s && gameTable[2][1] == s ||
                gameTable[0][2] == s && gameTable[1][2] = s && gameTable[2][2] == s ||
                gameTable[0][0] == s && gameTable[0][1] = s && gameTable[0][2] == s ||
                gameTable[1][0] == s && gameTable[1][1] = s && gameTable[1][2] == s ||
                gameTable[2][0] == s && gameTable[2][1] = s && gameTable[2][2] == s ||
                gameTable[0][0] == s && gameTable[1][1] = s && gameTable[2][2] == s ||
                gameTable[0][2] == s && gameTable[1][1] = s && gameTable[2][0] == s)
                System.out.println("Player 1 " + player1.name + "is a WINNER");
        }

        public void checkPlayer2(){
            char s = player2.symbol;
            if (gameTable[0][0] == s && gameTable[1][0] = s && gameTable[2][0] == s ||
                    gameTable[0][1] == s && gameTable[1][1] = s && gameTable[2][1] == s ||
                    gameTable[0][2] == s && gameTable[1][2] = s && gameTable[2][2] == s ||
                    gameTable[0][0] == s && gameTable[0][1] = s && gameTable[0][2] == s ||
                    gameTable[1][0] == s && gameTable[1][1] = s && gameTable[1][2] == s ||
                    gameTable[2][0] == s && gameTable[2][1] = s && gameTable[2][2] == s ||
                    gameTable[0][0] == s && gameTable[1][1] = s && gameTable[2][2] == s ||
                    gameTable[0][2] == s && gameTable[1][1] = s && gameTable[2][0] == s)
                System.out.println("Player 2 " + player2.name + "is a WINNER");
        }

        public void movePlayer1() {     // jak umieścić to w klasie player i odwołać się do tablicy gameTable (modyfikatory?_)
            player1.putSignQuestions();     // jak to uogólnić player[wybór nr gracza?].putQuestions()
            gameTable[columnNumber][rawNumber] = player1.symbol;
            System.out.println(gameTable.toString());
            checkPlayer1();
        }

        public void movePlayer2() {
            player2.putSignQuestions();
            gameTable[columnNumber][rawNumber] = player2.symbol;
            System.out.println(gameTable.toString());
            checkPlayer2();
        }

    }
      /*  public putSignInTable(){
        gameTable[]
        }*/

        /*public putSign(){
        System.out.println("Give a column number where you want to put a sign");
        columnNumber = scanner.nextInt();
        System.out.println("Give a raw number where you want to put a sign");
        rawNumber = scanner.nextInt();
        gameTable[columnNumber][rawNumber] = 's'; // how to put a symbol?
        System.out.println();
        }*/

        /*private void changeTable() {
            gameTable[columnNumber][rawNumber] = 's'; // how to put a parameter symbol?
        }

        public void showTable() {
            System.out.println(gameTable);
        }

        public void play() {
            for (int i = 0; i < 5 ; i++) {
                player1.putSignqQuestions();
                System.out.println(gameTable);
                player2.putSignqQuestions();
                showTable();
            }
            player1.putSignqQuestions();
            showTable();
        }


        public char getGameTable() {
            System.out.println(gameTable);
            return gameTable;
        }*/
    }
/*
public class Player {

    int columnNumber;
    int rawNumber;
    String name;
    char symbol;
    Scanner scanner = new Scanner(System.in);

    //char[][] gameTable = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

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

    *//*
    Player player1 = new Player("Player 1", 'O');
    Player player2 = new Player("Player 2", 'X');*//*

    public void putSignQuestions() {
        System.out.println("Give a column number where you want to put a sign");
        columnNumber = scanner.nextInt();
        System.out.println("Give a raw number where you want to put a sign");
        rawNumber = scanner.nextInt();
    }

}*/
