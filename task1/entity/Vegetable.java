package entity;

/**
 *
 * @author Admin
 */
public class Vegetable{
    private String name;
    private double caloricity; //amount of clories on 100g
    private double quantity;
    
    public Vegetable(String name, double caloricity){
        this.name = name;
        this.caloricity = caloricity;
    }
    
    public Vegetable(String name, double caloricity, double quantity){
        this(name, caloricity);
        this.quantity = quantity;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public double getCaloricity(){
        return caloricity;
    }
    
    public void setCaloricity(double newCaloricity){
        caloricity = newCaloricity;
    }
    
    public double getQuantity(){
        return quantity;
    }
    
    public void setQuantity(double newQuantity){
        quantity = newQuantity;
    }
    
    public void show(boolean withQuantity){
        System.out.printf("%10s\t|\t%.2f\t|\t", name, caloricity);
        if(withQuantity){
            //quantity will be shown in the end of making salad
            System.out.println(quantity);
        }
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
