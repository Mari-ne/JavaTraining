/*
 *Создать консольное приложение, удовлетворяющее следующим требованиям:
 *1.	Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
 *2.	Каждый класс должен иметь исчерпывающее смысл название и информативный состав.
 *3.	Наследование должно применяться только тогда, когда это имеет смысл.
 *4.	При кодировании должны быть использованы соглашения об оформлении кода java code convention.
 *5.	Классы должны быть грамотно разложены по пакетам.
 *6.	Работа с консолью или консольное меню должно быть минимальным.
 *7.	Для хранения параметров инициализации можно использовать файлы.
 *
 *Вариант 4:
 *Шеф-повар. Определить иерархию овощей. 
 *Сделать салат. Посчитать калорийность. 
 *Провести сортировку овощей для салата на основе одного из параметров. 
 *Найти овощи в салате, соответствующие заданному диапазону калорийности.
 */
package runner;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Comparator;

import entity.*;
import service.CookTable;
import service.CaloricityFilter;
import service.comporator.*;
import service.logger.*;

/**
 *
 * @author Admin
 */
public class Runner {

    private static InfoLogger infoLog = new InfoLogger(InfoLogger.class);
    private static ErrorLogger errorLog = new ErrorLogger(ErrorLogger.class);
    private static DebugLogger debugLog = new DebugLogger(DebugLogger.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        debugLog.getLogger().debug("Start\n");
        
        while(true){
            Scanner input = new Scanner(System.in);

            String name = "";
            CookTable table = new CookTable();
            
            debugLog.getLogger().debug("Start of new iteration\n");
            
            infoLog.info("Hello! Now you will create new salad!\n");
            infoLog.info("Firstly, input name for new salad: ");
            while(name.equals("")){
                name = input.nextLine();
                if(name.equals("")){
                    errorLog.getLogger().error("Empty string input\n");
                    //errorLog.error();
                    infoLog.info("Name can't be empty!\n");
                    infoLog.info("Input name for new salad: ");
                }
            }
            
            debugLog.getLogger().debug("Succesful input of name\n");
            
            infoLog.info("Next, you will choose ingedients. If you don't want some vegetable, input 0.\n");
            table.chooseIngredients();
            
            debugLog.getLogger().debug("Succesful ingedients choose\n");

            Salad salad = new Salad(name, table.getVegetables());

            debugLog.getLogger().debug("Created new object of Salad\n");
            
            infoLog.info("You made next salad:\n");
            salad.show();
            
            infoLog.info("Next, input bounds for searching.\n");
            double lower = 0.0, upper = 0.0;
            while(true){
                try{
                    infoLog.info("Lower bound: ");
                    lower = input.nextDouble();
                    
                    infoLog.info("Upper bound: ");
                    upper = input.nextDouble();
                    if(upper > lower){
                        break;
                    }
                    else{
                        errorLog.getLogger().error("Upper bound less then lower bound\n");
                        infoLog.info("Lower bound must be less then upper!\n");
                    }
                }
                catch(InputMismatchException e){
                    errorLog.getLogger().error("Incorrect input of number\n");
                    infoLog.info("Bounds should be numbers!\n");
                    input.nextLine();
                }                
            }
            List<Vegetable> filterd = CaloricityFilter.twoBoundFilter(salad, lower, upper);
            infoLog.info("Search results:\n");
            if(filterd.isEmpty()){
                infoLog.info("None\n");
            }
            else{
                filterd.forEach((vegi) -> vegi.show(true));
            }
            debugLog.getLogger().debug("Search succesfully completed\n");
            
            
            infoLog.info("Next, choose parametr for sorting.\n");
            infoLog.info("1. Name\n");
            infoLog.info("2. Caloricity\n");
            infoLog.info("3. Quantity\n");
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
                            errorLog.getLogger().error("Number out of range\n");
                            infoLog.info("You should input 1, 2 or 3!\n");
                        }
                    }
                }
                catch(InputMismatchException e){
                    errorLog.getLogger().error("Incorrect input of number\n");
                    infoLog.info("You should input 1, 2 or 3!\n");
                    input.nextLine();
                }
            }
            salad.sortIngredients(compor);
            salad.show();
            
            debugLog.getLogger().debug("Sorting succesfully completed\n");
            
            infoLog.info("Do you want to create a new salad? If yes, then input 1.\n");
            input.next();
            String in = input.nextLine();
            if(!(in.equals("1"))){
                infoLog.info("Bye!\n");
                break;
            }
            
        }
        
        debugLog.getLogger().debug("End");
    }
    
}
