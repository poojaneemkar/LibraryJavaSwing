package libraryWindowGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

public class AdminLibrary extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JScrollPane scrollPane;
	private JButton btnNewButton_2;
	//	private JLabel displayname;
	private JLabel newmsgtext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminLibrary frame = new AdminLibrary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLibrary() {
		connection = PostgresqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(848, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("View Books");
		btnNewButton.setBounds(241, 49, 117, 29);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from librarybooks";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Add Books");
		btnNewButton_1.setBounds(460, 49, 117, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAddBooks fab = new FrameAddBooks();
				fab.setVisible(true);
				//				AddBooks adb = new AddBooks();
				//				adb.setSize(500, 500);
				//				adb.setVisible(true);
				//				desktopPane.add(adb);
				//				getContentPane().add(desktopPane);
			}
		});
		contentPane.add(btnNewButton_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 138, 757, 187);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LibraryLogin ll = new LibraryLogin();
				ll.setVisibleLogin(true);
			}
		});
		btnNewButton_2.setBounds(6, 6, 117, 29);
		contentPane.add(btnNewButton_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 462, 848, 29);
		contentPane.add(panel);
		JLabel openbooks = new JLabel("");
		Image imgopenbks = new ImageIcon(this.getClass().getResource("/books.png")).getImage();
		openbooks.setIcon(new ImageIcon(imgopenbks));
		openbooks.setBounds(349, 357, 141, 111);
		contentPane.add(openbooks);

	}

	public void welcomemsg(String text) {
		// TODO Auto-generated method stub
		newmsgtext = new JLabel("New label");
		newmsgtext.setBounds(656, 11, 171, 16);
		newmsgtext.setText("Welcome " + text);
		contentPane.add(newmsgtext);
	}
}
