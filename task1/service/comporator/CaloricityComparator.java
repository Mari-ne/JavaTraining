package service.comporator;

import java.util.Comparator;

import entity.Vegetable;

/**
 *
 * @author Admin
 */
public class CaloricityComparator implements Comparator<Vegetable>{
    
    @Override
    public int compare(Vegetable a, Vegetable b){
        return (int)(a.getCaloricity() - b.getCaloricity());
    }
}
