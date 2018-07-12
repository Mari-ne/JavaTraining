/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef;

import java.util.Scanner;
import salad.MayonnaiseSalad;
import salad.Salad;
import salad.VinegarSalad;
import table.CookTable;
import vegetable.*;
import service.Service;

/**
 *
 * @author Admin
 */
public class Chef {
    
    Salad salad;
    
    public void makeSalad(String name_, Vegetable[] ingredients_, int flavoring){
        Scanner input = new Scanner(System.in);
        String name;
        CookTable table = new CookTable();
        
        if(name_.equals("")){
            System.out.print("Введите название Вашего салата: ");
            name = input.nextLine();   
        }
        else{
            name = name_;
        }
        
        if(ingredients_.length == 0){
            int check = 0;
            int[] quantity = new int[7];
            while(check == 0){
                Service.separator();
                System.out.println("Для создания салата выберите необходимые ингредиенты.");
                System.out.println("Если Вам необходим ингредиент, то введите его количество (в граммах),");
                System.out.println("иначе - введите 0");
                int num = 0;
                while(table.showOneIngredient(num ++)){
                    quantity[num - 1] = input.nextInt();
                    if(quantity[num - 1] < 0){
                        System.out.println("Ошибка ввода! Должно быть введено число не меньще 0!");
                        num --;
                    }
                }
                for(int x : quantity){
                    if(x > 0){
                        check = 1;
                        break;
                    }
                }
                if(check == 0){
                    System.out.println("В салате должен быть хоть один ингредиент!");
                }
            }
            table.setQuantities(quantity);
        }
        else{
            table.setIngredients(ingredients_);
        }
        Service.separator();
        int choosen;
        if(flavoring == 0){
            System.out.println("Выберите заправку для салата:");
            System.out.println("1. Майонез (680 ккал / 100 г)");
            System.out.println("2. Уксус (18 ккал / 100 г");
            System.out.println("3. Без заправки");
        }
        do{
            if(flavoring == 0){
                choosen = input.nextInt();
            }
            else{
                choosen = flavoring;
            }
            switch(choosen){
                case 1:{
                    salad = new MayonnaiseSalad(name, table.getIngredients());
                    break;
                }
                case 2:{
                    salad = new VinegarSalad(name, table.getIngredients());
                    break;
                }
                case 3:{
                    salad = new Salad(name, "-", table.getIngredients(), 0);
                    break;
                }
                default:{
                    System.out.println("Введите 1, 2 или 3!");
                }
            }
        }
        while(choosen < 1 || choosen > 3);
    }
    
    public Salad getSalad(){
        return salad;
    }
}
