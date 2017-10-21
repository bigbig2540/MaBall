/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maballv1;

/**
 *
 * @author suchat
 */
public class test {
    static int numberOfThis = 0;
    static int numberOfTest = 0;
    
    test(double i){
        numberOfThis++;
        System.out.println("numberOfThis = " + numberOfThis);
    }
    
    test(int j){
        
        numberOfTest++;
        System.out.println("numberOfTest = " + numberOfTest);
    }
    
    test(){
        //this(1.0);
        new test(1);
    }
    
    public static void main(String[] args) {
        new test();
        new test();
        new test(1.0);
    }
}
