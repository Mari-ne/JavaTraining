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
public class CaloricityFilter {
    
    public static Vegetable[] twoBoundsFilter(Vegetable[] vegetables, double min, double max){
        int[] numbers = new int[vegetables.length];
        int j = 0;
        for(int i = 0 ; i < vegetables.length; i ++){
            if(vegetables[i].getCaloricity() >= min && vegetables[i].getCaloricity() <= max){
                numbers[j] = i + 1;
                j ++;
            }
        }
        Vegetable[] tmp = new Vegetable[j];
        j = 0;
        while(j < numbers.length && numbers[j] != 0){
            tmp[j] = vegetables[numbers[j] - 1];
            j ++;
        }
        return tmp;
    }
    
    public static Vegetable[] oneBoundFilter(Vegetable[] vegetables, double bound, boolean isMinor){
        int[] numbers = new int[vegetables.length];
        int j = 0;
        for(int i = 0 ; i < vegetables.length; i ++){
            if((vegetables[i].getCaloricity() >= bound && isMinor) || (vegetables[i].getCaloricity() <= bound && !isMinor)){
                numbers[j] = i + 1;
                j ++;
            }
        }
        Vegetable[] tmp = new Vegetable[j];
        j = 0;
        while(j < numbers.length && numbers[j] != 0){
            tmp[j] = vegetables[numbers[j] - 1];
            j ++;
        }
        return tmp;
    }
    
}
