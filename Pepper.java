package vegetable;

/**
 *
 * @author Admin
 */
public class Pepper extends Nightshade{
    
    public Pepper(int quantity){
        super("Перец", "Красный", 27.0, quantity);
    }
    
    public Pepper(String color, int quantity){
        super("Перец", color, 27.0, quantity);
    }
    
    @Override
    public String getName(){
        return super.getName() + " " + getColor();
    }
}
