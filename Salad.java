package salad;

import vegetable.Vegetable;
import calority.CalorityCounter;
import java.util.Arrays;
import java.lang.System;

/**
 *
 * @author Admin
 */
public class Salad {
    String name;
    String flavoring;
    double flavoringCaloricity;
    double totalCaloricity; //общая калорийность салата
    Vegetable[] ingredients; //используемые в салате ингредиенты
    
    public Salad(String name, String flavoring, Vegetable[] vegetables, double flavorCaloricity){
        this.name = name;
        this.flavoring = flavoring;
        this.flavoringCaloricity = flavorCaloricity;
        
        ingredients = new Vegetable[vegetables.length];
        ingredients = Arrays.copyOf(vegetables, vegetables.length);
        
        totalCaloricity = CalorityCounter.countTotalCaloricity(ingredients, this.flavoringCaloricity);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public int getFlavoring(){
        return 3;
    }
    
    public void setFlaforing(String newFlavoring){
        flavoring = newFlavoring;
    }
    
    public double getTotalCaloricity(){
        return totalCaloricity;
    }
    
    protected void setTotalCaloricity(double newTotalCaloricity){
        if(totalCaloricity == 0){
            totalCaloricity = newTotalCaloricity;
        }
    }
    
    public Vegetable[] getIngredients(){
        return ingredients;
    }
    
    public void setIngredients(Vegetable[] newIngredients){
        totalCaloricity = 0;
        ingredients = new Vegetable[newIngredients.length];
        ingredients = Arrays.copyOf(newIngredients, newIngredients.length);
        totalCaloricity = CalorityCounter.countTotalCaloricity(ingredients, flavoringCaloricity);
    }
    
    public void show(){
        System.out.println("Вы сделали следующий салат:");
        System.out.println("Салат '" + name + "'.");
        System.out.println("Для салата использовались следующие овощи:");
        for(int i = 1; i <= ingredients.length; i ++){
            System.out.print(i + "|");
            ingredients[i - 1].show();
        }
        System.out.println("Калорийность салата (без заправки): " + (totalCaloricity + flavoringCaloricity));
        System.out.println("Калорийность салата (с учетом заправки): " + totalCaloricity);
        System.out.println("В качестве заправки использовался " + flavoring + ".");
    }
}
