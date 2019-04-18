import backend.* ; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

//OUR MAIN CLASS
class PreviousBookings2 extends JFrame {
   String msg  ; 
    
    PreviousBookings2(){}
    
  public PreviousBookings2(String s){
  //FORM TITLE
 // super("Button Column Example");
    msg=s ;            //taking username . 
    
  JFrame jfr = new JFrame("Vixigo") ; 
        jfr.setLayout(null) ; 
        jfr.setSize(900,900) ;
       // jfr.setLocationRelativeTo(null);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
        jfr.setResizable(false);
        jfr.setContentPane(new JLabel(new ImageIcon("C:\\Users\\acer\\Desktop\\programme codes\\OOP mini project\\Sol-House-Ibiza-1-900x900.jpg")));
        
       JLabel heading = new JLabel("Previous Bookings") ;
       jfr.add(heading) ; 
        heading.setBounds(100,00,600,100) ; 
        heading.setFont(new Font("Times New Roman" ,Font.BOLD,50));
        heading.setForeground(Color.BLACK);
        
        
         JButton b1 = new JButton("Back") ; 
        JButton b2 = new JButton("Logout") ; 
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               new User_page(s).setVisible(true) ; 
                  jfr.dispose(); 
            }
                    
        }) ; 
        
         b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
               new sign_in().setVisible(true) ; 
                  jfr.dispose(); 
            }
                    
        }) ;
         
         b1.setBounds(600, 10, 150,60);
         b2.setBounds(750, 10, 150,60);
 
         b1.setForeground(Color.BLACK);
         b2.setForeground(Color.BLACK); 
         
         jfr.add(b1) ; 
         jfr.add(b2) ; 
  //DATA FOR OUR TABLE
 @SuppressWarnings("deprecation")
         
Object[][] data= Main.returnBookings(s);
Object[][] data2 = new Object[data.length][]; 
 
for(int i=0 ; i<data.length ;i++)
{   
   data2[i] = new Object[data[i].length+2] ; 
     for(int j=0 ; j<data[i].length ;j++)
     {  data2[i][j]= data[i][j] ; 
        
     }
     data2[i][data[i].length] = "Cancel" ;
     data2[i][data[i].length+1]="Modify" ; 

}
  //COLUMN HEADERS
  String columnHeaders[]={"Refernce_Id ","Booking Name","No Of People" , "city", "HotelName",", Room Type" ,  "No Of Rooms" ,"CheckIn Date","CheckOut Date",  "Aadhaar","pan no ","Cancel" , "Modify"};
  //CREATE OUR TABLE AND SET HEADER
  JTable table=new JTable(data2,columnHeaders);
  
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    table.getColumnModel().getColumn(0).setPreferredWidth(120);
    table.getColumnModel().getColumn(1).setPreferredWidth(120);
    table.getColumnModel().getColumn(2).setPreferredWidth(120);
    table.getColumnModel().getColumn(3).setPreferredWidth(90);
    table.getColumnModel().getColumn(4).setPreferredWidth(200);
    table.getColumnModel().getColumn(6).setPreferredWidth(120);
    table.getColumnModel().getColumn(7).setPreferredWidth(190);
    table.getColumnModel().getColumn(8).setPreferredWidth(190);
    table.getColumnModel().getColumn(9).setPreferredWidth(150);
    table.getColumnModel().getColumn(10).setPreferredWidth(40);
    table.getColumnModel().getColumn(11).setPreferredWidth(100);
    table.getColumnModel().getColumn(12).setPreferredWidth(100);
    table.setBackground(Color.YELLOW);
    table.setForeground(Color.darkGray);
    

  //SET CUSTOM RENDERER TO TEAMS COLUMN
  table.getColumnModel().getColumn(11).setCellRenderer(new ButtonRenderer());

  //SET CUSTOM EDITOR TO TEAMS COLUMN
  table.getColumnModel().getColumn(11).setCellEditor(new ButtonEditor(new JTextField()));
  
    table.getColumnModel().getColumn(12).setCellRenderer(new ButtonRenderer());

  //SET CUSTOM EDITOR TO TEAMS COLUMN
  table.getColumnModel().getColumn(12).setCellEditor(new ButtonEditor(new JTextField()));
  
  

  //SCROLLPANE,SET SZE,SET CLOSE OPERATION
  JScrollPane pane=new JScrollPane(table);
  //getContentPane().add(pane);
  pane.setSize(700,700);
  pane.setBounds(50,200,800,600) ; 
  pane.setBorder( BorderFactory.createLineBorder(Color.BLACK,5));
  
  jfr.add(pane) ; 
   jfr.setVisible(true) ; 

  //setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
   
   
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PreviousBookings2().setVisible(false)  ;
            }
        });
   
    
  }


}

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{

  //CONSTRUCTOR
  public ButtonRenderer() {
    //SET BUTTON PROPERTIES
    setOpaque(true);
  }
  @Override
  public Component getTableCellRendererComponent(JTable table, Object obj,
      boolean selected, boolean focused, int row, int col) {

    //SET PASSED OBJECT AS BUTTON TEXT
      setText((obj==null) ? "":obj.toString());

    return this;
  }

}

//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
  protected JButton btn;
   private String lbl;
   private Boolean clicked;

   public ButtonEditor(JTextField txt) {
    super(txt);

    btn=new JButton();
    btn.setOpaque(true);

    //WHEN BUTTON IS CLICKED
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        fireEditingStopped();
      }
    });
  }

   //OVERRIDE A COUPLE OF METHODS
   @Override
  public Component getTableCellEditorComponent(JTable table, Object obj,
      boolean selected, int row, int col) {

      //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
     lbl=(obj==null) ? "":obj.toString();
     btn.setText(lbl);
     clicked=true;
    return btn;
  }

  //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
   @Override
  public Object getCellEditorValue() {

     if(clicked)
      {
      //SHOW US SOME MESSAGE
        JOptionPane.showMessageDialog(btn, lbl+" Clicked");
      }
    //SET IT TO FALSE NOW THAT ITS CLICKED
    clicked=false;
    return new String(lbl);
  }

   @Override
  public boolean stopCellEditing() {

         //SET CLICKED TO FALSE FIRST
      clicked=false;
    return super.stopCellEditing();
  }

  @Override
  protected void fireEditingStopped() {
    // TODO Auto-generated method stub
    super.fireEditingStopped();
  }
}
