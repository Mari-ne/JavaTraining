/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author Admin
 */
public class ShowMenu {
 
    public static void showMainMenu(){
        System.out.println("1. Изменение салата.");
        System.out.println("2. Просмотр данных о салате.");
        System.out.println("3. Сортировка по параметрам.");
        System.out.println("4. Поиск по калорийности.");
        System.out.println("5. Завершение работы.");
    }
    
    public static void showChangeMenu(){
        System.out.println("1. Изменение названия.");
        System.out.println("2. Изменение ингредиентов.");
        System.out.println("3. Изменение заправки");
        System.out.println("4. Назад.");
    }
    
    public static void showSortMenu(){
        System.out.println("1. Сортировка по имени (возрастание).");
        System.out.println("2. Сортировка по имени (убывание).");
        System.out.println("3. Сортировка по группе (возрастание).");
        System.out.println("4. Сортировка по группе (убывание).");
        System.out.println("5. Сортировка по цвету (возрастание).");
        System.out.println("6. Сортировка по цвету (убывание).");
        System.out.println("7. Сортироска по калорийности (возрастание).");
        System.out.println("8. Сортироска по калорийности (убывание).");
        System.out.println("9. Сортировка по количеству (возрастание).");
        System.out.println("10. Сортировка по количеству (убывание).");
        System.out.println("11. Назад.");
    }
    
    public static void showFilterMenu(){
        System.out.println("1. Поиск с верхней и нижней границей.");
        System.out.println("2. Поиск с нижней границей.");
        System.out.println("3. Поиск с верхней границейю");
        System.out.println("4. Назад.");
    }
    
    public static void showFlavoringMenu(){
        System.out.println("Выберите заправку для салата:");
        System.out.println("1. Майонез (680 ккал / 100 г)");
        System.out.println("2. Уксус (18 ккал / 100 г)");
        System.out.println("3. Без заправки");
    }
}
