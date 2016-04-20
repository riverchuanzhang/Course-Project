package users;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Uselect_CourseMenu extends JFrame{
	
	private JButton jButtonCheck ;
	private JButton jButtonSelect;
	private String employeeNo;
	private JButton jBack;
	
	public Uselect_CourseMenu(String FTitle,String employeeNo){
		
		super(FTitle);
		this.employeeNo = employeeNo;
		this.init();
	}
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 200, 250);
		
		jButtonCheck = new JButton("员工选课查询");
		jButtonSelect = new JButton("员工选课");
		jBack = new JButton("返回");
		
		Container container = this.getContentPane();
		container.add(jButtonCheck);
		container.add(jButtonSelect);
		container.add(jBack);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jButtonCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new USelect_CourseInforframe("选课查询",employeeNo);
			}
		});
		
		jButtonSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UselectCourseFrame("用户选课", employeeNo);
				dispose();
			}
		});
		jBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UMenu("员工菜单",employeeNo);
			}
		});
	}
}
