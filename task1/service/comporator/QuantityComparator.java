package service.comporator;

import java.util.Comparator;

import entity.Vegetable;

/**
 *
 * @author Admin
 */
public class QuantityComparator implements Comparator<Vegetable>{
    
    @Override
    public int compare(Vegetable a, Vegetable b){
        return (int)(a.getQuantity() - b.getQuantity());
    }
}
