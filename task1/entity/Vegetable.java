package task1.entity;

import java.util.Formatter;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class Vegetable{
    private String name;
    private double caloricity; //amount of clories on 100g
    private double quantity;
    
    final private static Logger LOGGER = Logger.getLogger(Vegetable.class);
    
    public Vegetable(String name, double caloricity){
        this.name = name;
        this.caloricity = caloricity;
        LOGGER.debug("Create " + toString() + "\n");
    }
    
    public Vegetable(String name, double caloricity, double quantity){
        this(name, caloricity);
        this.quantity = quantity;
        LOGGER.debug("Create " + toString() + "\n");
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        Formatter format = new Formatter();
        format.format("Set new name %s -> %s. Object ", name, newName);
        LOGGER.debug(format.toString() + toString() + "\n");
        name = newName;
    }
    
    public double getCaloricity(){
        return caloricity;
    }
    
    public void setCaloricity(double newCaloricity){
        Formatter format = new Formatter();
        format.format("Set new caloricity %f -> %f. Object ", caloricity, newCaloricity);
        LOGGER.debug(format.toString() + toString() + "\n");
        caloricity = newCaloricity;
    }
    
    public double getQuantity(){
        return quantity;
    }
    
    public void setQuantity(double newQuantity){
        Formatter format = new Formatter();
        format.format("Set new quantity %f -> %f. Object ", quantity, newQuantity);
        LOGGER.debug(format.toString() + toString() + "\n");
        quantity = newQuantity;
    }
    
    public void show(boolean withQuantity){
        Formatter format = new Formatter();
        format.format("%10s\t|\t%.2f\t|\t", name, caloricity);
        if(withQuantity){
            //quantity will be shown in the end of making salad
            format.format("%f\n", quantity);
        }        
        LOGGER.info(format.toString());
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
            Vegetable tmp = (Vegetable)obj;
            if(name.equals(tmp.name) && caloricity == tmp.caloricity){
                //if(quantity == tmp.quantity){
                return true;
                //}
            }
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return name.hashCode()*31 + (int)caloricity * 11 + (int)quantity;
    }
    
    @Override
    public String toString(){
        String s = this.getClass().getName() + "@" + name + "/" + caloricity + "/" + quantity;
        return s;
    }
}
