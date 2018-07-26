package task1.service;

import java.util.List;
import org.apache.log4j.Logger;

import task1.entity.Vegetable;
import task1.entity.Salad;

/**
 *
 * @author Admin
 */
public class CaloricityCounter {
    
    private static final Logger LOGGER = Logger.getLogger(CaloricityCounter.class);
    
    public static double countTotalCaloricity(Salad salad){
        LOGGER.debug("Count total caloricity of salad.");
        double tmpCaloricity = 0;
        List<Vegetable> ingredients = salad.getIngredients();
        for(Vegetable vegetable : ingredients){
            tmpCaloricity += vegetable.getCaloricity() * vegetable.getQuantity() / 100;
        }
        
        return tmpCaloricity;
    }
    
}
