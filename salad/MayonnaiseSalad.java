package salad;

import flavoring.Flavoring;
import vegetable.Vegetable;

/**
 *
 * @author Admin
 */
public class MayonnaiseSalad extends FlavoredSalad{
    //класс Салат с майонезом
    
    public MayonnaiseSalad(String name, Vegetable[] ingredients){
        super(name, ingredients, new Flavoring("Майонез", 150.0));
    }
}
