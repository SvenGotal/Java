import java.util.Scanner;

public class main{

	public static void main(String[] args)
	{
		TicTacToe game = new TicTacToe();
		
		Scanner sc = new Scanner(System.in);
		
		do{
			
			game.changePlayer();
			
			// Player X
			System.out.println(game.getPlayerMark() +  " -Unesi koordinatu x");
			int x = sc.nextInt();
		    
		    System.out.println(game.getPlayerMark() + " -Unesi koordinatu y");
			int y = sc.nextInt();

			game.placeMark(x,y);
			game.printBoard();
			
			game.changePlayer();
			
			// Player O
		    System.out.println(game.getPlayerMark() + " -Unesi koordinatu x");
			x = sc.nextInt();
		    
		    System.out.println(game.getPlayerMark() + " -Unesi koordinatu y");
			y = sc.nextInt();

			game.placeMark(x,y);
			game.printBoard();
			
		}while(!game.checkForWin() && !game.isBoardFull());
		
	}

}
