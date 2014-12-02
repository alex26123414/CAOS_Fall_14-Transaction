package hashmapex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HashMapEx {

    public static void main(String[] args) {
        
        HashMap<String, ArrayList<String>> emailMap = new HashMap<>();
        
        ArrayList<String> emails1 = new ArrayList<>();
        emails1.add("cons0046@stud.kea.dk");
        emails1.add("alex@itsol.dk");
        
        emailMap.put("Alex", emails1);
        
        ArrayList<String> emails2 = new ArrayList<>();
        emails2.add("nick0046@stud.kea.dk");
        emails2.add("nick@itsol.dk");
        
        emailMap.put("Nick", emails2);
        
        System.out.println(emailMap);
        
        Object[] keys = emailMap.keySet().toArray();
        for (int i=0; i< keys.length; i++) {
            ArrayList<String> value = emailMap.get((String)keys[i]);
            System.out.println((String)keys[i]);
            
            for (int j = 0; j< value.size(); j++) {
                System.out.print(value.get(j)+j+" ");
            }
            System.out.println("");
        }
        
        
        
    }
    
}
