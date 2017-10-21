/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maballv1;

public interface RandomColor {
    String[][] COLORLIST = {{"Darkgreen","Brown"},{"Green","Brown"},{"Green","Darkgreen"},{"Red","Darkgreen"},{"Red","Green"},{"Red","Yellow"},{"Yellow","Green"},{"Yellow","Darkgreen"},{"Red","Brown"}};
    //String COLORLIST = "abc" ;
    String[] getColorList();
}
