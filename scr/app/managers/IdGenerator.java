package managers;

import java.util.Collection;
import models.Dragon;

public class IdGenerator{
    public static int currentMaxId = 0;

    public int configureFromCollection(Collection<Dragon> dragons){
        int maxId = 0;
        for (Dragon element: dragons){
            if (element.getId() > maxId){
                maxId = element.getId();
            }
        }
        return maxId;
    }

    public int nextId(){
        return IdGenerator.currentMaxId ++;
    }
}