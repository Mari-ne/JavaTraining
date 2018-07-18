package salad;

import flavoring.Flavoring;
import vegetable.Vegetable;

/**
 *
 * @author Admin
 */
public class VinegarSalad extends FlavoredSalad{
    //класс Салат с уксусом
    
    public VinegarSalad(String name, Vegetable[] ingredients){
        super(name, ingredients, new Flavoring("Уксус", 4.0));
    }
}
