package vegetable;

import java.lang.System;

/**
 *
 * @author Admin
 */
public class Vegetable {
    private String name;
    private String color;
    private double caloricity; //калорийность на 100 грамм
    private String groupName;
    private int quantity; //количество, используемое для салата (в граммах)
    
    public Vegetable(String name, String color, double caloricity, String group, int quantity){
        this.name = name;
        this.color = color;
        this.caloricity = caloricity;
        this.groupName = group;
        this.quantity = quantity;
    }
    
    public String getName(){
        return name;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String newColor){
        color = newColor;
    }
    
    public double getCaloricity(){
        return caloricity;
    }
    
    public void setCaloricity(double newCaloricity){
        if(newCaloricity > 0.0){
            caloricity = newCaloricity;
        }
    }
    
    public String getGroupName(){
        return groupName;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int newQuantity){
        if(newQuantity > 0){
            quantity = newQuantity;
        }
    }
    
    public void show(){
        System.out.printf("%10s\t|%15s\t|%15s\t|\t%.2f\t|\t%d\n", 
                name, groupName, color, caloricity, quantity);
    }
    
    public void showShort(){
        System.out.printf("%10s\t|%15s\t|%15s\t|\t%.2f\t|\t", 
                name, groupName, color, caloricity);
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        
        if(!(obj instanceof Vegetable)){
            return false;
        }
        
        Vegetable tmp = (Vegetable)obj;
        if(name.equals(tmp.name)){
            if(color.equals(tmp.color)){
                if(caloricity == tmp.caloricity){
                    return true;
                }
            }
        }
        return false;
    }
}
