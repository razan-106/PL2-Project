import java.awt.*;
import javax.swing.*; //المكتبات دي علشان ال GUI 

public class StaffUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Staff Board"); //بعمل ال frame الاساسي 
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new BorderLayout()); //بحدد نوع ال layout
        frame.getContentPane().setBackground(new Color(30, 41, 59)); //لون ال frame
        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane(); //بحط صفحات مختلفة جوا الframe اللي عملته
        JPanel pmpanel = new JPanel(); //بعمل panel لكل تاب
        JPanel sppanel = new JPanel();

        tabbedPane.addTab("Project Manager", pmpanel);
        pmpanel.setBackground(new Color(30,41,59));
        tabbedPane.setForegroundAt(0, Color.lightGray);
        tabbedPane.setBackgroundAt(0, new Color(30,41,59));

        tabbedPane.addTab("Servise Provider", sppanel);
        sppanel.setBackground(new Color(30,41,59));
        tabbedPane.setForegroundAt(1, Color.lightGray);   
        tabbedPane.setBackgroundAt(1, new Color(30,41,59));
        
        frame.add(tabbedPane, BorderLayout.CENTER); //احط التابين في نص ال frame

        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new BorderLayout());

        tabbedPane.addTab("Staff", staffPanel);
        staffPanel.setBackground(new Color(30, 41, 59));
        tabbedPane.setForegroundAt(2, Color.lightGray);
        tabbedPane.setBackgroundAt(2, new Color(30,41,59));
        
        String [] staffcolumns = {"ID", "Name", "Phone", "Role"}; //اسماء اعمده جدول ال staff
        Object[][] staffData = {}; 
        JTable staffTable = new JTable(staffData, staffcolumns);  //بعمل الاعمده واعرض البيانات للجدول
        staffTable.setBackground(new Color(15, 23, 42));
        JScrollPane staffScroll = new JScrollPane(staffTable) ;  //scroll علشان يظهر كامل
        staffScroll.getViewport().setBackground(new Color(15, 23, 42));
        staffPanel.add(staffScroll) ;
        staffTable.getTableHeader().setBackground(new Color(30,41,59));
        staffTable.getTableHeader().setForeground(Color.WHITE);            // لون كتابة الهيدر

        String[] pmColumns = {"Booking ID", "Customer", "Event Type", "Status"} ; //اعمده جدول ال project manager
        Object[][] pmData = {} ;  
        
        JTable pmTable = new JTable(pmData, pmColumns);
        pmTable.setBackground(new Color(15, 23 , 42));
        JScrollPane pmScroll = new JScrollPane(pmTable);
        pmScroll.getViewport().setBackground(new Color(15, 23, 42));
        pmTable.getTableHeader().setBackground(new Color(30, 41, 59));
        pmTable.getTableHeader().setForeground(Color.WHITE);
        pmpanel.setLayout(new BorderLayout()); 
        pmpanel.add(pmScroll, BorderLayout.CENTER);

        JButton assignBtn = new JButton("Assign to Service Provider"); //بعمل ال button
        JPanel pmButtons = new JPanel(); //panel to place the button in
        pmButtons.add(assignBtn);
        pmpanel.add(pmButtons,BorderLayout.SOUTH);
        pmButtons.setBackground(new Color(30, 41, 59));   // 

        String[] spColumns={"Task ID", "Event", "Status"};
        Object[][] spData = {};
        
        JTable spTable = new JTable(spData, spColumns); //انشاء جدول SP
        spTable.setBackground(new Color(15,23,42));
        JScrollPane spScroll = new JScrollPane(spTable);
        spScroll.getViewport().setBackground(new Color(15,23,42));
        spTable.getTableHeader().setBackground(new Color(30, 41, 59));
        spTable.getTableHeader().setForeground(Color.WHITE);
        sppanel.setLayout(new BorderLayout()); //بحط الجدول في تاب ال  Service Provider
        sppanel.add(spScroll, BorderLayout.CENTER);

        JPanel spControls = new JPanel();
        spControls.setLayout(new FlowLayout());

        JLabel dateLabel = new JLabel("Delivery Date:"); //ادخل التاريخ
        JTextField dateField = new JTextField(10);
        dateLabel.setForeground(Color.lightGray);
        dateField.setBackground(new Color(15, 23, 42));   // خلفية 
        dateField.setForeground(Color.WHITE);
        dateField.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

        JLabel priceLabel = new JLabel("Price:"); //ادخل السعر
        JTextField priceField = new JTextField(10);
        priceLabel.setForeground(Color.lightGray);
        priceField.setBackground(new Color(15, 23, 42));
        priceField.setForeground(Color.WHITE);
        priceField.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

        JButton submitPriceBtn = new JButton("Submit Price");
        JButton confirmDateBtn = new JButton("Confirm Date");
        spControls.setBackground(new Color(30, 41, 59)); 


        spControls.add(priceLabel);
        spControls.add(priceField);
        spControls.add(dateLabel);
        spControls.add(dateField);
        spControls.add(submitPriceBtn);
        spControls.add(confirmDateBtn);

        sppanel.add(spControls, BorderLayout.SOUTH);
    }
}