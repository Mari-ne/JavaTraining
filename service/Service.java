package service;

/**
 *
 * @author Admin
 */
public class Service {
    
    public static void windowClear(){
        for(int i = 0; i < 50; i ++){
            System.out.println("");
        }
    }
    
    public static void separator(){
        System.out.println("");
        for(int i = 0; i < 100; i ++){
            System.out.print("=");
        }
        System.out.println("");
    }
    
}
