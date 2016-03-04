package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilities.Major;
import utilities.Semester;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
/*
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
*/
import javax.swing.JTextPane;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class MainPageSwing extends JFrame {

	private JPanel contentPane;
	private ArrayList<Semester> schedule = new ArrayList<Semester>();
	private ArrayList<String> electives = new ArrayList<String>();
	private static MainPageSwing frame;
	private String[] options = {"12","13","14","15","16","17","18"};
	
	//String Array for testing
	private String[] majors = {"Computer Engineering", "Software Engineering", "Computer Science", "Music"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Parse.initialize("ueGmIfXGhjfg5T31XCDEc0fkT5uEVZVBfIDGLxBp", 
				"XXZyCO5GmBUAAm9mirRaxfW91WqmxWf3cn4HN3iu");
		runCourseMapper();
	}
	
	public static void runCourseMapper()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainPageSwing();
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPageSwing() {
		//for Testing, should be replaced with one from database
		for (int i = 0; i < 10; i++) {
			electives.add("Elective " + i +"\n"); 
		}
		
		String chosen = (String) JOptionPane.showInputDialog(null, "Major:", "Pick a Major", JOptionPane.PLAIN_MESSAGE, null, majors, "Computer Engineering"); //String Array "majors should be replaced with one from the database
		String minCredits = (String) JOptionPane.showInputDialog(null, "Minimum credits per semester:", "Credits per semester", JOptionPane.PLAIN_MESSAGE, null, options, "Computer Engineering");
		String maxCredits = (String) JOptionPane.showInputDialog(null, "Maximum credits per semester:", "Credits per semester", JOptionPane.PLAIN_MESSAGE, null, options, "Computer Engineering");
		
		
		ParseQuery<ParseObject> majorParse = new ParseQuery<ParseObject>("Majors");
		majorParse.whereContains("name", chosen);
		
		Major major = null;
		try {
			major = new Major(majorParse.find().get(0));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		schedule = major.makeSchedule(Integer.parseInt(minCredits), Integer.parseInt(maxCredits));
		
		schedulePart(schedule);
		
		//System.out.println(chosen);
		//System.out.println(credits);
	}
	
	public void schedulePart(ArrayList schedule)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		for(int i = 0; i < schedule.size();i++) //schedule size is the number of semesters
		{
			SemesterMaker newSemester = new SemesterMaker((Semester) schedule.get(i), i);
			
			contentPane.add(newSemester.getPanel(), newSemester.getGridCon());
		}
		
		//lastPanel();	
	}
	
	/*
	 * LastPanel contains the two buttons for launching the app again and getting electives
	 */
	public JPanel lastPanel()
	{
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.VERTICAL;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 12;
		contentPane.add(panel_12, gbc_panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		JButton btnNewMajor = new JButton("New Major");
		btnNewMajor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				runCourseMapper();
				//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
			}
		});
		
		GridBagConstraints gbc_btnNewMajor = new GridBagConstraints();
		gbc_btnNewMajor.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewMajor.gridx = 11;
		gbc_btnNewMajor.gridy = 0;
		panel_12.add(btnNewMajor, gbc_btnNewMajor);
		
		JButton btnElectives = new JButton("Electives");
		GridBagConstraints gbc_btnElectives = new GridBagConstraints();
		gbc_btnElectives.gridx = 13;
		gbc_btnElectives.gridy = 0;
		
		btnElectives.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//popUp electives
				JOptionPane.showMessageDialog(contentPane, electives, "Top 10", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		panel_12.add(btnElectives, gbc_btnElectives);
		
		return panel_12;
	}
}
