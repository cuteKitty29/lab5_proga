package managers;

import java.io.BufferedInputStream;
import  java.io.BufferedWriter;
import  java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import models.Dragon;
import models.DragonHead;
import util.*;

public class CollectionManager{
    private PriorityQueue<Dragon> collection = new PriorityQueue<Dragon>();



    ArrayList<Object> objectList = new ArrayList<>();

    public void add (Dragon dr){
        Validator validator = new Validator();
        try{
            validator.validateDragon(dr);
            collection.add(dr);

        }
        catch (ValidationException e){
            e.getMessage();
        }
    }

    public void load(String fileName) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            // прочитать XML
            // распарсить (можно через DOM / вручную)
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка загрузки файла");
        }   
    }

    public void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            
        }
        catch (Exception e) {
            System.out.println("Ошибка сохранения");
        }
    }

    public Dragon head(){
        if (collection.isEmpty()){
            return null;
        }
        else{
            return collection.peek();
        }
    }

    public boolean removeById(int id){
        for (Dragon element: collection){
            if (element.getId() == id){
                collection.remove(element);
                return true;
            }
        }
        return false;
    }

    public void removeHead(){
        if (collection.isEmpty()){
            System.out.println("null");    // вывод элементов, желательно не так 
        }
        else{
            Dragon element = collection.peek();
            System.out.println(element);
            collection.remove(element);
        
        }
    }

    public long SumOfAge(){
        long sumAge = 0;
        if (collection.isEmpty()){
            return sumAge;
        }
        else{
            for (Dragon element: collection){
                sumAge = sumAge + element.getAge();
            }
            return sumAge;
        }
    }


    public void clear(){
        collection.clear();
    }

    public void update(int id, Dragon newDr){
        try{
            Dragon oldDragon;
            for (Dragon element: collection){
                if (id == element.getId()){
                    oldDragon = element;
                }
            }
            if (oldDragon == null){
                throw new CommandException("Exception in update command: previous dragon not found");
            }
            newDr.setId(id);
            collection.remove(oldDragon);
            collection.add(newDr);
        }
        catch (CommandException e){
            e.getMessage();
        }
    }

    public void addIfMin(Dragon dr){
        if (collection.isEmpty()){
            collection.add(dr);
        }
        else{
            Dragon nDr = collection.peek()
            if((nDr.compareTo(dr)) > 0){
                collection.add(dr);
            }
        }
    }

    public int countGreaterThanAge(long age){
        int count = 0;
        for (Dragon element: collection){
            if (age < element.getAge()){
                count++;
            }
        }
        return count;
    }

    public ArrayList<DragonHead> printFieldDescendingHead(){
        ArrayList<DragonHead> headList = new ArrayList<DragonHead>();
        for (Dragon element: collection){
            headList.add(element.getHead());
        }
        Collections.sort(headList);
        return headList;
        
    }

    public void show(){
        for (Dragon element: collection){
            System.out.println(element);
        }
    }




}
