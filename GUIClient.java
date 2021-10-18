import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class GUIClient {
    public ArrayList<Employee> search(String searchString, BufferedReader br) {
        String line = new String("");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try{
            while (line != null) {
                line = br.readLine();
                Employee employee = new Employee();
                employees.add(employee);    
            }
        } catch (IOException e) {
            return null;
        }
        return employees;
    }
}