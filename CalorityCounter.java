/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calority;

import vegetable.Vegetable;

/**
 *
 * @author Admin
 */
public class CalorityCounter {
    
    public static double countTotalCaloricity(Vegetable[] ingredients, double flavoringCalority){
        double tmpCaloricity = 0;
        for(Vegetable vegetable : ingredients){
            tmpCaloricity += vegetable.getCaloricity() * vegetable.getQuantity() / 100;
        }
        return tmpCaloricity + flavoringCalority;
    }
}
