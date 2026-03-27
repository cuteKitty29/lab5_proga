package managers;

import java.io.BufferedInputStream;
import  java.io.BufferedWriter;
import  java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import models.Dragon;

public class CollectionManager{
    private PriorityQueue<Dragon> collection = new PriorityQueue<Dragon>();



    ArrayList<Object> objectList = new ArrayList<>();

    public void add (Object ob){
        objectList.add(ob);
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

    public PriorityQueue<Dragon> getCollection(){
        return collection;
    }

    public Dragon head(){
        if (collection.isEmpty()){
            return null;
        }
        else{
            collection.peek();
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
            System.out.println(null);    // вывод элементов, желательно не так 
        }
        else{
            Dragon element = collection.peek();
            System.out.println(element);
            collection.remove(element);
        
        }
    }

    public long SumOfAge(){
        if (collection.isEmpty()){
            return null;
        }
        else{
            long sumAge;
            for (Dragon element: collection){
                sumAge = sumAge + element.getAge();
            }
            return sumAge;
        }
    }


    public void clear(){
        collection.clear();
    }
}