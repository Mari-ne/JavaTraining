package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Formatter;

import entity.Vegetable;
import service.logger.*;

/**
 *
 * @author Admin
 */
public class CookTable {
    private List<Vegetable> vegetables;
    private static InfoLogger infoLog = new InfoLogger(InfoLogger.class);
    private static ErrorLogger errorLog = new ErrorLogger(ErrorLogger.class);
    private static DebugLogger debugLog = new DebugLogger(DebugLogger.class);
    
    public CookTable(){
        vegetables = new ArrayList<>();
        
        vegetables.add(new Vegetable("Tomat", 18.0));
        vegetables.add(new Vegetable("Pepper", 27.0));
        vegetables.add(new Vegetable("Dill", 38.0));
        vegetables.add(new Vegetable("Celery", 12.0));
        vegetables.add(new Vegetable("Carrot", 36.0));
        vegetables.add(new Vegetable("Basil", 22.0));
    }
    
    public void chooseIngredients(){
        Scanner input  = new Scanner(System.in);
        
        while(true){
            Formatter format = new Formatter(); //java.util.Formatter
            format.format("%10s\t|%s\t|\t%s\n", "Name", " Caloricity ", "Quantity");//create string with set format
            infoLog.info(format.toString());
            double quantity;
            int zeroQuantity = 0; //need for preventing situation when there is no chosen vegetables
            for(int i = 0; i < vegetables.size(); i ++){
                //used simple for (not for-each) for opportunity to return to previous element
                try{
                    vegetables.get(i).show(false);
                    quantity = input.nextDouble();
                    vegetables.get(i).setQuantity(quantity);
                    if(quantity == 0.0){
                        //if user chose not use i-ty vegetable
                        zeroQuantity ++;
                    }
                }
                catch(InputMismatchException e){
                    errorLog.getLogger().error("Incorrext input of number\n");
                    infoLog.info("You should input only numbers!\n");
                    input.nextLine();
                    i --;
                }                
            }
            if(zeroQuantity == vegetables.size()){
                //all vegetables is unused
                errorLog.getLogger().error("Was choosen 0 ingredients\n");
                infoLog.info("You should choose at least one vegetable to your salad!\n");
            }
            else{
                break;
            }
        }
    }
    
    public List<Vegetable> getVegetables(){
        debugLog.getLogger().debug("Get choosen ingredients\n");
        List<Vegetable> tmp = new ArrayList<>();
        for(Vegetable vegi : vegetables){
            if(vegi.getQuantity() != 0.0){
                tmp.add(vegi);
            }
        }
        return tmp;
    }
}
