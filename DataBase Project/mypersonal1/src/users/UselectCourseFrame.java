package users;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.SQLConnect;

public class UselectCourseFrame extends JFrame{
	
	private String employeeNo;
	private JButton jtSelect;
	private JButton jtBack;
	private JComboBox jComboBox;
	private JTable jTable;
	
	public UselectCourseFrame(String FTitle,String employeeNo){
		
		super(FTitle);
		this.employeeNo =employeeNo;
		this.init();
	}
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jtSelect = new JButton("确定");
		jtBack = new JButton("返回");
		jComboBox = new JComboBox();
		jComboBox.addItem("全部");
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		String sql = "select Course_name from Course";
		String sql2 = "select Course_no, Course_name, Course_book, Course_time from Course ";
		System.out.println(sql);
		System.out.println(sql2);
		Object set[][] = null;
		String name[] = {"课程编号","课程名","教材","学时"};
		try {
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = stmt.executeQuery(sql);
			while(resultSet.next()){
				
				jComboBox.addItem(resultSet.getString(1));
			}
			ResultSet resultSet2 = stmt.executeQuery(sql2);
			int count = 0;
			while(resultSet2.next()){
				
				count++;
			} 
			set = new Object[count][4];
			count =0;
			resultSet2.beforeFirst();
			while(resultSet2.next()){
				
				set[count][0]=resultSet2.getString(1);
				set[count][1]=resultSet2.getString(2);
				set[count][2]=resultSet2.getString(3);
				set[count][3]=resultSet2.getString(4);
				count++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		jTable = new JTable(set, name);
		
		Container container = this.getContentPane();
		container.add(new JScrollPane(jTable));
		container.add(jComboBox);
		container.add(jtBack);
		container.add(jtSelect);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jtBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Uselect_CourseMenu("选课菜单", employeeNo);
				dispose();
			}
		});
		
		jComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String course_name = (String) jComboBox.getSelectedItem();
				insert(course_name);
				
			}
		});
		
	}
	private void insert(String course_name){
		
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		String sql = "select Course_no, Course_name, Course_book, Course_time from Course " +
				"where Course_name = '" + course_name + "'";
		Statement stmt = null;
		String courseNo = null;
		String courseName = null;
		String courseBook = null;
		String courseTime = null;
		try {
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			while(resultSet.next()){
				courseNo = resultSet.getString(1);
				courseName = resultSet.getString(2);	
				courseBook = resultSet.getString(3);
				courseTime = resultSet.getString(4);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		PreparedStatement psmt = null;
		try {
			psmt = connection.prepareStatement("insert into Select_Course values(?,?,?,?)");
			psmt.setString(1, " ");
			psmt.setInt(2,3 );
			psmt.setString(3, employeeNo);
			psmt.setString(4, courseNo);	
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(this, "添加成功！", "添加",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				psmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	
}
