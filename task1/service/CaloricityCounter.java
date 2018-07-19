package service;

import java.util.List;

import entity.Vegetable;
import entity.Salad;
//import entity.FlavoredSalad;

/**
 *
 * @author Admin
 */
public class CaloricityCounter {
    
    public static double countTotalCaloricity(Salad salad){
        double tmpCaloricity = 0;
        List<Vegetable> ingredients = salad.getIngredients();
        for(Vegetable vegetable : ingredients){
            tmpCaloricity += vegetable.getCaloricity() * vegetable.getQuantity() / 100;
        }
        
        return tmpCaloricity;
    }
    
}