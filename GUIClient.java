/**
 * @author Bùi Lê Anh Duy, Phạm Vũ Hải, Tạ Quang Minh, Chu Minh Quân
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

public class GUIClient {
    public ArrayList<Employee> search(String searchString, BufferedReader br) {
        String line = new String("");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try{
            int i = 0;
            while (line != null) {
                line = br.readLine();
                i++;
                String[] info = line.split("\t");
                if (info[1].contains(searchString)){
                    Employee employee = new Employee(Integer.parseInt(info[0]), info[1], info[2], info[3], Double.parseDouble(info[4]), info[5], info[6], Boolean.parseBoolean(info[7]), i);
                    employees.add(employee);    
                }
            }
        } catch (IOException e) {
            return null; 
        }
        return employees;
    } //written by Bùi Lê Anh Duy

    public boolean writeChanges(ArrayList<Employee> employees, BufferedWriter bw) {
        //TODO
        for (Employee o : employees) {
            try {
                bw.write(o.getId()+"\t"+
                    o.getName()+"\t"+
                    o.getBirthyear()+"\t"+  
                    o.getEmail()+"\t"+
                    String.format("%.2f", o.getSalary())+"\t"+
                    o.getDepartment()+"\t"+
                    o.getJobTitle()+"\t"+
                    (o.isMedical() ? "1" : "0"));
                bw.newLine();
                bw.flush();
            } catch(IOException e) {
                return false;
            }
        }
        return true;
    } //written by Phạm Vũ Hải

    public static void main(String[] args) {
        //TODO
    }
}
