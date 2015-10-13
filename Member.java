package com.example;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Member extends Frame implements ActionListener {
	
	public static final int  NONE   = 0;
	public static final int  ADD    = 1;
	public static final int  DELETE = 2;
	public static final int  SEARCH = 3;
	public static final int  TOTAL  = 4;
	
	Object[] title = {"ID","�̸�","�ֹι�ȣ","��ȭ��ȣ","����","�̸���"};
	
	TextArea display;
	TextField id, name, ssn, tel, sex, email;
	Label id_label, name_label, ssn_label, tel_label, sex_label, email_label;
	Button add, delete, search, total, cancel;
	JTable table = null;
	JScrollPane scroll;
	DefaultTableModel dtm;
	
	Connection conn;
	Statement  stat;
	int        cmd;
	
	public Member() {
		super("1310510������ 2015-1 ESQL, ���������");
		setLayout(new BorderLayout());
		setBackground(new Color(250,224,212));
		Panel display_panel = new Panel();
		display_panel.setLayout(new GridLayout(1,1));
		display = new TextArea();
		display.setEditable(false);
		display.setBackground(new Color(255,255,255));
		display.setPreferredSize(new Dimension(700,30));
		//display_panel.add(display);
		
		Panel info = new Panel();
		info.setLayout(new GridLayout(7,1));
		info.setBackground(new Color(250,224,212));
		
		Panel pid = new Panel();		
		Label id_label = new Label();
		id_label.setText("ID");
		id_label.setFont(new Font("����", Font.BOLD,12));
		pid.add(id_label);
		pid.add(id = new TextField(10));
		pid.setPreferredSize(new Dimension(200,60));
		
		Panel pname = new Panel();
		Label name_label = new Label();
		name_label.setText("�̸�");
		name_label.setFont(new Font("����", Font.BOLD,12));
		pname.add(name_label);
		pname.add(name = new TextField(10));
		pname.setPreferredSize(new Dimension(200,60));
		
		Panel pssn = new Panel();
		Label ssn_label = new Label();
		ssn_label.setText("�ֹε�Ϲ�ȣ");
		ssn_label.setFont(new Font("����", Font.BOLD,12));
		pssn.add(ssn_label);
		pssn.add(ssn = new TextField(10));
		
		Panel ptel = new Panel();
		Label tel_label = new Label();
		tel_label.setText("��ȭ��ȣ");
		tel_label.setFont(new Font("����", Font.BOLD,12));
		ptel.add(tel_label);
		ptel.add(tel = new TextField(10));
		
		Panel psex = new Panel();
		Label sex_label = new Label();
		sex_label.setText("����");
		sex_label.setFont(new Font("����", Font.BOLD,12));
		psex.add(sex_label);
		psex.add(sex = new TextField(10));
		
		Panel pemail = new Panel();
		Label email_label = new Label();
		email_label.setText("E-Mail");
		email_label.setFont(new Font("����", Font.BOLD,12));
		pemail.add(email_label);
		pemail.add(email = new TextField(10));
		
		info.add(pid);
		info.add(pname);
		info.add(pssn);
		info.add(ptel);
		info.add(psex);
		info.add(pemail);
		
		dtm = new DefaultTableModel(title, 0);
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.white);
		//scroll.setPreferredSize(new Dimension(500,580));
		display_panel.add(scroll);
		
		Panel bottom = new Panel();
		Panel bottom1 = new Panel();
		Panel bottom2 = new Panel();
		bottom.setLayout(new GridLayout(2,1));
		bottom2.setBackground(new Color(250,224,212));
		bottom1.add(display);
		bottom2.add(add = new Button("���"));
		bottom2.setBackground(new Color(250,224,212));
		bottom2.setPreferredSize(new Dimension(700,30));
		add.addActionListener(this);
		
		bottom2.add(delete = new Button("����"));
		delete.addActionListener(this);
		
		bottom2.add(search = new Button("�˻�"));
		search.addActionListener(this);
		
		bottom2.add(total = new Button("�ϰ����"));
		total.addActionListener(this);
		
		bottom2.add(cancel = new Button("���"));
		cancel.addActionListener(this);
		
		bottom.add(bottom1);
		bottom.add(bottom2);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				destroy();
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		
		add("Center", display_panel);
		add("West", info);
		add("South", bottom);
		cmd = NONE;
		init();
	}
	
	private void init() {
		try {
			 //JDBC ����̹��� DriverManager�� ���
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/accountbook";

			//�ش� Driver�� ���� Connection ��ü ȹ��
			conn = DriverManager.getConnection(url, "root", "mint13");
			//Connection ��ü�� ���� Statement ��ü ȹ��
			stat = conn.createStatement();
			initialize();
			System.out.println("DataBase ���ῡ �����߽��ϴ�.");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public void initialize() {
		id.setEditable(false);
		name.setEditable(false);
		ssn.setEditable(false);
		tel.setEditable(false);
		sex.setEditable(false);
		email.setEditable(false);	
	}
	
	public void clear() {
		id.setText("");
		name.setText("");
		ssn.setText("");
		tel.setText("");
		sex.setText("");
		email.setText("");		
	}
	
	public void cancle()
	{
		id.setText(" ");
		name.setText(" ");
		ssn.setText(" ");
		tel.setText(" ");
		sex.setText(" ");
		email.setText(" ");		
	}
	
	private void destroy() {
		try {
			if(stat != null) {
				stat.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(Exception ex) { }
	}
	
	public void setEditable(int n) {
		switch(n) {
			case ADD:
				id.setEditable(true);
				name.setEditable(true);
				ssn.setEditable(true);
				tel.setEditable(true);
				sex.setEditable(true);
				email.setEditable(true);
				
				break;
			case DELETE:
				id.setEditable(true);
				break;
			case SEARCH:
				id.setEditable(true);
				break;
			case NONE:
			case TOTAL:
				id.setEditable(false);
				name.setEditable(false);
				ssn.setEditable(false);
				tel.setEditable(false);
				sex.setEditable(false);
				email.setEditable(false);
				
		}	
	}
	
	public void setEnable(int n) {
		add.setEnabled(false);
		delete.setEnabled(false);
		search.setEnabled(false);
		total.setEnabled(false);
		switch(n) {
			case ADD:
				add.setEnabled(true);
				setEditable(ADD);
				cmd = ADD;
				break;
			case DELETE:
				delete.setEnabled(true);
				setEditable(DELETE);
				cmd = DELETE;
				break;
			case SEARCH:
				search.setEnabled(true);
				setEditable(SEARCH);
				cmd = SEARCH;
				break;
			case TOTAL:
				total.setEnabled(true);
				cmd = TOTAL;
				break;
			case NONE:
				add.setEnabled(true);
				delete.setEnabled(true);
				search.setEnabled(true);
				total.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		Member screen = new Member();
		screen.setSize(700, 500);
		screen.setVisible(true);
		screen.setResizable(false);

	}

	public void actionPerformed(ActionEvent e) {
		ResultSet rs = null;
		int idnum = 0;
		Component c = (Component) e.getSource();
	try{
		if(c==add)
		{
			if(cmd != ADD)
			{
				setEnable(ADD);
			}
			else
			{
				String vid = id.getText().trim();
				//idnum = Integer.parseInt(vid);
				String vname = name.getText().trim();
				String vssn = ssn.getText().trim();
				String vtel = tel.getText().trim();
				String vsex = sex.getText().trim();
				String vemail = email.getText().trim();
				if(vid == null || vname == null || vssn == null || vtel == null || vsex == null || vemail == null ||
					vid.length() == 0 || vname.length() == 0 || vssn.length() == 0 || vtel.length() == 0 || vsex.length() == 0 || vemail.length() == 0)
					return ;
				idnum = Integer.parseInt(vid);
				
				ResultSet rs2=stat.executeQuery("select UserID from MEMBER where UserID="+idnum);
				if(rs2.next()) { 	
						display.setText("�̹� ��� ���� �����ID �Դϴ�.");
						setEnable(NONE);
						clear();
						cmd = NONE;
						initialize();
				}
				
				String sql = "insert into MEMBER values(?,?,?,?,?,?)";
				//Statement�� �޼ҵ带 �̿��ؼ� SQL���� ����
				PreparedStatement stat=conn.prepareStatement(sql);
				stat.setInt(1, idnum);
				stat.setString(2, vname);
				stat.setString(3, vssn);
				stat.setString(4, vtel);
				stat.setString(5, vsex);
				stat.setString(6, vemail);
				stat.executeUpdate();
				setEnable(NONE);
				clear();
				cmd = NONE;
				initialize();
			}
		}
		else if(c==delete)
		{
			if(cmd != DELETE)
				setEnable(DELETE);
			else {
				String vid = id.getText().trim();
				if(vid == null || vid.length() == 0)
					return ;
				idnum = Integer.parseInt(vid);
				//�����ͺ��̽��� ������ �����ID�� �ִ��� Ȯ��
				ResultSet rs2=stat.executeQuery("select UserID from MEMBER where UserID="+idnum);
				if(!rs2.next()) { 	
						display.setText("�������� �ʴ� �����ID �Դϴ�.");
						
						setEnable(NONE);
						clear();
						cmd = NONE;
						initialize();
						return;
				}
				stat.executeUpdate("delete from MEMBER where UserID='"
					+ idnum + "'");
				rs2=stat.executeQuery("select UserID from MEMBER where UserID="+idnum);
				if(!rs2.next()) { 	
					display.setText("������ �Ϸ�Ǿ����ϴ�.");
				}
				setEnable(NONE);
				clear();
				cmd = NONE;
				initialize();
			}
		}
		else if(c==search)
		{
			if(cmd != SEARCH)
				setEnable(SEARCH);
			else{
				String vid = id.getText().trim();
				if(vid == null || vid.length() == 0)
					return ;
				idnum = Integer.parseInt(vid);
				//�����ͺ��̽��� ������ �����ID�� �ִ��� Ȯ��
				ResultSet rs2=stat.executeQuery("select UserID from MEMBER where UserID="+idnum);
				if(!rs2.next()) { 	
						display.setText("�������� �ʴ� �����ID �Դϴ�.");
						
						setEnable(NONE);
						clear();
						cmd = NONE;
						initialize();
						return;
				}
				
				rs = stat.executeQuery("select * from MEMBER where UserID='"
					+ idnum + "'");
				
				if(rs.next()) {
					dtm.setNumRows(0);
					do {
						String user_id = rs.getString(1);
						String user_name = rs.getString(2);
						String ssn = rs.getString(3);
						String tel_no = rs.getString(4);
						String sex = rs.getString(5);
						String email = rs.getString(6);
					
						Object rowData[] = {user_id, user_name, ssn, tel_no, sex, email};
						dtm.addRow(rowData);					
					} while(rs.next());
				}
				else{
					display.setText("�ڷᰡ �����ϴ�.");
					dtm.setNumRows(0);
				}
				setEnable(NONE);
				clear();
				cmd = NONE;
				initialize();
			}
		}
		else if(c==total)
		{
			//String sql = "select * from MEMBER order by UserID";
			rs = stat.executeQuery("select * from MEMBER order by UserID");
			
			if(rs.next())
			{
				dtm.setNumRows(0);
				do {
					String user_id = rs.getString(1);
					String user_name = rs.getString(2);
					String ssn = rs.getString(3);
					String tel_no = rs.getString(4);
					String sex = rs.getString(5);
					String email = rs.getString(6);

					Object rowData[] = {user_id, user_name, ssn, tel_no, sex, email};
					dtm.addRow(rowData);
				} while(rs.next());
			}
			else{
				display.setText("�ڷᰡ �����ϴ�.");
				dtm.setNumRows(0);
			}
		}
		else if(c==cancel)
		{
			setEnable(NONE);
			cancle();
			initialize();
			cmd = NONE;
		}
	} catch(Exception ex) { }
	return;
	}

}
