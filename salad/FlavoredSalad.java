/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salad;

import flavoring.Flavoring;
import vegetable.Vegetable;
import calority.CalorityCounter;

/**
 *
 * @author Admin
 */
public class FlavoredSalad  extends Salad{
    private Flavoring flavoring;
    
    public FlavoredSalad(String name, Vegetable[] vegetables, Flavoring flavoring){
        super(name, vegetables);
        this.flavoring = flavoring;
        System.out.println(totalCaloricity);
        totalCaloricity = CalorityCounter.countTotalCaloricity(this);
        System.out.println(totalCaloricity);
    }
    
    @Override
    public Flavoring getFlavoring(){
        return flavoring;
    }
    
    public void setFlavoring(Flavoring newFlavoring){
        flavoring = newFlavoring;
        totalCaloricity = CalorityCounter.countTotalCaloricity(this);
    }
    
    @Override
    public void show(){
        super.show();
        System.out.println("Калорийность салата (без заправки): " + (totalCaloricity - flavoring.getCaloricity()));
        System.out.println("В качестве заправки использовался " + flavoring.getName() + ".");
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof FlavoredSalad)){
            return false;
        }
        
        FlavoredSalad tmp = (FlavoredSalad)obj;
        if(super.equals((Salad)tmp)){
            if(flavoring.equals(tmp.flavoring)){
                return true;
            }
        }
        return false;
    }
    
}
