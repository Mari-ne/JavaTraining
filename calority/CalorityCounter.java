package calority;

import vegetable.Vegetable;
import salad.Salad;
import salad.FlavoredSalad;

/**
 *
 * @author Admin
 */
public class CalorityCounter {
    
    public static double countTotalCaloricity(Salad salad){
        double tmpCaloricity = 0;
        Vegetable[] ingredients = salad.getIngredients();
        for(Vegetable vegetable : ingredients){
            tmpCaloricity += vegetable.getCaloricity() * vegetable.getQuantity() / 100;
        }
        
        return tmpCaloricity;
    }
    
    public static double countTotalCaloricity(FlavoredSalad salad){
        return countTotalCaloricity((Salad)salad)+ salad.getFlavoring().getCaloricity();
    }
}
