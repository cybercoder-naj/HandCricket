import java.util.Random;
import java.util.Scanner;

public class HandCricket {
    private static final String BATTING = "BATTING";
    private static final String BOWLING = "BOWLING";

    private int[] arr = new int[2];

    private String player, computer;
    private int target=0, score=0, innings = 1;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);
        System.out.println("Welcome to Hand Cricket!\n\nThe rules are:-");
        System.out.println("1)There will be a toss to decide who is bowling/batting");
        enter.nextLine();
        System.out.println("2)The numbers will range from 1 to 10. Any other number will result in termination of game!");
        enter.nextLine();
        HandCricket obj = new HandCricket();

        try {
            obj.toss();
        } catch (Exception e) {
            System.exit(1);
        }
        while(true) {
            try {
                obj.playBall();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    private void toss() throws Exception {
        System.out.println("\nReady for toss?");
        for (int i = 5; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        switch (new Random().nextInt(2)) {
            case 0:
                System.out.println("\nYou have won the toss!");
                System.out.println("1) Batting\n2) Bowling\n");
                System.out.print("Enter your choice: ");
                switch(sc.nextInt()) {
                    case 1:
                        player = BATTING;
                        computer = BOWLING;
                        break;

                    case 2:
                        player = BOWLING;
                        computer = BATTING;
                        break;

                    default:
                        System.out.println("Wrong input! You have to restart the program.");
                        throw new Exception();
                }
                break;

            case 1:
                System.out.println("\nThe computer_ has won the toss!");
                for(int i=1;i<=3;i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                switch(new Random().nextInt(2)) {
                    case 0:
                        System.out.println("\nThe computer_ chooses to bat. You are bowling!");
                        computer = BATTING;
                        player = BOWLING;
                        break;

                    case 1:
                        System.out.println("\nThe computer_ chooses to bowl. You are batting!");
                        computer = BOWLING;
                        player = BATTING;
                        break;
                }
        }
    }

    private void playBall() throws Exception {
        System.out.print("\nEnter your play: ");
        int play = sc.nextInt();
        int comp = new Random().nextInt(10)+1;
        if(arr[0]==arr[1] && arr[0]!=0) comp = arr[0];
        if(play<0 || play>10) {
            System.out.println("Restart the game to read to rules again");
            System.exit(1);
        }
        System.out.println("\nYour play: " + play + "\nComputer's play: " + comp);
        if(play == comp) {
            if (player.equals(BATTING)) {
                System.out.println("You are out!");
                if(innings==1) {
                    player = BOWLING;
                    computer_ = BATTING;
                    target = score+1;
                    score = 0;
                    innings++;
                    System.out.println("You are bowling.\ntarget=" + target);
                }
                else this.exit();
            }
            else {
                System.out.println("Computer is out!");
                if(innings==1) {
                    player = BATTING;
                    computer_ = BOWLING;
                    target=score+1;
                    score=0;
                    innings++;
                    System.out.println("You are batting.\ntarget=" + target);
                }
                else this.exit();
            }
        }
        else {
            if(player.equals(BATTING)) {
                arr[0] = arr[1];
                arr[1] = play;
                score += play;
            }
            else score+= comp;
        }
        if(score>target && innings==2) this.exit();
    }

    private void exit() throws Exception {
        if(player.equals(BATTING)) {
            if(score<target) System.out.println("You lost!");
            else if( score>target) System.out.println("You won!");
            else System.out.println("Its a tie!");
        }
        else {
            if(score>target) System.out.println("You lost!");
            else if(score<target) System.out.println("You won!");
            else System.out.println("Its a tie!");
        }
        throw new Exception();
    }
}