/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import chef.Chef;
import vegetable.Vegetable;
import sort.Sort;
import calority.CaloricityFilter;
import service.Service;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu {
    
    Chef chef;
    
    public Menu(){
        chef = new Chef();
        chef.makeSalad("", new Vegetable[0], null);
    }  
        
    public void mainMenu(){
        Scanner input = new Scanner(System.in);
        int choose = 0;
        Service.windowClear();
        do{            
            Service.separator();
            ShowMenu.showMainMenu();
            System.out.print("Ваше действие: ");
            choose = input.nextInt();
            Service.separator();
            switch(choose){
                case 1:{
                    changeMenu();
                    Service.windowClear();
                    break;
                }
                case 2:{
                    chef.getSalad().show();
                    break;
                }
                case 3:{
                    sortMenu();
                    Service.windowClear();
                    break;
                }
                case 4:{
                    filterMenu();
                    Service.windowClear();
                    break;
                }
                case 5:{
                    System.out.println("До свидания!");
                    break;
                }
                default:{
                    System.out.println("Ввведите число от 1 до 5!");
                }
            }
        }
        while(choose != 5);
    }
    
    public void changeMenu(){
        Scanner input = new Scanner(System.in);
        int choose = 0;
        Service.windowClear();
        do{
            Service.separator();
            ShowMenu.showChangeMenu();
            System.out.print("Ваше действие: ");
            choose = input.nextInt();
            Service.separator();
            switch(choose){
                case 1:{
                    chef.makeSalad("", chef.getSalad().getIngredients(), chef.getSalad().getFlavoring());
                    break;
                }
                case 2:{
                    chef.makeSalad(chef.getSalad().getName(), new Vegetable[0], chef.getSalad().getFlavoring());
                    break;
                }
                case 3:{
                    chef.makeSalad(chef.getSalad().getName(), chef.getSalad().getIngredients(), null);
                }
                case 4:{
                    break;
                }
                default:{
                    System.out.println("Введите число от 1 до 4!");
                }
            }
        }
        while(choose != 4);
    }
    
    public void sortMenu(){        
        Scanner input = new Scanner(System.in);
        int choose;
        Service.windowClear();
        do{
            Service.separator();
            ShowMenu.showSortMenu();
            System.out.print("Ваше действие: ");
            choose = input.nextInt();
            switch(choose){
                case 1:
                case 2:{
                    Sort.sortName(chef.getSalad().getIngredients(), choose % 2 == 0);
                    break;
                }
                case 3:
                case 4:{
                    Sort.sortGroup(chef.getSalad().getIngredients(), choose % 2 == 0);
                    break;
                }
                case 5:
                case 6:{
                    Sort.sortColor(chef.getSalad().getIngredients(), choose % 2 == 0);
                    break;
                }
                case 7:
                case 8:{
                    Sort.sortCaloricity(chef.getSalad().getIngredients(), choose % 2 == 0);
                    break;
                }
                case 9:
                case 10:{
                    Sort.sortQuantity(chef.getSalad().getIngredients(), choose % 2 == 0);
                    break;
                }
                case 11:{
                    break;
                }
                default:{
                    System.out.println("Введите число от 1 до 11!");
                }
            }
        }
        while(choose != 11);
    }
    
    public void filterMenu(){
        Scanner input = new Scanner(System.in);
        int choose = 0;
        Service.windowClear();
        do{            
            Service.separator();
            Vegetable[] tmp = new Vegetable[0];
            ShowMenu.showFilterMenu();
            System.out.print("Ваше действие: ");
            choose = input.nextInt();
            Service.separator();
            switch(choose){
                case 1:{
                    double min, max;
                    System.out.print("Нижняя граница: ");
                    min = input.nextDouble();
                    System.out.print("Вверхняя граница: ");
                    max = input.nextDouble();
                    tmp = CaloricityFilter.twoBoundsFilter(chef.getSalad().getIngredients(), min, max);
                    break;
                }
                case 2:
                case 3:{
                    double bound;
                    if(choose % 2 == 0){
                        System.out.print("Нижняя граница: ");
                    }
                    else{
                        System.out.print("Вверхняя граница: ");
                    }                    
                    bound = input.nextDouble();                    
                    tmp = CaloricityFilter.oneBoundFilter(chef.getSalad().getIngredients(), bound, choose % 2 == 0);
                    break;
                }
                case 4:{
                    break;
                }
                default:{
                    System.out.println("Введите число от 1 до 4!");
                }
            }
            if(choose > 0 && choose < 4){
                System.out.println("Результат:");
                if(tmp.length == 0){
                    System.out.println("Подходящих овощей нет в рассматриваемом салате.");
                }
                else{
                    for(Vegetable x : tmp){
                        x.show();
                    }
                }
            }
        }
        while(choose != 4);   
    }
}
