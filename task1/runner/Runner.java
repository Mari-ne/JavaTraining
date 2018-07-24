package task1.runner;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Comparator;
import org.apache.log4j.Logger;

import task1.entity.*;
import task1.service.CookTable;
import task1.service.CaloricityFilter;
import task1.service.comporator.*;

/**
 *
 * @author Admin
 */
public class Runner {
    
    final private static Logger LOGGER = Logger.getLogger(Runner.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LOGGER.debug("Start\n");
        
        while(true){
            Scanner input = new Scanner(System.in);

            String name = "";
            CookTable table = new CookTable();
            
            LOGGER.debug("Start of new iteration\n");
            
            LOGGER.info("Hello! Now you will create new salad!\n");
            while(name.equals("")){
                LOGGER.info("Firstly, input name for new salad: ");
                name = input.nextLine();
                if(name.equals("")){
                    LOGGER.error("Empty string input\n");
                    LOGGER.info("Name can't be empty!\n");
                }
            }            
            LOGGER.debug("Succesful input of name\n");
            
            LOGGER.info("Next, you will choose ingedients. If you don't want some vegetable, input 0.\n");
            table.chooseIngredients();            
            LOGGER.debug("Succesful ingedients choose\n");

            Salad salad = new Salad(name, table.getVegetables());
            LOGGER.debug("New object of Salad was created\n");
            
            LOGGER.info("You made next salad:\n");
            salad.show();
            
            LOGGER.info("Next, input bounds for searching.\n");
            double lower = 0.0, upper = 0.0;
            while(true){
                try{
                    LOGGER.info("Lower bound: ");
                    lower = input.nextDouble();
                    
                    LOGGER.info("Upper bound: ");
                    upper = input.nextDouble();
                    if(upper > lower){
                        break;
                    }
                    else{
                        LOGGER.error("Upper bound less then lower bound\n");
                        LOGGER.info("Lower bound must be less then upper!\n");
                    }
                }
                catch(InputMismatchException e){
                    LOGGER.error(e.getMessage(), e);
                    LOGGER.info("Bounds should be numbers!\n");
                    input.nextLine();
                }                
            }
            List<Vegetable> filterd = CaloricityFilter.filtration(salad, lower, upper);
            LOGGER.info("Search results:\n");
            if(filterd.isEmpty()){
                LOGGER.info("None\n");
            }
            else{
                filterd.forEach((vegi) -> vegi.show(true));
            }
            LOGGER.debug("Search succesfully completed\n");
            
            
            LOGGER.info("Next, choose parametr for sorting.\n");
            LOGGER.info("1. Name\n");
            LOGGER.info("2. Caloricity\n");
            LOGGER.info("3. Quantity\n");
            int choose = 0;
            Comparator<Vegetable> compor = null;
            while(choose < 1 || choose > 3){
                try{
                    choose = input.nextInt();
                    switch(choose){
                        case 1:{
                            compor = new NameComparator();
                            break;
                        }
                        case 2:{
                            compor = new CaloricityComparator();
                            break;
                        }
                        case 3:{
                            compor = new QuantityComparator();
                            break;
                        }
                        default:{
                            LOGGER.error("Number out of range\n");
                            LOGGER.info("You should input 1, 2 or 3!\n");
                        }
                    }
                }
                catch(InputMismatchException e){
                    LOGGER.error(e.getMessage(), e);
                    LOGGER.info("You should input 1, 2 or 3!\n");
                    input.nextLine();
                }
            }
            salad.sortIngredients(compor);
            salad.show();            
            LOGGER.debug("Sorting succesfully completed\n");
            
            LOGGER.info("Do you want to create a new salad? If yes, then input 1.\n");
            int in = 0; //if user type not int, programm will finish its work (beause not 1)
            try{
                in = input.nextInt();
            }
            catch(InputMismatchException e){
                //there is no need to do something, because it means that user want funish work
                //try-catch is need only to prevent error
            }
            if(in != 1){
                LOGGER.info("Bye!\n");
                break;
            }
        }
        LOGGER.debug("End");
    }
}
