import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

//used inheritance
public class StaffUI extends JFrame { 
    //Global Variable 
    private JTable pmTable, spTable,staffTable ;
    private JButton assignBtn, submitBtn, confirmBtn ;
    private JTextField priceField, dateField ;

    private final Color Main_BackGround = new Color(30, 41, 59 );
    private final Color Snd_BackGround = new Color (15, 23, 42);
    private final Color Text_setForeground = Color.white;
    private final Color Text_setForeground_pan = Color.lightGray;

//Frame
public StaffUI() {
    setTitle("Staff Board");
    setSize(700,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    getContentPane().setBackground(Main_BackGround);


    JTabbedPane tabbedPane = new JTabbedPane(); 
    tabbedPane.setBackground(Main_BackGround);
    tabbedPane.setForeground(Text_setForeground_pan);
    //بحط التابات
    tabbedPane.addTab("Project Manager",createPMPanel());
    tabbedPane.addTab("Service Provider",createSPPanel());
    tabbedPane.addTab("Staff",createStaffPanel());
    add(tabbedPane, BorderLayout.CENTER);
}

//function Project Manager
private JPanel createPMPanel() {
    JPanel pmPanel = new JPanel();
    pmPanel.setLayout(new BorderLayout());
    pmPanel.setBackground(Main_BackGround);
    
    String [] pmcolumns = {"Booking ID", "Customer","Event Type","Status"};
    Object [][] pmdata = {};
    pmTable = createTable(pmdata,pmcolumns);

    JScrollPane pmscroll = new JScrollPane(pmTable);
    pmscroll.getViewport().setBackground(Snd_BackGround);
    pmPanel.add(pmscroll,BorderLayout.CENTER);

    assignBtn = new JButton("Assign to Service Provider");
    JPanel pmButtons = new JPanel();
    pmButtons.setBackground(Main_BackGround);
    pmButtons.add(assignBtn);
    pmPanel.add(pmButtons , BorderLayout.SOUTH);
    return pmPanel;
}

//Function Service Provider
private JPanel createSPPanel() {
    JPanel spPanel = new JPanel();
    spPanel.setLayout(new BorderLayout());
    spPanel.setBackground(Main_BackGround);

    String [] spcolumns = {"Task ID", "Event", "Status"};
    Object [][] spdata ={};
    spTable = createTable(spdata, spcolumns);

    JScrollPane spscroll = new JScrollPane(spTable);
    spscroll.getViewport().setBackground(Snd_BackGround);
    spPanel.add(spscroll,BorderLayout.CENTER);

    JPanel spcontrols = new JPanel();
    spcontrols.setLayout(new FlowLayout());
    spcontrols.setBackground(Main_BackGround);

    JLabel priceLabel = new JLabel("Price:");
    priceLabel.setForeground(Text_setForeground_pan);
    priceField = new JTextField(10);
    priceField.setBackground(Snd_BackGround);
    priceField.setForeground(Text_setForeground);
    priceField.setFont(new Font("Arial", Font.BOLD, 14));

    JLabel dateLabel = new JLabel("Delivery date:");
    dateLabel.setForeground(Text_setForeground_pan);
    dateField = new JTextField(10);
    dateField.setBackground(Snd_BackGround);
    dateField.setForeground(Text_setForeground);
    dateField.setFont(new Font("Arial", Font.BOLD, 14));

    submitBtn = new JButton("Submit Price");
    confirmBtn = new JButton("Confirm Date");

    spcontrols.add(priceLabel);
    spcontrols.add(priceField);
    spcontrols.add(dateLabel);
    spcontrols.add(dateField);
    spcontrols.add(submitBtn);
    spcontrols.add(confirmBtn);

    spPanel.add(spcontrols,BorderLayout.SOUTH);
    return spPanel;
}

//Function Staff
private JPanel createStaffPanel() {
    JPanel staffPanel = new JPanel();
    staffPanel.setLayout(new BorderLayout());
    staffPanel.setBackground(Main_BackGround);
    
    String [] staffcolumns = {"ID", "Name", "Phone", "Role"};
    Object [][] staffdata ={};
    staffTable = createTable(staffdata, staffcolumns);

    JScrollPane staffscroll = new JScrollPane(staffTable);
    staffscroll.getViewport().setBackground(Snd_BackGround);
    staffPanel.add(staffscroll,BorderLayout.CENTER);

    return staffPanel;
}

//Function Create Table
private JTable createTable(Object[][] data, String [] columns) {
    JTable table = new JTable(data, columns);
    table.setBackground(Snd_BackGround);
    table.setForeground(Text_setForeground);
    
    JTableHeader header = table.getTableHeader();
    header.setBackground(Main_BackGround);
    header.setForeground(Text_setForeground);
    
    return table;
}
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StaffUI().setVisible(true);
        });
}
}