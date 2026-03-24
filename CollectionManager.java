import java.io.BufferedInputStream;
import  java.io.BufferedWriter;
import  java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

public class CollectionManager{
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
}