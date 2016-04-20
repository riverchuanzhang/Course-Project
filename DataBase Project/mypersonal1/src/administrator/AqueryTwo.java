package administrator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.SQLManage;

public class AqueryTwo extends JFrame{
	
	private String departmentName;
	private String workType;
	private JTable jTable;
	private JButton jbBack;
	
	public AqueryTwo(String FTitle, String departmentName, String workType){
		
		super(FTitle);
		this.departmentName = departmentName;
		this.workType = workType;
		this.init();
	}
public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("返回");
		jTable = new JTable();
		Object name[] =  {"员工姓名","奖罚项目","奖金"};
		Object query1Set[][] = null;		
		String sql = "select Employee_name, Rework_punish_name, Reword_punish_found " +
				"from Employee, Accept_Rework, Department, Work, Rework " +
				"where Employee.Employee_no = Accept_Rework.Employee_no " +
				"and Accept_Rework.Reword_punish_no = Rework.Reword_punish_no " +
				"and Employee.Work_type_no = Work.Work_type_no " +
				"and Employee.Department_no = Department.Department_no  " +
				"and Department_name = '" + departmentName + "'" + 
				" and Work_type = '" + workType + "'";
		int j = name.length;
		SQLManage myQueryManage = new SQLManage();
		query1Set = myQueryManage.query2(sql, j);
		jTable = new JTable(query1Set, name);
		
		Container container = this.getContentPane();
		container.add(new JScrollPane(jTable));
		container.add(jbBack);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Amenu("管理员菜单");
				dispose();
			}
		});
	}
}
