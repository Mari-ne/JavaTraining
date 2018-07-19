package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import service.CaloricityCounter;

public class Salad {
    private String name;
    private List<Vegetable> ingredients; //used in salad vegetables
    protected double totalCaloricity;
    
    public Salad(String name, List<Vegetable> ingredients){
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
        totalCaloricity = CaloricityCounter.countTotalCaloricity(this);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public double getTotalCaloricity(){
        return totalCaloricity;
    }
    
    public List<Vegetable> getIngredients(){
        return ingredients;
    }
    
    public void setIngredients(List<Vegetable> newIngredients){
        ingredients.clear();
        ingredients = new ArrayList<>();
        ingredients.addAll(newIngredients);
        totalCaloricity = CaloricityCounter.countTotalCaloricity(this);
    }
    
    public void show(){
        System.out.println("Name: " + name);
        System.out.println("Total caloricity: " + totalCaloricity);
        System.out.printf("%10s\t|%s\t|\t%s\n", "Name", " Caloricity ", "Quantity");
        ingredients.forEach((vegi) -> vegi.show(true));
    }
    
    public void sortIngredients(Comparator<Vegetable> comp){
        Collections.sort(ingredients, comp);
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        
        if(obj.getClass() == this.getClass()){
            Salad tmp = (Salad)obj;
            if(name.equals(tmp.name) && totalCaloricity == tmp.totalCaloricity){
                if(ingredients.equals(tmp.ingredients)){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        int hash = name.hashCode()*21 + (int)totalCaloricity * 7;
        for(Vegetable vegetable : ingredients){
            hash += vegetable.hashCode() * 3;
        }
        return hash;
    }
    
    @Override
    public String toString(){
        String s = this.getClass().getName() + "%" + name;
        for(Vegetable vegetable : ingredients){
            s += "%" + vegetable.toString();
        }
        return s;
    }
}
