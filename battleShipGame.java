
import java.util.Scanner;

public class battleShipGame {


    public static int rows = 10;
    public static int cols = 10;
    public static int playerShips;
    public static int computerShips;
    public static String[][] boundary = new String[rows][cols];
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
            Start();
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


        }

    public static void deployPlayerShips(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nDeploy your ships:");
        battleShipGame.playerShips = 5;
        for (int i = 1; i <= battleShipGame.playerShips; ) {
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

    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");
        battleShipGame.computerShips = 5;
        for (int i = 1; i <=battleShipGame.computerShips; )
        {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < rows) && (y >= 0 && y < cols) && (boundary[x][y] == " "))
            {
                boundary[x][y] =   "C";
                System.out.println(i + " Ships deployed");
                i++;
            }
        }
        printBoundary();
    }

    public static void Start()
    {
        for (int i=1;i<=10;i++)
        {
            if(i%2==1)
            {
                playerTurn();
            }else
            {
                computerTurn();
            }

        }

        printBoundary();
        gameOver();
        System.out.println();
    }

    public static void playerTurn()
    {

            System.out.println("\nYOUR TURN");
            int x = -1, y = -1;
            do {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter X coordinate: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate: ");
                y = input.nextInt();

                if ((x >= 0 && x < rows) && (y >= 0 && y < cols))
                {

                    if (boundary[x][y] == "C")
                    {
                        System.out.println("you sunk opponent's ship");
                        boundary[x][y] = "x";
                        --battleShipGame.computerShips;
                        hitByPlayer+=1;

                    } else if (boundary[x][y] == "P") {
                        System.out.println("Oops ! you sunk your own ship :");
                        boundary[x][y] = "!";
                        --battleShipGame.playerShips;
                        hitOwnshipPlayer+=1;
                    } else if (boundary[x][y] == " ") {
                        System.out.println("Sorry, you missed");
                        boundary[x][y] = "0";
                        missByplayer+=1;
                    }
                } else if ((x < 0 || x >= rows) || (y < 0 || y >= cols))
                    System.out.println("You can't place ships outside the boundary");
            } while ((x < 0 || x >= rows) || (y < 0 || y >= cols));

    }

    public static void computerTurn()
    {

         System.out.println("\nCOMPUTER'S TURN");
         int x = -1, y = -1;
            do {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);
                if ((x >= 0 && x < rows) && (y >= 0 && y < cols))
                {
                    if (boundary[x][y] == "P")
                    {
                        System.out.println("The Computer sunk one of your ships!");
                        boundary[x][y] = "C";
                        --battleShipGame.playerShips;
                        hitByComputer+=1;
                    } else if (boundary[x][y] == "C") {
                        System.out.println("The Computer sunk  its own ship");
                        boundary[x][y] = "-";
                        hitOwnshipComputer+=1;
                    } else if (boundary[x][y] == " ") {
                        System.out.println("Computer missed");
                       missByComputer+=1;
                    }
                }
            } while ((x < 0 || x >= rows) || (y < 0 || y >= cols));



    }

    public static void gameOver(){

        System.out.println("Your ships: " +battleShipGame.playerShips + " | Computer ships: " + battleShipGame.computerShips);
        if(battleShipGame.playerShips > 0 && battleShipGame.computerShips <= 0)
        {
            System.out.println(" You won the game :)");
        }
        else if(battleShipGame.computerShips>0&&battleShipGame.playerShips<=0)
        {
            System.out.println("opponent wins");
        }
        else
        {
            if (battleShipGame.playerShips>battleShipGame.computerShips)
            {
                System.out.println("you won the game");
            }else if (battleShipGame.playerShips==battleShipGame.computerShips)
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

    public static void printBoundary(){

        System.out.println();
        System.out.print("  ");
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
}



