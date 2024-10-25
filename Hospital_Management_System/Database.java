import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Database {
    public <T> void addht(String key, T value,  Hashtable<String, List<T>> table){
        List<T> slots = table.getOrDefault(key, new ArrayList<>());
        slots.add(value);
        table.put(key, slots);
    }

    public <T> T popht(String key, int index, Hashtable<String, List<T>> table){
        List<T> slots = table.get(key);
        if (slots != null && index >= 0 && index < slots.size()) {
            T removedSlot = slots.remove(index);
            table.put(key, slots);
            return removedSlot;
        }
        return null;
    }
    public <T> T getht(String key, int index, Hashtable<String, List<T>> table){
       
        List<T> slots = table.get(key);
        if (slots != null && index >= 0 && index < slots.size()) {
            return slots.get(index);
        }
        return null;
    }
    
    public <T> void printListTable(Hashtable<String, List<T>> table){
        for (String key : table.keySet()) {
            System.out.println("Key: " + key);
            List<T> slots = table.get(key);
            for (T slot : slots) {
                System.out.println(slot.toString());
            }
        }
    }
    public <T> void printItemTable(Hashtable<String, T> table){
        for (String key : table.keySet()) {
            System.out.println(key + " ");
            System.out.println(table.get(key).toString());
            
        }
    }
    
    public <T> void printList(List<T> list) {
        int i=0;
        for (T element : list) {

            System.out.println(i + ". "+element.toString());
        }
    }

}
