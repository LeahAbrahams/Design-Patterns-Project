
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Condition;
import java.util.ArrayList;
import java.util.List;

import models.Row;
/**
 *
 * @author ברכי
 */
public class AndCondition implements Condition {

    private List<Condition> conditions=new ArrayList<>();

    public AndCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean evaluate(Row row) {
        for (Condition condition : conditions) {
            if (!condition.evaluate(row)) {
                return false;
            }
        }
        return true;
    }
    
}
