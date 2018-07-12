/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import vegetable.Vegetable;

/**
 *
 * @author Admin
 */
public class Sort {
    public static Vegetable[] sortName(Vegetable[] vegetables, boolean isReverse){
        for(int i = 0; i < vegetables.length; i ++){
            Vegetable veg = vegetables[i];
            int min_i = i;
            for(int j = i + 1; j < vegetables.length; j ++){
                if(veg.getName().compareTo(vegetables[j].getName()) > 0 || isReverse){
                    veg = vegetables[j];
                    min_i = j;
                }
            }
            if(i != min_i){
                Vegetable tmp = vegetables[i];
                vegetables[i] = vegetables[min_i];
                vegetables[min_i] = tmp;
            }
        }
        return vegetables;
    }
    
    public static Vegetable[] sortGroup(Vegetable[] vegetables, boolean isReverse){
        for(int i = 0; i < vegetables.length; i ++){
            Vegetable veg = vegetables[i];
            int min_i = i;
            for(int j = i + 1; j < vegetables.length; j ++){
                if(veg.getGroupName().compareTo(vegetables[j].getGroupName()) > 0 || isReverse){
                    veg = vegetables[j];
                    min_i = j;
                }
            }
            if(i != min_i){
                Vegetable tmp = vegetables[i];
                vegetables[i] = vegetables[min_i];
                vegetables[min_i] = tmp;
            }
        }
        return vegetables;
    }
    
    public static Vegetable[] sortColor(Vegetable[] vegetables, boolean isReverse){
        for(int i = 0; i < vegetables.length; i ++){
            Vegetable veg = vegetables[i];
            int min_i = i;
            for(int j = i + 1; j < vegetables.length; j ++){
                if(veg.getColor().compareTo(vegetables[j].getColor()) > 0 || isReverse){
                    veg = vegetables[j];
                    min_i = j;
                }
            }
            if(i != min_i){
                Vegetable tmp = vegetables[i];
                vegetables[i] = vegetables[min_i];
                vegetables[min_i] = tmp;
            }
        }
        return vegetables;
    }
    
    public static Vegetable[] sortCaloricity(Vegetable[] vegetables, boolean isReverse){
        for(int i = 0; i < vegetables.length; i ++){
            Vegetable veg = vegetables[i];
            int min_i = i;
            for(int j = i + 1; j < vegetables.length; j ++){
                if(veg.getCaloricity() > vegetables[j].getCaloricity() || isReverse){
                    veg = vegetables[j];
                    min_i = j;
                }
            }
            if(i != min_i){
                Vegetable tmp = vegetables[i];
                vegetables[i] = vegetables[min_i];
                vegetables[min_i] = tmp;
            }
        }
        return vegetables;
    }
    
    public static Vegetable[] sortQuantity(Vegetable[] vegetables, boolean isReverse){
        for(int i = 0; i < vegetables.length; i ++){
            Vegetable veg = vegetables[i];
            int min_i = i;
            for(int j = i + 1; j < vegetables.length; j ++){
                if(veg.getQuantity() > vegetables[j].getQuantity() || isReverse){
                    veg = vegetables[j];
                    min_i = j;
                }
            }
            if(i != min_i){
                Vegetable tmp = vegetables[i];
                vegetables[i] = vegetables[min_i];
                vegetables[min_i] = tmp;
            }
        }
        return vegetables;
    }
}
