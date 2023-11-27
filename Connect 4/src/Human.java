
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {

    public int nextColumn(Board board) {
        System.out.println("Enter a column to place a piece in");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        int output =0;
        try {
            output = (int)Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }

    public static void main( String[] args){
        LocalDatabase.init();
    }
}
