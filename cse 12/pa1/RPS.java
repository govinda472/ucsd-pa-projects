import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     * @param moves all possible moves in the game.
     */

     

    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }



    //public int getindex(String s){
        
        
       // int RPSlistlen=this.possibleMoves.length;
        
            
           
       //     for(int i=0; i<this.possibleMoves.length; i++){
            //    if(this.possibleMoves[i].equals(s)){
            //        return i;
             //   }
           // }
        
    
    //return -1;
//}









    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
          // replace this when you implement the method
        if ((isValidMove(playerMove))&&(isValidMove(cpuMove))){

        
        //int moveplayerpower= getindex(playerMove);
        //int cpuMovepower= getindex(cpuMove);


        int moveplayerpower=0;
        int cpuMovepower=0;

        for(int i=0; i<this.possibleMoves.length; i++){
            if(this.possibleMoves[i].equals(playerMove)){
                moveplayerpower=i;
            }
        }

        for(int i=0; i<this.possibleMoves.length; i++){
            if(this.possibleMoves[i].equals(cpuMove)){
                cpuMovepower=i;
            }
        }



    
        if(cpuMovepower==moveplayerpower){
            return TIE_OUTCOME;
            //tie
        }
        if(moveplayerpower==((this.possibleMoves.length)-1)||(cpuMovepower==((this.possibleMoves.length)-1))){
            if(moveplayerpower==0){
                return CPU_WIN_OUTCOME;
            }

            if(cpuMovepower==0){
                return PLAYER_WIN_OUTCOME;
            }
        }
// modify only ajacent cases beat each other lower cases
        if((moveplayerpower<cpuMovepower)&&((moveplayerpower-cpuMovepower)==-1)){
            return PLAYER_WIN_OUTCOME;
            //player wins
        }

        if((moveplayerpower>cpuMovepower)&&((moveplayerpower-cpuMovepower)==1)){
            //computer wins 
            return CPU_WIN_OUTCOME;
        }


        else{
            return TIE_OUTCOME;
        }
    
    }
    else{
        return INVALID_INPUT_OUTCOME;
    }


    
        

    

    }




    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        

        String playerMove;
        RPS Game= new RPS(moves);
        System.out.println(PROMPT_MOVE);
        Scanner in = new Scanner(System.in);

        playerMove = in.nextLine();
        while (!(playerMove.equals(QUIT))) {
            if(Game.isValidMove(playerMove)){
                Game.playRPS(playerMove, Game.genCPUMove());


            } else{
                System.out.println(INVALID_INPUT);
            }
            System.out.println(PROMPT_MOVE);
            playerMove = in.nextLine();
          }

          // prints out the info
          
          
          Game.displayStats();


        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work! And don't forget Javadoc.

        in.close();
    }
}
