package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import utilities.Course;
import utilities.Semester;

/**
 * 
 * @author Kit Kohl
 *
 */
public class SemesterMaker {
		private JPanel panel;
		private GridBagConstraints gridCon;
		private Semester semester;
		private int semesterNum;
		
		public SemesterMaker(Semester courses, int numberOfSemester)
		{
			this.semester = courses;
			semesterNum = numberOfSemester;
			makeSemester();
		}
		
		private void makeSemester()
		{
			panel = new JPanel();
			gridCon = new GridBagConstraints();
			gridCon.insets = new Insets(0, 0, 5, 0);
			gridCon.fill = GridBagConstraints.BOTH;
			gridCon.gridx = 0;
			gridCon.gridy = semesterNum;
			//contentPane.add(panel_1, gbc_panel_1);
			panel.setLayout(new GridLayout(1, 0, 10, 10));
			//panel.setBackground(Color.WHITE);

			for(int i = 0; i < semester.getCourses().size(); i++)
			{
				panel.add(makeCourse(semester.getCourses().get(i)));
				
			}
			panel.add(lastPanel(semester.getCredits()));
		}
		
		private JTextPane makeCourse(Course course)
		{
			JTextPane txtpnTest = new JTextPane();
			txtpnTest.setText(course.getCourseCode());
			txtpnTest.setEditable(false);
			//contentPane.add(txtpnTest);
			if (semesterNum%2 == 0) {
				txtpnTest.setBackground(Color.WHITE);
			}
			else
			{
				txtpnTest.setBackground(Color.YELLOW);
			}
			return txtpnTest;
		}
		
		private JTextPane lastPanel(int credits)
		{
			JTextPane txtpnTest = new JTextPane();
			txtpnTest.setText("Credits " + credits + "\nSemester " + (semesterNum+1));
			txtpnTest.setEditable(false);
			if(semesterNum%2 == 0){
				txtpnTest.setBackground(Color.LIGHT_GRAY);
			}else
			{
				txtpnTest.setBackground(Color.LIGHT_GRAY);
			}
			return txtpnTest;
		}
		
		public JPanel getPanel()
		{
			return panel;
		}
		
		public GridBagConstraints getGridCon()
		{
			return gridCon;
		}
}
