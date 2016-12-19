/*
 * Adv Obj-Orien Prg Desgn
 * GUI based 'Contact List' Program
 * Osama Alghamdi s1049374
 * November 28,2016
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.List;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.naming.ldap.SortKey;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;

import java.util.*;

public class MainContact extends JFrame implements ActionListener {
	JLabel First_name;
	JLabel Last_name;
	JLabel lblDueDate;
	JTextField First_name_TF;
	JTextField Last_name_TF;
	JButton save;
	int getSelectedRow;
	JButton Cancel;	
	JLabel Phone_number;
	JTextField Phone_number_TF;
	JLabel Work_number;
	JTextField Work_number_TF;
	 JTable Table;
	 JFrame frame;
	 int row = 0;
	 ArrayList<String> Which_Language = new ArrayList<String>();
	 Object data[][] = new Object[100][4];;
	 FileWriter File_Writer;
	BufferedWriter bufferWriter;
	JTextField search_TF;
	TableRowSorter<TableModel> rowSorter;
	String Which_language_select;
	JRadioButton red;
	JRadioButton yellow;
	JRadioButton blue;
	JRadioButton green;
    JRadioButton magenta;
				
		//create a file
		File filename = new File("ContactList.txt");
		DefaultTableModel TM;
		BufferedReader ReaderBuffer;

	
    public MainContact() {

    	language_selector();
    }
    
    private void initUI() {
    	
    	color();
    	sorted_buttom();
        createMenuBar();
        Search_bar();
        create_table();
        setTitle(Which_Language.get(0));
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
		JButton add = new JButton(Which_Language.get(1));
		add.addActionListener(this);
        add(add);
        
        JButton edit = new JButton(Which_Language.get(2));
        edit.addActionListener(this);
        add(edit);
        
        JButton delete = new JButton(Which_Language.get(3));
        delete.addActionListener(this);
        add(delete);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        

        JMenu file = new JMenu(Which_Language.get(4));
        file.setMnemonic(KeyEvent.VK_F);
        

        JMenuItem NEW_ = new JMenuItem(Which_Language.get(5));
        NEW_.setMnemonic(KeyEvent.VK_E);
        //NEW_.addActionListener(new MenuActionListener());
        file.add(NEW_);
       
        JMenuItem open = new JMenuItem(Which_Language.get(6));
        open.setMnemonic(KeyEvent.VK_E);
        open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readContactFile("Open");
			}
		});
        file.add(open);


        JMenuItem save = new JMenuItem(Which_Language.get(7));
        save.setMnemonic(KeyEvent.VK_E);
        save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTableDataToTxt();
			}
		});
        file.add(save);


        JMenuItem Save_as = new JMenuItem(Which_Language.get(8));
        Save_as.setMnemonic(KeyEvent.VK_E);
        Save_as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsFile();
			}
		});
        file.add(Save_as);
        
        JMenuItem About = new JMenuItem(Which_Language.get(9));
        About.setMnemonic(KeyEvent.VK_E);
        About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About();
			}
		});
        file.add(About);
        
        menubar.add(file);

        setJMenuBar(menubar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainContact ex = new MainContact();
            ex.setVisible(true);
        });
    }
    
   
        	
    public void actionPerformed(ActionEvent event) {
    	String click = event.getActionCommand();
    	if(click.equals("Add"))
    		add_contact();
    	else if (click.equals("edit"))
    		edit_contact();
    	else if (click.equals("update"))
    		update_contact();
    	else if (click.equals("save contact"))
    		saveContact();
    	else if (click.equals("Search"))
    		search_contact();
    	else if (click.equals("delete"))
    		delete_contact();    	
    	else if(click.equals("Añadir"))
    		add_contact();
    	else if (click.equals("editar"))
    		edit_contact();
    	else if (click.equals("actualizar"))
    		update_contact();
    	else if (click.equals("guardar contacto"))
    		saveContact();
    	else if (click.equals("Buscar"))
    		search_contact();
    	else if (click.equals("borrar"))
    		delete_contact();
    	else if(event.getSource()== red)
    		getContentPane().setBackground(Color.red);
    	else if(event.getSource()== yellow)
    		getContentPane().setBackground(Color.yellow);
    	else if(event.getSource()== blue)
    		getContentPane().setBackground(Color.blue);
    	else if(event.getSource()== green)
    		getContentPane().setBackground(Color.green);
    	else if(event.getSource()== magenta)
    		getContentPane().setBackground(Color.magenta);
    	else if(click.equals("rojo"))
    		getContentPane().setBackground(Color.red);
    	else if(click.equals("amarillo"))
    		getContentPane().setBackground(Color.yellow);
    	else if(click.equals("azul"))
    		getContentPane().setBackground(Color.blue);
    	else if(click.equals("verde"))
    		getContentPane().setBackground(Color.green);
    	else if(click.equals("sorted"))
    		sort_contact();
    	
    	
    }
    
    
    
    
    
    
    public void saveTableDataToTxt(){
		try{
			
			if (!filename.exists()) {
				filename.createNewFile();
			}
			
			FileWriter fw = new FileWriter(filename.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
			
            for(int row_ = 0; row_ < row ; row_++){
                
                for(int col = 0; col < 4; col++){
                    bw.write(Table.getModel().getValueAt(row_, col)+",");
                }
               //break line at the end of each row
                bw.write("\n");
            }
            //close BufferedWriter
            bw.close();
            //close FileWriter 
            fw.close();
			JOptionPane.showMessageDialog(this, Which_Language.get(10) +filename, Which_Language.get(7), JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, ex.getMessage(), Which_Language.get(11), JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
   
    public void saveAsFile(){
		JFileChooser filesave = new JFileChooser();
		int returnVal = filesave.showSaveDialog(MainContact.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {	
		   try {
		       File file = filesave.getSelectedFile();		
		       File_Writer = new FileWriter(file.getAbsoluteFile());
		       bufferWriter = new BufferedWriter(File_Writer);
		       for (int row = 0; row < Table.getRowCount(); row++) {
		    	   for (int col = 0; col < Table.getColumnCount(); col++) {		
		    		  bufferWriter.write(Table.getModel().getValueAt(row, col)+",");	
		           }	
		    	   bufferWriter.write("\n");
		        }	
		       bufferWriter.close();
		       File_Writer.close();		
		       JOptionPane.showMessageDialog(this, Which_Language.get(12), Which_Language.get(8), JOptionPane.INFORMATION_MESSAGE);		
		   } catch (IOException e1) {
			   JOptionPane.showMessageDialog(this, e1.getMessage(), Which_Language.get(13), JOptionPane.ERROR_MESSAGE);		
		   }		
		}
	}
    
    public void readContactFile(String Which_file_to_read){
		JFileChooser fileload = new JFileChooser();
		if(Which_file_to_read.equals("Open")){
		if (fileload.showOpenDialog(MainContact.this)==JFileChooser.APPROVE_OPTION)
			try{
				String rowdata;
				File file = fileload.getSelectedFile();
				ReaderBuffer = new BufferedReader(new FileReader(file));
				TM = (DefaultTableModel) Table.getModel();
		        while((rowdata = ReaderBuffer.readLine()) != null){
		           TM.addRow(rowdata.split(",")); 
		        }
		        ReaderBuffer.close();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), Which_Language.get(13), JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			if(Which_file_to_read.equals("English"))
			{
				
				// The name of the file to open.
		        String fileName = "English.txt";

		        // This will reference one line at a time
		        String line = null;

		        try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fileName);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);

		            while((line = bufferedReader.readLine()) != null) {
		                //System.out.println(line);
		                Which_Language.add(line);
		                
		            }   

		            // Always close files.
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		            		Which_Language.get(32) + 
		                fileName + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		            		Which_Language.get(33) 
		                + fileName + "'");                  
		            // Or we could just do this: 
		            // ex.printStackTrace();
		        }
				
			}
			else{
				// The name of the file to open.
		        String fileName = "Spanish";

		        // This will reference one line at a time
		        String line = null;

		        try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fileName);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);

		            while((line = bufferedReader.readLine()) != null) {
		                //System.out.println(line);
		                Which_Language.add(line);
		                
		            }   

		            // Always close files.
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		            		Which_Language.get(32) + 
		                fileName + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		            		Which_Language.get(33) 
		                + fileName + "'");                  
		            // Or we could just do this: 
		            // ex.printStackTrace();
		        }
				
				
				
			}
			
		}
		
	}
    // method to save contact into array also show it on the table
    public void saveContact(){
    	if(Phone_number_TF.getText().matches("-?\\d+(\\.\\d+)?")&&Work_number_TF.getText().matches("-?\\d+(\\.\\d+)?"))
		{
			
			data[row][0] =First_name_TF.getText();
			data[row][1] =Last_name_TF.getText();
			data[row][2] =Phone_number_TF.getText();
			data[row][3] =Work_number_TF.getText();
			//Table.setValueAt(data[row][0], row, 0);
			
			//Table.setValueAt(First_name_TF.getText(), row, 0);
			//Table.setValueAt(Last_name_TF.getText(), row, 1);
			//Table.setValueAt(Phone_number_TF.getText(), row, 2);
			//Table.setValueAt(Work_number_TF.getText(), row, 3);
			Table.setModel(new DefaultTableModel(
					data,
					new String[] {
							Which_Language.get(14), Which_Language.get(15), Which_Language.get(16), Which_Language.get(17)
					}
				));
			row++;
			Last_name_TF.setText("");
			First_name_TF.setText("");
			Phone_number_TF.setText("");
			Work_number_TF.setText("");
			logfile(Which_Language.get(1));
			
			frame.dispose();
    	
    }
    	else{
			final JPanel panel = new JPanel();

		    JOptionPane.showMessageDialog(panel, Which_Language.get(19), Which_Language.get(24), JOptionPane.ERROR_MESSAGE);
		   
			Phone_number_TF.setText("");
			Work_number_TF.setText("");
		}
    }
    // that method to update table and update data in array
    public void update_contact(){
    	// to update the date in the text file 
    	Table.setValueAt(First_name_TF.getText(),Table.getSelectedRow(), 0);
		Table.setValueAt(Last_name_TF.getText(), Table.getSelectedRow(), 1);
		Table.setValueAt(Phone_number_TF.getText(), Table.getSelectedRow(), 2);
		Table.setValueAt(Work_number_TF.getText(), Table.getSelectedRow(), 3);
		data[getSelectedRow][0] = First_name_TF.getText();
		data[getSelectedRow][1] = Last_name_TF.getText();
		data[getSelectedRow][2] = Phone_number_TF.getText();
		 data[getSelectedRow][3] = Work_number_TF.getText();
		 System.out.println(Table.getSelectedRow());
		
		 Last_name_TF.setText("");
			First_name_TF.setText("");
			Phone_number_TF.setText("");
			Work_number_TF.setText("");
			System.out.print(data[getSelectedRow][0]);
			System.out.print(data[getSelectedRow][1]);
			System.out.print(data[getSelectedRow][2]);
			System.out.print(data[getSelectedRow][3]);
			logfile(Which_Language.get(20));
		frame.dispose();
    	
    }
    
    
    public void edit_contact(){
    	
    	// to edit the contact after we select the line that we want to change it 
    	// and that we become in a new frame when we click the edit 

		frame = new JFrame();
		if_selected(frame);
		frame.setTitle(Which_Language.get(19));
		frame.setSize(250, 300);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
        First_name = new JLabel(Which_Language.get(14));
        frame.add(First_name);
        First_name_TF= new JTextField(10);
        First_name_TF.setText(Table.getValueAt(Table.getSelectedRow(), 0).toString());
        frame.add(First_name_TF);
		
		Last_name = new JLabel(Which_Language.get(15));
		frame.add(Last_name);
		Last_name_TF= new JTextField(10);
		Last_name_TF.setText(Table.getValueAt(Table.getSelectedRow(), 1).toString());
		frame.add(Last_name_TF);
		
		
		Phone_number = new JLabel(Which_Language.get(16));
		frame.add(Phone_number);
		Phone_number_TF = new JTextField(10);
		
		Phone_number_TF.setText(Table.getValueAt(Table.getSelectedRow(), 2).toString()); 
		frame.add(Phone_number_TF);
		Work_number = new JLabel(Which_Language.get(17));	
		frame.add(Work_number);
		Work_number_TF = new JTextField(10);
		Work_number_TF.setText(Table.getValueAt(Table.getSelectedRow(), 3).toString()); 
		frame.add(Work_number_TF);
		JButton update = new JButton(Which_Language.get(20));
		update.addActionListener(this);
		frame.add(update);
		frame.setVisible(true);
    	
    }
    // Jframe for contact that has labels and textfiles to fill. 
    public void add_contact(){
    	
    	frame = new JFrame();
    	if_selected(frame);
		frame.setTitle(Which_Language.get(21));
		frame.setSize(250, 300);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
        First_name = new JLabel(Which_Language.get(14));
        frame.add(First_name);
        First_name_TF= new JTextField(10);
        
        frame.add(First_name_TF);
		
		Last_name = new JLabel(Which_Language.get(15));
		frame.add(Last_name);
		Last_name_TF= new JTextField(10);
		
		frame.add(Last_name_TF);
		
		
		Phone_number = new JLabel(Which_Language.get(16));
		frame.add(Phone_number);
		Phone_number_TF = new JTextField(10);
		
		frame.add(Phone_number_TF);
		Work_number = new JLabel(Which_Language.get(17));	
		frame.add(Work_number);
		Work_number_TF = new JTextField(10);
		
		frame.add(Work_number_TF);
		JButton save = new JButton(Which_Language.get(22));
		save.addActionListener(this);
		frame.add(save);
		frame.setVisible(true);
		
	
    	
    	
    }
    // this method to delete contact,After we select the line and press the bottom delete 
    public void delete_contact(){
    	

		//shift rows one by one from the end of the table
		for (int i = Table.getSelectedRow() ; i <row ; i++) {
		    Table.setValueAt(Table.getValueAt(i+1, 0), i, 0);
		    Table.setValueAt(Table.getValueAt(i+1, 1), i, 1);
		    Table.setValueAt(Table.getValueAt(i+1, 2), i, 2);
		    Table.setValueAt(Table.getValueAt(i+1, 3), i, 3);
		}
		row--;
		logfile(Which_Language.get(25));
		
    }
    
    // method that show what its written in search textfiled
    public void search_contact(){
    	// method to search for contact in the list 
    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) Table.getModel())); 
	    sorter.setRowFilter(RowFilter.regexFilter(search_TF.getText()));
	    Table.setRowSorter(sorter);
    	
    }
    // search bar that show in Jframe 
    public void Search_bar(){
    
        JPanel SearchBar = new JPanel();
        search_TF= new JTextField(25);
        SearchBar.add(search_TF);
        JButton searchB = new JButton(Which_Language.get(23));
        searchB.addActionListener(this);
        SearchBar.add(searchB);
       add(SearchBar);
    	
    }
    
    public void create_table(){
    	JPanel pnlList = new JPanel();
		add(pnlList);
		pnlList.setLayout(null);
		JScrollPane List = new JScrollPane();
		pnlList.add(List);
		Table = new JTable();
		Table.setModel(new DefaultTableModel(
				new String[][] {
				},
				new String[] {
						Which_Language.get(14), Which_Language.get(15), Which_Language.get(16), Which_Language.get(17)
				}
				
			));
			Table.getColumnModel().getColumn(0).setPreferredWidth(50);
			Table.getColumnModel().getColumn(1).setPreferredWidth(50);
			Table.getColumnModel().getColumn(2).setPreferredWidth(50);
			Table.getColumnModel().getColumn(3).setPreferredWidth(50);
			
			Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					
				}
			});
		List.setViewportView(Table);
		add(List);
    	
    }
    
    public void About(){
    	// when we click about we see information for person who create the program. 
    	frame = new JFrame();
    	if_selected(frame);
		frame.setTitle(Which_Language.get(9));
		frame.setSize(250, 300);
		frame.setLocationRelativeTo(null);
		JLabel myname = new JLabel("Osama Alghamdi",JLabel.CENTER);
		frame.add(myname);
		frame.setVisible(true);
    	
    	
    	
    }
    //log to print all action in Console.
    public void logfile(String action){
    try{
    	LogManager Logmanager = LogManager.getLogManager();
        Logger log;
       

        log = Logger.getLogger("");

        Logmanager.addLogger(log);
        log.setLevel(Level.INFO);
               log.log(Level.INFO,action);
    }
        catch (Exception e) {
            System.out.println("Exception thrown: " + e);
            e.printStackTrace();
          }
    }
    
 
    
    
    public void language_selector(){
    	JFrame selector = new JFrame();
    	selector.setLayout(new FlowLayout());
    	JPanel f = new JPanel();
    	JPanel s = new JPanel();
    	selector.setTitle("Language");
    	selector.setSize(350, 180);
    	selector.setLocationRelativeTo(null);
		JLabel label = new JLabel("Which language you prefer, Que idioma prefieres ",JLabel.CENTER);
		f.add(label);
		JButton English_s = new JButton("English");
		English_s.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Which_language_select = "English";
				  readContactFile(Which_language_select);
				  selector.dispose();
				  initUI();
				  
			  } 
			} );
		s.add(English_s);
        JButton Spanish_s = new JButton("Spanish");
        Spanish_s.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Which_language_select = "Spanish";
				  readContactFile(Which_language_select);
				  selector.dispose();
				  initUI();
			  } 
			} );
        s.add(Spanish_s);
        selector.add(f);
        selector.add(s);
        
		selector.setVisible(true);
    	
    	
    	
    	
    }
    
    public void color(){
    	JLabel choose_color = new JLabel(Which_Language.get(31)); 
    	red=new JRadioButton(Which_Language.get(26));
    	yellow=new JRadioButton(Which_Language.get(27));
    	blue=new JRadioButton(Which_Language.get(28));
    	green=new JRadioButton(Which_Language.get(29));
    	magenta=new JRadioButton(Which_Language.get(30));
    	ButtonGroup group=new ButtonGroup();
    	
    	add(choose_color);
    	group.add(red);
    	group.add(yellow);
    	group.add(blue);
    	group.add(green);
    	group.add(magenta);
    	red.addActionListener(this);
    	yellow.addActionListener(this);
    	blue.addActionListener(this);
    	green.addActionListener(this);
    	magenta.addActionListener(this);
    	add(red);
    	add(yellow);
    	add(blue);
    	add(green);
    	add(magenta);
    	
    	
    }
    
    public void if_selected(JFrame jframe){
    	
    	if(red.isSelected())
    		jframe.getContentPane().setBackground(Color.red);	
    	else if(yellow.isSelected())
    		jframe.getContentPane().setBackground(Color.yellow);
    	else if(blue.isSelected())
    		jframe.getContentPane().setBackground(Color.blue);
    	else if(green.isSelected())
    		jframe.getContentPane().setBackground(Color.green);
    	else if(magenta.isSelected())
    		jframe.getContentPane().setBackground(Color.magenta);
  
    	
    	
    }
    
    public void sort_contact(){
    	TableRowSorter<TableModel> sorter = new TableRowSorter<>(Table.getModel());
        Table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
         
        // SORT BY MULTIPE COLUMNS
         
        int columnIndexForName = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexForName, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        
    	
    }
    
    public void sorted_buttom(){
    	
    	JRadioButton sorted=new JRadioButton(Which_Language.get(34));
    	ButtonGroup group=new ButtonGroup();
    	group.add(sorted);
    	sorted.addActionListener(this);
    	add(sorted);
    	
    	
    	
    }
    
    
    
  
}
    
