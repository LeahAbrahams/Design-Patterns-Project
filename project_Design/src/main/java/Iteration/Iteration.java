package Iteration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ברכי
 */
import java.util.List;

import models.Row;
public class Iteration {
    private  final Iiteration byIteration;
    public Iteration(Iiteration byIteration){
        this.byIteration=byIteration;
    }

    
 public void iteration(List<Row>rows)
   {
byIteration.iterate(rows);
   }
    

   

}

