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
public class VinegarSalad extends Salad{
    //класс Салат с уксусом
    
    public VinegarSalad(String name, Vegetable[] ingredients){
        super(name, "Уксус", ingredients, 4.0);
    }
    
    @Override
    public int getFlavoring(){
        return 2;
    }

}
