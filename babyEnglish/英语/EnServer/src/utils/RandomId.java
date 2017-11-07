package utils;
import java.util.Random;  
  
public class RandomId {  
    private static Random random = new Random();
    private static String table = "456789";  
    private static NumberCaesar caesar = new NumberCaesar();
    public RandomId() {  
       
    }
    
    public static String randomId(int id) {  
        String ret = null,  
            num = String.format("%06d", id);  
        int key = random.nextInt(10),   
            seed = random.nextInt(100);  
        num = caesar.encrypt(table, seed);  
        ret =  String.valueOf(id) + num   
            + String.format("%01d", key)   
            + String.format("%02d", seed);  
         
        return ret;  
    }  
}