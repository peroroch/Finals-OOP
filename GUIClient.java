/**
 * @author Bui Le Anh Duy, Pham Vu Hai, Chu Minh Quan, Ta Quang Minh, Duong Nghia Quyet
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUIClient extends JFrame {
    public static ArrayList<Employee> search(String searchString, BufferedReader br) throws IOException {
        String line = br.readLine();
        ArrayList<Employee> employees = new ArrayList<>();
        int i = 0;
        while (line != null) {
            i++;
            String[] info = line.split("\t");
            if (info[0].toLowerCase().contains(searchString.toLowerCase())){
                Employee employee = new Employee(
                        Integer.parseInt(info[7]),
                        info[0],
                        info[1],
                        info[2],
                        Double.parseDouble(info[3]),
                        info[4],
                        info[5],
                        Boolean.parseBoolean(info[6]),
                        i);
                employees.add(employee);    
            }
            line = br.readLine();
        }
        return employees;
    } //written by Bui Le Anh Duy

    public static void writeChanges(ArrayList<Employee> employees, File clinicData) throws IOException {
        File tempFile = new File(clinicData.getParent() + "\\temp.txt");
        tempFile.createNewFile();
        try {
            PrintWriter tempWriter = new PrintWriter(new BufferedWriter(new FileWriter(tempFile)), true);
            BufferedReader tempReader = new BufferedReader(new FileReader(clinicData));
            String line = tempReader.readLine();
            int i = 0;
            while (line != null) {
                i++;
                if (!employees.isEmpty())
                    if (i == employees.get(0).getId()) {
                        line = employees.get(0).getName() + "\t" +
                                employees.get(0).getBirthyear() + "\t" +
                                employees.get(0).getEmail() + "\t" +
                                String.format("%.2f", employees.get(0).getSalary()) + "\t" +
                                employees.get(0).getDepartment() + "\t" +
                                employees.get(0).getJobTitle() + "\t" +
                                (employees.get(0).isMedical() ? "1" : "0") + "\t" +
                                employees.get(0).getId();
                        employees.remove(0);
                    }
                tempWriter.println(line);
                line = tempReader.readLine();
            }
            tempWriter.close();
            tempReader.close();
            boolean result = clinicData.delete();
            tempFile.renameTo(clinicData);
        } catch (IOException exception) {
            boolean result = tempFile.delete();
            throw new IOException(exception.getMessage(), exception.getCause());
        }
    } //written by Pham Vu Hai

    private final ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == chooseFile) {
                if (unsave) {
                    try {
                        int answer = JOptionPane.showConfirmDialog(panel,
                                "You have unsaved changes. Are you sure you want to continue?",
                                "Warning",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (answer == 1) {
                            return;
                        }
                    } catch (HeadlessException exception3) {
                    }
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(".")); //sets directory when filechooser is opened (same directory as GUIClient.java)
                int response = fileChooser.showOpenDialog(null); //select file to open
                if(response == JFileChooser.APPROVE_OPTION) {
                    clinicData = new File(fileChooser.getSelectedFile().getAbsolutePath());
                }
            } else //written by Chu Minh Quan
            if (event.getSource() == searchFile) {
                if (clinicData == null) {
                    try {
                        JOptionPane.showMessageDialog(panel, "No file has been chosen. Please try again.");
                        return;
                    } catch (HeadlessException exception3) {
                    }
                }
                if (unsave) {
                    try {
                        int answer = JOptionPane.showConfirmDialog(panel,
                                "You have unsaved changes. Are you sure you want to continue?",
                                "Warning",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (answer == 1) {
                            return;
                        }
                    } catch (HeadlessException exception3) {
                    }
                }
                String searchString = JOptionPane.showInputDialog(panel, "Enter the name you want to search.");
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(clinicData));
                } catch(FileNotFoundException exception) {
                    exception.printStackTrace();
                    try {
                        JOptionPane.showMessageDialog(panel, "The chosen file can not be found. Please try again.");
                        return;
                    } catch (HeadlessException exception3) {
                    }
                }
                boolean i = true;
                while (i) 
                    try {
                        employees = GUIClient.search(searchString, br);
                        br.close();
                        i = false;
                    } catch(IOException exception) {
                        i = true;
                    }
                if (employees.isEmpty()) {
                    try {
                        JOptionPane.showMessageDialog(panel, "Search yielded no results. Please try again.");
                    } catch (HeadlessException exception3) {
                    }
                }
            } else //written by Duong Nghia Quyet
            if (event.getSource() == editEmployee) {
                if (employees.isEmpty()) {
                    try {
                        JOptionPane.showMessageDialog(panel, "No search has been performed. Please try again.");
                        return;
                    } catch (HeadlessException exception3) {
                    }
                }

                Employee choice = null;
                try {
                    choice = (Employee) JOptionPane.showInputDialog(panel,
                            "Choose the employee whose information you want to edit.",
                            "Employee List",
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon(),
                            employees.toArray(),
                            employees.get(0));
                } catch (HeadlessException exception3) {
                }

                JDialog editorDialog = new JDialog(self, choice.getName());
                editorDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel editor = new JPanel(),
                        options = new JPanel();
                editor.setLayout(new GridLayout(8,2,0,1));

                JButton b = new JButton("Just fake button");
                Dimension buttonSize = b.getPreferredSize();
                editor.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 3.5)+20,
                        (int)(buttonSize.getHeight() * 3.5)+20 * 2));

                editor.add(new JLabel("ID:"));
                JTextField IDField = new JTextField(String.valueOf(choice.getId()));
                editor.add(IDField);

                editor.add(new JLabel("Name:"));
                JTextField nameField = new JTextField(choice.getName());
                editor.add(nameField);

                editor.add(new JLabel("Date of Birth:"));
                JTextField DOBField = new JTextField(choice.getBirthyear());
                editor.add(DOBField);

                editor.add(new JLabel("Email:"));
                JTextField emailField = new JTextField(choice.getEmail());
                editor.add(emailField);

                editor.add(new JLabel("Salary:"));
                JTextField salaryField = new JTextField(String.valueOf(choice.getSalary()));
                editor.add(salaryField);

                editor.add(new JLabel("Department:"));
                JTextField departmentField = new JTextField(choice.getDepartment());
                editor.add(departmentField);

                editor.add(new JLabel("Job Title:"));
                JTextField jobField = new JTextField(choice.getJobTitle());
                editor.add(jobField);

                editor.add(new JLabel("Is a Medical Employee? (True or False):"));
                JTextField medicField = new JTextField(String.valueOf(choice.isMedical()));
                editor.add(medicField);

                JButton okButton = new JButton("OK");
                Employee finalChoice = choice;
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int initialID = finalChoice.getId();
                        if (!IDField.getText().isEmpty())
                            try {
                                finalChoice.setId(Integer.parseInt(IDField.getText()));
                            } catch (final NumberFormatException exception) {
                                try {
                                    JOptionPane.showMessageDialog(panel, "Input for ID not valid. Please try again.");
                                    return;
                                } catch (HeadlessException exception3) {
                                }
                            }
                        if (!nameField.getText().isEmpty()) finalChoice.setName(nameField.getText());
                        if (!DOBField.getText().isEmpty()) finalChoice.setBirthyear(DOBField.getText());
                        if (!emailField.getText().isEmpty()) finalChoice.setEmail(emailField.getText());
                        if (!salaryField.getText().isEmpty())
                            try {
                                finalChoice.setSalary(Float.parseFloat(salaryField.getText()));
                            } catch (final NumberFormatException exception) {
                                try {
                                    finalChoice.setId(initialID);
                                    JOptionPane.showMessageDialog(panel, "Input for Salary not valid. Please try again.");
                                    return;
                                } catch (HeadlessException exception3) {
                                }
                            }
                        if (!departmentField.getText().isEmpty()) finalChoice.setDepartment(departmentField.getText());
                        if (!jobField.getText().isEmpty()) finalChoice.setJobTitle(jobField.getText());
                        if (!medicField.getText().isEmpty()) finalChoice.setIsMedical(Boolean.parseBoolean(medicField.getText()));

                        unsave = true;

                        editorDialog.setVisible(false);
                        editorDialog.dispose();
                    }
                });
                options.add(okButton);

                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        editorDialog.setVisible(false);
                        editorDialog.dispose();
                    }
                });
                options.add(closeButton);

                editorDialog.getContentPane().add(editor, BorderLayout.NORTH);
                editorDialog.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
                editorDialog.getContentPane().add(options, BorderLayout.SOUTH);

                editorDialog.pack();
                editorDialog.setLocationRelativeTo(self);
                editorDialog.setVisible(true);
            } else //written by Ta Quang Minh
            if (event.getSource() == writeToFile) {
                if (!unsave) {
                    try {
                        JOptionPane.showMessageDialog(panel, "There is nothing to save.");
                        return;
                    } catch (HeadlessException exception3) {
                    }
                }
                boolean i = true;
                while (i)
                    try {
                        writeChanges(employees, clinicData);
                        i = false;
                        unsave = false;
                    } catch(IOException exception) {
                        i = true;
                    }
            } else //written by Chu Minh Quan
            if (event.getSource() == exit) {
                if (unsave) {
                    try {
                        int answer = JOptionPane.showConfirmDialog(panel,
                                "You have unsaved changes. Are you sure you want to continue?",
                                "Warning",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (answer == 1) {
                            return;
                        }
                    } catch (HeadlessException exception3) {
                    }
                }

                self.setVisible(false);
                self.dispose();
                System.exit(0);
            } //written by Duong Nghia Quyet
        }
    };

    private JFrame self = this;
    private JPanel panel = new JPanel();
    private JButton chooseFile = new JButton("Choose clinic database file");
    private JButton searchFile = new JButton("Search clinic database");
    private JButton editEmployee = new JButton("Edit searched employees' informations");
    private JButton writeToFile = new JButton("Write changes to database");
    private JButton exit = new JButton("Exit");

    private File clinicData = null;
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private boolean unsave = false;

    public GUIClient() {
        super("Employee Manager Pro");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //to make the program shutdown when the exit button on the GUI is pressed.
        panel.setLayout(new GridLayout(5,1,1,1));
        
        JButton b = new JButton("Just a fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+20,
                (int)(buttonSize.getHeight() * 3.5)+20 * 3));
        
        chooseFile.addActionListener(listener);
        chooseFile.setFocusable(false);
        panel.add(chooseFile);

        searchFile.addActionListener(listener);
        searchFile.setFocusable(false);
        panel.add(searchFile);

        editEmployee.addActionListener(listener);
        editEmployee.setFocusable(false);
        panel.add(editEmployee);

        writeToFile.addActionListener(listener);
        writeToFile.setFocusable(false);
        panel.add(writeToFile);

        exit.addActionListener(listener);
        exit.setFocusable(false);
        panel.add(exit);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    } //written by Pham Nguyen Minh Duc

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        GUIClient client = new GUIClient();
    } // written by Pham Nguyen Minh Duc
}
