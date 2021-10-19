/**
 * @author Nguyễn Xuân Trường
 */
public class Employee {
    private int id;
    private String name;
    private String birthyear;
    private String email;
    private double salary;
    private String department;
    private String jobTitle;
    private boolean isMedical;
    private int lineInFile;

    public Employee(int id, String name, String birthyear, String email, double salary, String department, String jobTitle, boolean isMedical, int lineInFile) {
        this.id = id;
        this.name = name;
        this.birthyear = birthyear;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.jobTitle = jobTitle;
        this.isMedical = isMedical;
        this.lineInFile = lineInFile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setIsMedical(boolean isMedical) {
        this.isMedical = isMedical;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isMedical() {
        return isMedical;
    }

    public int getLineInFile() {
        return lineInFile;
    }
}
