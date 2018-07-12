package table;

import java.lang.System;
import vegetable.*;

/**
 *
 * @author Admin
 */
public class CookTable {
    
    Vegetable[] vegetables;
    
    public CookTable(){
        Basil basil = new Basil(0);
        Carrot carrot = new Carrot(0);
        Celery celery = new Celery(0);
        Dill dill = new Dill(0);
        Pepper pepperRed = new Pepper("Красный", 0);
        Pepper pepperYellow = new Pepper("Желтый", 0);
        Tomat tomat = new Tomat(0);
        vegetables = new Vegetable[]{carrot, celery, pepperYellow, pepperRed, tomat, basil, dill};
    }
    
    public boolean showOneIngredient(int number){
        if(number >= vegetables.length){
            return false;
        }
        vegetables[number].showShort();
        return true;
    }
    
    public void setQuantities(int[] quantities){
        for(int i = 0; i < vegetables.length; i ++){
            vegetables[i].setQuantity(quantities[i]);
        }
    }
    
    public Vegetable[] getIngredients(){
        int number = 0;
        for(int i = 0; i < vegetables.length; i ++){
            if(vegetables[i].getQuantity() != 0){
                number ++;
            }
        }
        Vegetable[] ingredients = new Vegetable[number];
        for(int i = 0, j = 0; i < vegetables.length; i ++){
            if(vegetables[i].getQuantity() != 0){
                ingredients[j ++] = vegetables[i];
            }
        }
        return ingredients;
    }
    
    public void setIngredients(Vegetable[] ingredients){
        for(int i = 0, j = 0; i < vegetables.length; i ++){
            if(vegetables[i].getName().equals(ingredients[j].getName())){
                vegetables[i].setQuantity(ingredients[j].getQuantity());
                j ++;
            }
        }
    }
}
