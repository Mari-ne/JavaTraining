package runner;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Comparator;

import entity.*;
import service.CookTable;
import service.Filter;
import service.comporator.*;

/**
 *
 * @author Admin
 */
public class Task1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        while(true){
            Scanner input = new Scanner(System.in);

            String name = "";
            CookTable table = new CookTable();

            System.out.println("Hello! Now you will create new salad!");
            System.out.print("Firstly, input name for new salad: ");
            while(name.equals("")){
                name = input.nextLine();
                if(name.equals("")){
                    System.out.println("Name can't be empty!");
                    System.out.print("Input name for new salad: ");
                }
            }
            System.out.println("Next, you will choose ingedients. If you don't want some vegetable, input 0.");
            table.chooseIngredients();

            Salad salad = new Salad(name, table.getVegetables());

            System.out.println("You made next salad:");
            salad.show();
            
            System.out.println("Next, input bounds for searching.");
            double lower = 0.0, upper = 0.0;
            while(true){
                try{
                    System.out.print("Lower bound: ");
                    lower = input.nextDouble();
                    
                    System.out.print("Upper bound: ");
                    upper = input.nextDouble();
                    if(upper > lower){
                        break;
                    }
                    else{
                        System.out.println("Lower bound must be less then upper!");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Bounds should be numbers!");
                    input.nextLine();
                }                
            }
            List<Vegetable> filterd = Filter.twoBoundFilter(salad, lower, upper);
            System.out.println("Search results:");
            if(filterd.isEmpty()){
                System.out.println("None");
            }
            else{
                filterd.forEach((vegi) -> vegi.show(true));
            }
            
            System.out.println("Next, choose parametr for sorting.");
            System.out.println("1. Name");
            System.out.println("2. Caloricity");
            System.out.println("3. Quantity");
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
                            System.out.println("You should input 1, 2 or 3!");
                        }
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("You should input 1, 2 or 3!");
                    input.nextLine();
                }
            }
            salad.sortIngredients(compor);
            salad.show();
            
            System.out.println("Do you want to create a new salad? If yes, then input 1.");
            input.next();
            String in = input.nextLine();
            if(!(in.equals("1"))){
                System.out.println("Bye!");
                break;
            }
            
        }        
    }
    
}
