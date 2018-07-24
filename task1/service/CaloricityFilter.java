package task1.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import task1.entity.*;

/**
 *
 * @author Admin
 */
public class CaloricityFilter {
    
    final private static Logger LOGGER = Logger.getLogger(CaloricityFilter.class);
    
    public static List<Vegetable> filtration(Salad salad, double lowerBound, double upperBound){
        LOGGER.debug("Two bounds filtration\n");
        List<Vegetable> tmp = new ArrayList<>();
        for(Vegetable vegi : salad.getIngredients()){
            if(vegi.getCaloricity() > lowerBound && vegi.getCaloricity() < upperBound){
                tmp.add(vegi);
            }
        }
        return tmp;
    }
}
