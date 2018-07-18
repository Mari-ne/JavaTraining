package salad;

import vegetable.Vegetable;
import calority.CalorityCounter;
import flavoring.Flavoring;
import java.util.Arrays;
import java.lang.System;

/**
 *
 * @author Admin
 */
public class Salad {
    private String name;
    protected double totalCaloricity; //общая калорийность салата
    private Vegetable[] ingredients; //используемые в салате ингредиенты
    
    public Salad(String name, Vegetable[] vegetables){
        this.name = name;
        
        ingredients = new Vegetable[vegetables.length];
        ingredients = Arrays.copyOf(vegetables, vegetables.length);
        
        totalCaloricity = CalorityCounter.countTotalCaloricity(this);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public Flavoring getFlavoring(){
        return new Flavoring();
    }
    
    public double getTotalCaloricity(){
        return totalCaloricity;
    }
    
    protected void setTotalCaloricity(double newTotalCaloricity){
        totalCaloricity = newTotalCaloricity;
    }
    
    public Vegetable[] getIngredients(){
        return ingredients;
    }
    
    public void setIngredients(Vegetable[] newIngredients){
        totalCaloricity = 0;
        ingredients = new Vegetable[newIngredients.length];
        ingredients = Arrays.copyOf(newIngredients, newIngredients.length);
        totalCaloricity = CalorityCounter.countTotalCaloricity(this);
    }
    
    public void show(){
        System.out.println("Вы сделали следующий салат:");
        System.out.println("Салат '" + name + "'.");
        System.out.println("Для салата использовались следующие овощи:");
        for(int i = 1; i <= ingredients.length; i ++){
            System.out.print(i + "|");
            ingredients[i - 1].show();
        }
        System.out.println("Калорийность салата: " + totalCaloricity);
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        
        if(!(obj instanceof Salad)){
            return false;
        }         
        Salad tmp = (Salad)obj;
        if(name.equals(tmp.name)){
            for(int i = 0; i < ingredients.length; i ++){
                if(!ingredients[i].equals(tmp.ingredients[i])){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
