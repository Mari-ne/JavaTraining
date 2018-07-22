package service;

import java.util.ArrayList;
import java.util.List;

import entity.*;
import service.logger.DebugLogger;

/**
 *
 * @author Admin
 */
public class CaloricityFilter {
    
    private static DebugLogger debugLog = new DebugLogger(DebugLogger.class);
    
    public static List<Vegetable> twoBoundFilter(Salad salad, double min, double max){
        debugLog.getLogger().debug("Two bounds filtration\n");
        List<Vegetable> tmp = new ArrayList<>();
        for(Vegetable vegi : salad.getIngredients()){
            if(vegi.getCaloricity() > min && vegi.getCaloricity() < max){
                tmp.add(vegi);
            }
        }
        return tmp;
    }
}
