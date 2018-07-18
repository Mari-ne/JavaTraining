package flavoring;

/**
 *
 * @author Admin
 */
public class Flavoring {
    
    private String name;
    private double caloricity;
    
    public Flavoring(){
        name = "-";
        caloricity = 0.0;
    }
    
    public Flavoring(String name, double caloricity){
        this.name = name;
        this.caloricity = caloricity;
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
    
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Flavoring)){
            return false;
        }
        
        Flavoring tmp = (Flavoring)obj;
        
        if(name.equals(tmp.name)){
            if(caloricity == tmp.caloricity){
                return true;
            }
        }
        return false;
    }
}
