package service;

import java.util.ArrayList;
import java.util.List;

import entity.*;

/**
 *
 * @author Admin
 */
public class Filter {
    
    public static List<Vegetable> twoBoundFilter(Salad salad, double min, double max){
        List<Vegetable> tmp = new ArrayList<>();
        for(Vegetable vegi : salad.getIngredients()){
            if(vegi.getCaloricity() > min && vegi.getCaloricity() < max){
                tmp.add(vegi);
            }
        }
        return tmp;
    }
    
}
