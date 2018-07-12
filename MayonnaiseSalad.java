/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salad;

import vegetable.Vegetable;

/**
 *
 * @author Admin
 */
public class MayonnaiseSalad extends Salad{
    //класс Салат с майонезом
    
    public MayonnaiseSalad(String name, Vegetable[] ingredients){
        super(name, "Майонез", ingredients, 150.0);
    }
    
    @Override
    public int getFlavoring(){
        return 1;
    }
}
