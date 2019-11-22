package Lab3.UserData;

import java.util.*;

public class UserData {
    Map<String, String> data = new TreeMap<String, String>();

    public void addUser(String username, String password){
        data.put(username, password);
    }

    public Map<String, String> getData(){
        return data;
    }
}
