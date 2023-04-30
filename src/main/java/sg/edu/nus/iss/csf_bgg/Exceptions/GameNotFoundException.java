package sg.edu.nus.iss.csf_bgg.Exceptions;

public class GameNotFoundException extends Exception{
    
    public GameNotFoundException(String name){
        super("Game " + name + " not found");
    }
}
