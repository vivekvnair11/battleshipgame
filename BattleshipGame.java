import java.util.Scanner;

public class BattleshipGame
{


    public static int rows = 5;
    public static int cols = 5;
    public static int playerShips;
    public static int computerShips;
    public static String[][] boundary = new String[rows][cols];
    public static String[][]computer=new String[rows][cols];
    public static int missByplayer=0;
    public static int missByComputer=0;
    public static int hitByPlayer=0;
    public static int hitByComputer=0;
    public static int hitOwnshipComputer=0;
    public static int hitOwnshipPlayer=0;




    public static void main(String[] args)
    {
        System.out.println("BATTLESHIP GAME");

        createBoundary();
        deployPlayerShips();
        deployComputerShips();


        for (int i=1;i<=5;i++)
        {
            start();
        }

        gameOver();
    }

    public static void createBoundary()
    {

        System.out.println("This is your boundary .. ");
        System.out.println("");
        for (int i = 0; i < cols; i++)
        {

            System.out.print(" "+i);
        }
        System.out.println(" ");

        for (int i = 0; i < boundary.length; i++)
        {

            for (int j = 0; j < boundary[i].length; j++)

            {

                boundary[i][j] = " ";
                if (j == 0)

                {

                   System.out.print(i + "   " + boundary[i][j]);
                }

                else

                {

                    System.out.print(boundary[i][j]);

                }

            }
            System.out.println();
        }

        System.out.println("");

        System.out.println("\nComputer's Boundary");

        for (int i = 0; i < cols; i++)
        {

            System.out.print(" "+i);
        }
        System.out.println(" ");

        for (int i = 0; i < computer.length; i++)

        {

            for (int j = 0; j < computer[i].length; j++)

            {

                computer[i][j] = " ";
                if (j == 0)

                {

                   System.out.print(i  + computer[i][j]);

                }

                else

                {

                     System.out.print(computer[i][j]);

                }

            }
            System.out.println();
        }

    }

    public static void deployPlayerShips()

    {
        Scanner input = new Scanner(System.in);

        System.out.println("\nDeploy your ships:");
        BattleshipGame.playerShips = 5;
        for (int i = 1; i <= BattleshipGame.playerShips; )

        {
            System.out.print("Enter X coordinate for your ship "+ i+" : ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your ship "+ i+" : ");
            int y = input.nextInt();

            if((x >= 0 && x < rows) && (y >= 0 && y < cols) && (boundary[x][y] == " "))
            {
                boundary[x][y] =   "P";
                i++;
            }
            else if((x >= 0 && x < rows) && (y >= 0 && y < cols) && boundary[x][y] == "P")
                System.out.println("You can't place two ships on the same location");
            else if((x < 0 || x >= rows) || (y < 0 || y >= cols))
                System.out.println("You can't place ships outside the boundary");
        }
        printBoundary();
    }

    public static void deployComputerShips()

    {

        System.out.println("\nComputer is deploying ships");
        BattleshipGame.computerShips = 5;
        for (int i = 1; i <=BattleshipGame.computerShips; )
        {

            int x = (int)(Math.random() * 5);
            int y = (int)(Math.random() * 5);
            if((x >= 0 && x < rows) && (y >= 0 && y < cols) && (computer[x][y] == " "))
            {
                computer[x][y] =   "C";
                System.out.println(i + " Ships deployed");
                i++;
            }else if((x >= 0 && x < rows) && (y >= 0 && y < cols) && (computer[x][y] == "C"))
            {
                System.out.println("Already position occupied, please go for another co-ordinate");
                BattleshipGame.computerShips+=1;
            }
        }
        computerGrid();
    }

    public static void start()

    {
        newTurn();

        gameOver();
        printBoundary();
        computerGrid();
        System.out.println();

    }

