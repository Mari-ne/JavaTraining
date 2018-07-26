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
    
    private static final Logger LOGGER = Logger.getLogger(Runner.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LOGGER.debug("Start");
        
        while(true){
            Scanner input = new Scanner(System.in);

            String name = "";
            CookTable table = new CookTable();
            
            LOGGER.debug("Start of new iteration");
            
            LOGGER.info("Hello! Now you will create new salad!");
            while(name.equals("")){
                LOGGER.info("Firstly, input name for new salad: ");
                name = input.nextLine();
                if(name.equals("")){
                    LOGGER.error("Empty string input");
                    LOGGER.info("Name can't be empty!");
                }
            }            
            LOGGER.debug("Succesful input of name");
            
            LOGGER.info("Next, you will choose ingedients. If you don't want some vegetable, input 0.");
            table.chooseIngredients();            
            LOGGER.debug("Succesful ingedients choose");

            Salad salad = new Salad(name, table.getVegetables());
            LOGGER.debug("New object of Salad was created");
            
            LOGGER.info("You made next salad:");
            salad.show();
            
            LOGGER.info("Next, input bounds for searching.");
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
                        LOGGER.error("Upper bound less then lower bound");
                        LOGGER.info("Lower bound must be less then upper!");
                    }
                }
                catch(InputMismatchException e){
                    LOGGER.error("InputMismatchException - Incorrect type input:  double was expected", e);
                    LOGGER.info("Bounds should be numbers!");
                    input.nextLine();
                }                
            }
            List<Vegetable> filterd = CaloricityFilter.filtration(salad, lower, upper);
            LOGGER.info("Search results:");
            if(filterd.isEmpty()){
                LOGGER.info("None");
            }
            else{
                filterd.forEach((vegi) -> vegi.show(true));
            }
            LOGGER.debug("Search succesfully completed");
            
            
            LOGGER.info("Next, choose parametr for sorting.");
            LOGGER.info("1. Name");
            LOGGER.info("2. Caloricity");
            LOGGER.info("3. Quantity");
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
                            LOGGER.error("Number out of range");
                            LOGGER.info("You should input 1, 2 or 3!");
                        }
                    }
                }
                catch(InputMismatchException e){
                    LOGGER.error("InputMismatchException - Incorrect type input: int was expected", e);
                    LOGGER.info("You should input 1, 2 or 3!");
                    input.nextLine();
                }
            }
            salad.sortIngredients(compor);
            salad.show();            
            LOGGER.debug("Sorting succesfully completed.");
            
            LOGGER.info("Do you want to create a new salad? If yes, then input 1.");
            int in = 0; //if user type not int, programm will finish its work (beause not 1)
            try{
                in = input.nextInt();
            }
            catch(InputMismatchException e){
                //there is no need to do something, because it means that user want funish work
                //try-catch is need only to prevent error
            }
            if(in != 1){
                LOGGER.info("Bye!");
                break;
            }
        }
        LOGGER.debug("End");
    }
}
