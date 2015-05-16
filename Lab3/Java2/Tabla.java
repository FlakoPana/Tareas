import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class Tabla extends JFrame {
  String elemento;
	public Tabla(ArrayList<String> InfoServicios) {
		int contador = 0;
                Object[][] datos = {{"PC", "255.255.255.255"}};
                JPopupMenu Pmenu;
                JMenuItem menuItem; 
		String[] columnNames = {"Proceso","Ubicación"};
		DefaultTableModel dtm= new DefaultTableModel(null,columnNames);
		final JTable table = new JTable(dtm);
                Iterator<String> nombreIterator = InfoServicios.iterator();
                while(nombreIterator.hasNext()){
                      contador++;
                      elemento = nombreIterator.next();
                      Object[] newRow={"Proceso"+ Integer.toString(contador) , elemento};
                      dtm.addRow(newRow);
                }
                Pmenu = new JPopupMenu();
                menuItem = new JMenuItem("Finalizar");
                //menuItem.addActionListener(this);
                Pmenu.add(menuItem);
                table.setComponentPopupMenu(Pmenu);
                System.out.print("termina de construir tabla"  );                
                table.setPreferredScrollableViewportSize(new Dimension(250, 100));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
                //nuevo codigo DSC
                
           menuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           //System.out.print("metodo");  
            elegirMatarActionPerformed(evt);
           
           }
       });//DSC



           
  
	}

          public void elegirMatarActionPerformed(java.awt.event.ActionEvent evt) {
            String osName = System.getProperty("os.name");
           String system =  "";
            if(osName.toUpperCase().contains("WIN")){   //Windows
            system+="taskkill /F /IM " + elemento;
            }
            try{
            Process t = Runtime.getRuntime().exec(system);
                        //} catch (InterruptedException e) {
            //System.out.println("Incapaz de matar el proceso");
            if ( t.exitValue()==0){
                System.out.println(" Killed" );
            }else{
            System.out.println( "Cannot kill ");
            }
            //} catch (IOException ex) {
            //System.out.println("Incapaz de matar el proceso");
            //} 
          }catch (IOException e) {
            System.out.println("Incapaz de matar el proceso");
          } 

           }
         
	/*public static void main(String[] args) {
		Tabla frame = new Tabla();
		frame.pack();
		frame.setVisible(true);

	}*/}

//}