/**
 * @author Nguyen Xuan Truong
 */
public class Employee {
    private int id;
    private String name;
    private String birthYear;
    private String email;
    private double salary;
    private String department;
    private String jobTitle;
    private boolean isMedical;
    private int lineInFile;

    public Employee(int id, String name, String birthYear, String email, double salary, String department, String jobTitle, boolean isMedical, int lineInFile) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
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

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
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

    public String getBirthYear() {
        return birthYear;
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

    public String toString() {
        return this.name;
    }
}
