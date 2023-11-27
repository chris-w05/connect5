

public class Predictor extends Player{
    private short color;
    private int depthLimit;

    public static void main( String[] args){
        LocalDatabase.init();
    }

    public Predictor( int depth , short color ){
        this.depthLimit = depth;
        this.color = color;
    }

    public int nextColumn(Board board){
        float[] winchances = new float[7];
        board.printBoard();
        for( int i = 0; i < 7; i ++){
            winchances[i] = predict(new Board(board.boardstate, board.previousPiece, board.getHash()), i, 0);
        }

        int greatestIndex = 0;
        float greatestValue = winchances[0];
        for( int i = 1; i < winchances.length ; i ++ ){
            if( winchances[i] > greatestValue ){
                greatestIndex = i;
                greatestValue = winchances[i];
            }
        }

        for( int i = 0 ; i < winchances.length ; i ++){
            System.out.print( winchances[i] + " ");
        }
        System.out.println();
        
        return greatestIndex;
    }

    public float predict( Board board, int column, int depth ){
        board.addPiece( column );
        float winchance = 0;
        int wintype = board.checkWin();
        //System.out.println("Recursion @ depth  " + depth + " and column " + column);
        if( wintype != 0 ){
            //System.out.println("Win found @ depth " + depth);
            return (float)((wintype*color) / (Math.pow(7, (double)depth) ));
        }
        else if( depth > depthLimit ){
            //System.out.println("depth limit reached");
            return 0;
        }
        else{
            depth ++;
            for( int i = 0; i < 7; i ++){
                //System.out.println("Recursion @ depth  " + depth + " and column " + i);
                winchance += predict(new Board(board.boardstate, board.previousPiece, board.getHash()), i, depth);
            }
            return winchance;
            
        }
    }
}
