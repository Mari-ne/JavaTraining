package service.comporator;

import java.util.Comparator;

import entity.Vegetable;

/**
 *
 * @author Admin
 */
public class NameComparator implements Comparator<Vegetable>{
    
    @Override
    public int compare(Vegetable a, Vegetable b){
        return a.getName().compareTo(b.getName());
    }
}
