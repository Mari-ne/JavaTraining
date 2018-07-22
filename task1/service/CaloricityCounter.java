package service;

import java.util.List;

import entity.Vegetable;
import entity.Salad;
import service.logger.DebugLogger;

/**
 *
 * @author Admin
 */
public class CaloricityCounter {
    
    private static DebugLogger debugLog = new DebugLogger(DebugLogger.class);
    
    public static double countTotalCaloricity(Salad salad){
        debugLog.getLogger().debug("Count total caloricity of salad\n");
        double tmpCaloricity = 0;
        List<Vegetable> ingredients = salad.getIngredients();
        for(Vegetable vegetable : ingredients){
            tmpCaloricity += vegetable.getCaloricity() * vegetable.getQuantity() / 100;
        }
        
        return tmpCaloricity;
    }
    
}
