package Lab3.UserData;

import java.util.*;

public class UserData {
    Map<String, String> data = new TreeMap<String, String>();

    public void addUser(String username, String password) {
        data.put(username, password);
    }

    public Map<String, String> getData() {
        return data;
    }

    public static void main(String[] args) {
        UserData data = new UserData();
        data.addUser("Vasya", "lohiasd89");
        data.addUser("Ignat228", "qwert");
        data.addUser("Popa", "sRuchkoy");
        data.addUser("Stalin1945", "Yosya");
        Map<String, String> m = data.getData();
        for (Map.Entry<String, String> entry : m.entrySet()) {
            if (entry.getValue().length() > 6) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
