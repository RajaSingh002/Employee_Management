package utils;

public class ClearScreen {

   
    private static ClearScreen instance;

 
    private ClearScreen() {}

  
    public static ClearScreen getInstance() {
        if (instance == null) {
            instance = new ClearScreen();
        }
        return instance;
    }

 
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