    public static void newTurn()
    {

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 1) {
                //playerTurn

                System.out.println("\nYOUR TURN");
                int x = -1, y = -1;
                do {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Enter X coordinate: ");
                    x = input.nextInt();
                    System.out.print("Enter Y coordinate: ");
                    y = input.nextInt();

                    if ((x >= 0 && x < rows) && (y >= 0 && y < cols)) {

                        if (computer[x][y] == "C") {
                            System.out.println("you sunk opponent's ship");
                            computer[x][y] = "x";
                            --BattleshipGame.computerShips;
                            hitByPlayer += 1;

                        } else if (boundary[x][y] == "P") {
                            System.out.println("Oops ! you sunk your own ship :");
                            boundary[x][y] = "!";
                            --BattleshipGame.playerShips;
                            hitOwnshipPlayer += 1;
                        } else if (computer[x][y] == " ") {
                            System.out.println("Sorry, you missed");
                            computer[x][y] = "0";
                            missByplayer += 1;
                        }
                    } else if ((x < 0 || x >= rows) || (y < 0 || y >= cols))
                        System.out.println("You can't place ships outside the boundary");
                } while ((x < 0 || x >= rows) || (y < 0 || y >= cols));

            } else {

                // computerTurn


                System.out.println("\nCOMPUTER'S TURN");
                int x = -1, y = -1;
                do {
                    x = (int) (Math.random() * 5);
                    y = (int) (Math.random() * 5);
                    if ((x >= 0 && x < rows) && (y >= 0 && y < cols)) {
                        if (boundary[x][y] == "P") {
                            System.out.println("The Computer sunk one of your ships!");
                            boundary[x][y] = "-";
                            --BattleshipGame.playerShips;
                            hitByComputer += 1;
                        } else if (computer[x][y] == "C") {
                            System.out.println("The Computer sunk  its own ship");
                            computer[x][y] = "!";
                            --BattleshipGame.computerShips;
                            hitOwnshipComputer += 1;
                        } else if (boundary[x][y] == " ") {
                            System.out.println("Computer missed");
                            missByComputer += 1;
                        }
                    }
                } while ((x < 0 || x >= rows) || (y < 0 || y >= cols));


            }
        }
    }


    public static void gameOver()

    {
        System.out.println("");
        System.out.println("GAME OVER!!");

        System.out.println("Your ships: " +BattleshipGame.playerShips + " | Computer ships: " + BattleshipGame.computerShips);
        if(BattleshipGame.playerShips > 0 && BattleshipGame.computerShips <= 0)
        {
            System.out.println(" You won the game :)");
        }
        else if(BattleshipGame.computerShips>0&&BattleshipGame.playerShips<=0)
        {
            System.out.println("opponent wins");
        }
        else
        {
            if (BattleshipGame.playerShips>BattleshipGame.computerShips)
            {
                System.out.println("you won the game");
            }else if (BattleshipGame.playerShips==BattleshipGame.computerShips)
            {
                System.out.println("game draw");
            }
            else
            {
                System.out.println("opponent wins");
            }
        }
        System.out.println();
        System.out.println("Successful hits by Player :"+hitByPlayer);
        System.out.println("\nmissed chance by player :"+ missByplayer);
        System.out.println("\nplayer hits own ship :"+hitOwnshipPlayer);
        System.out.println("\nSuccessful hits by Computer :"+ hitByComputer);
        System.out.println("\nmissed chance by Computer :"+missByComputer);
        System.out.println("\nComputer hits own ship :"+hitOwnshipComputer);
        printBoundary();
        System.exit(0);

    }

    public static void printBoundary()

    {

        System.out.println();
        System.out.print(" ");
        for(int i = 0; i < cols; i++)
        {

            System.out.print( i);
        }
        System.out.println();


        for(int x = 0; x < boundary.length; x++)
        {
            System.out.print(x );

            for (int y = 0; y < boundary.length; y++)
            {
                System.out.print(boundary[x][y] +"");

            }
            System.out.println();
        }
    }

    public static void computerGrid()
    {
        System.out.println();
        System.out.print(" ");
        for(int i = 0; i < cols; i++)
        {

            System.out.print( i);
        }
        System.out.println();


        for(int x = 0; x < computer.length; x++)
        {
            System.out.print(x );

            for (int y = 0; y < computer.length; y++)
            {
                System.out.print(computer[x][y] +"");

            }
            System.out.println();
        }
    }}


