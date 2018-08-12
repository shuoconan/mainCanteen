package com.js.jhjs;
import com.js.jhjs.img.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoginFrame implements MouseListener,ListSelectionListener{
    private JFrame frame = null;
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel label ;
    private JLabel jLabel_jc;
    private JLabel jLabel_wm;
    private JLayeredPane layeredPane = new JLayeredPane();
    private ArrayList<String> userlist = new ArrayList<String>();
    private JLabel userJlabel = null;
    private JList<String> userJList = null;
    private Font font = new Font("∫⁄ÃÂ", Font.BOLD, 60);
    private JPasswordField jtxPsd = new JPasswordField();
    private JPanel dialPanel = new JPanel();
    private JLabel num1 = new JLabel("1",JLabel.CENTER);
    private JLabel num2 = new JLabel("2",JLabel.CENTER);
    private JLabel num3 = new JLabel("3",JLabel.CENTER);
    private JLabel num4 = new JLabel("4",JLabel.CENTER);
    private JLabel num5 = new JLabel("5",JLabel.CENTER);
    private JLabel num6 = new JLabel("6",JLabel.CENTER);
    private JLabel num7 = new JLabel("7",JLabel.CENTER);
    private JLabel num8 = new JLabel("8",JLabel.CENTER);
    private JLabel num9 = new JLabel("9",JLabel.CENTER);
    private JLabel numx = new JLabel("*",JLabel.CENTER);
    private JLabel num0 = new JLabel("0",JLabel.CENTER);
    private JLabel numj = new JLabel("DEL",JLabel.CENTER);
    private String psd = "";
    
    
	public LoginFrame() {
		this.initLoginFrame("img/1.jpg");
	}
	public void initLoginFrame(String bgPathname){
		//frame≥ı º…Ë÷√
		this.jtxPsd.setText("");
		this.frame = new JFrame("Ω…–√¿ ≥");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(0,0,1280,1024);
		this.frame.setUndecorated(true);
		//‘ÿ»Î±≥æ∞Õº∆¨
		this.label = new JLabel(new ImageIcon(bgPathname));
		this.label.setBounds(0, 0, 1280, 1024);
		
		this.panel.setSize(1280, 1024);
		this.panel.add(this.label);
		//æÕ≤Õ∞¥≈•
		this.jLabel_jc = new JLabel("æÕ ≤Õ");
		this.jLabel_jc.setFont(font);
		this.jLabel_jc.setBounds(103, 630, 215, 86);
		this.jLabel_jc.addMouseListener(this);
		this.jLabel_jc.setHorizontalAlignment(JLabel.CENTER);
		//»°Õ‚¬Ù∞¥≈•
		this.jLabel_wm = new JLabel("Õ‚ ¬Ù");
		this.jLabel_wm.setFont(font);
		this.jLabel_wm.setBounds(453, 630, 215, 86);
		this.jLabel_wm.addMouseListener(this);
		this.jLabel_wm.setHorizontalAlignment(JLabel.CENTER);
		
		this.panel2.setLayout(null);
		this.panel2.add(jLabel_jc);
		this.panel2.add(this.jLabel_wm);
		this.panel2.setSize(1280, 1024);
		this.panel2.setLocation(0, 0);
		this.panel2.setOpaque(false);
		
		this.userlist.add("πÀ—Ù");
		this.userlist.add("—’◊≥");
		this.userJlabel = new JLabel();
		this.userJlabel.setFont(font);	
		this.userJlabel.setBounds(185, 316, 510, 90);
		this.userJlabel.setHorizontalAlignment(JLabel.CENTER);
		this.userJlabel.addMouseListener(this);
		this.panel2.add(this.userJlabel);
		
		this.jtxPsd.setBounds(185,452,510,90);
		this.jtxPsd.setFont(new Font("Arabic", Font.BOLD, 60));
		this.jtxPsd.addMouseListener(this);
		this.jtxPsd.setEditable(false);
		this.panel2.add(this.jtxPsd);
		
		this.userJList = new JList<String>(new String[]{"πÀ—Ù","—’◊≥"});
		this.userJList.setFont(new Font("∫⁄ÃÂ", Font.BOLD, 40));
		this.userJList.setBounds(768, 300, 400, 480);
		this.userJList.setOpaque(false);
		this.userJList.addListSelectionListener(this);
		this.panel2.add(this.userJList);

		
		this.layeredPane.add(this.panel,1);
		this.layeredPane.add(this.panel2,0);
		frame.add(layeredPane);
		frame.setVisible(true);
	}
	//¥¥Ω®≤¶∫≈≈Ã
	public void createDialPanel(){
		this.panel2.remove(userJList);
		this.dialPanel.setBounds(768, 300, 360, 480);
		
		this.num1.setLocation(0, 0);
		this.num2.setLocation(120, 0);
		this.num3.setLocation(240, 0);
		this.num4.setLocation(0, 120);
		this.num5.setLocation(120, 120);
		this.num6.setLocation(240, 120);
		this.num7.setLocation(0, 240);
		this.num8.setLocation(120, 240);
		this.num9.setLocation(240, 240);
		this.numx.setLocation(0, 360);
		this.num0.setLocation(120, 360);
		this.numj.setLocation(240, 360);
		
		this.setSingleDial(num0);
		this.setSingleDial(num1);
		this.setSingleDial(num2);
		this.setSingleDial(num3);
		this.setSingleDial(num4);
		this.setSingleDial(num5);
		this.setSingleDial(num6);
		this.setSingleDial(num7);
		this.setSingleDial(num8);
		this.setSingleDial(num9);
		this.setSingleDial(numx);
		this.setSingleDial(numj);
		
		this.dialPanel.setOpaque(false);
		this.panel2.add(this.dialPanel);
		this.panel2.repaint();
	}
	private void setSingleDial(JLabel num){
		num.setSize(120, 120);
		num.setFont(this.font);
		num.setForeground(Color.white);
		num.addMouseListener(this);
		this.dialPanel.add(num);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(this.jtxPsd)){
			createDialPanel();
		}
		if(e.getComponent().equals(this.jLabel_jc)){
			new jcFrame();
			this.frame.dispose();
		}
		if(e.getComponent().equals(this.jLabel_wm)){
			new wmFrame();
			this.frame.dispose();
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(num1)){
			this.setPressedButtonStyle(this.num1,e);
		}
		if(e.getComponent().equals(num2)){
			this.setPressedButtonStyle(this.num2,e);
		}
		if(e.getComponent().equals(num3)){
			this.setPressedButtonStyle(this.num3,e);
		}
		if(e.getComponent().equals(num4)){
			this.setPressedButtonStyle(this.num4,e);
		}
		if(e.getComponent().equals(num5)){
			this.setPressedButtonStyle(this.num5,e);
		}
		if(e.getComponent().equals(num6)){
			this.setPressedButtonStyle(this.num6,e);
		}
		if(e.getComponent().equals(num7)){
			this.setPressedButtonStyle(this.num7,e);
		}
		if(e.getComponent().equals(num8)){
			this.setPressedButtonStyle(this.num8,e);
		}
		if(e.getComponent().equals(num9)){
			this.setPressedButtonStyle(this.num9,e);
		}
		if(e.getComponent().equals(num0)){
			this.setPressedButtonStyle(this.num0,e);
		}
		if(e.getComponent().equals(numx)){
			this.setPressedButtonStyle(this.numx,e);
		}
		if(e.getComponent().equals(numj)){
			this.setPressedButtonStyle(this.numj);
			this.psd = this.psd.substring(0, this.psd.length()-1);
			this.jtxPsd.setText(this.psd);
		}
		if(e.getComponent().equals(this.userJlabel)){
			this.panel2.remove(dialPanel);
			this.panel2.add(this.userJList);
			this.panel2.repaint();
		}
		if(e.getComponent().equals(this.jLabel_jc)){
			this.setPressedButtonStyle(this.jLabel_jc);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(num1)){
			this.setReleasedButtonStyle(this.num1);
		}
		if(e.getComponent().equals(num2)){
			this.setReleasedButtonStyle(this.num2);
		}
		if(e.getComponent().equals(num3)){
			this.setReleasedButtonStyle(this.num3);
		}
		if(e.getComponent().equals(num4)){
			this.setReleasedButtonStyle(this.num4);
		}
		if(e.getComponent().equals(num5)){
			this.setReleasedButtonStyle(this.num5);
		}
		if(e.getComponent().equals(num6)){
			this.setReleasedButtonStyle(this.num6);
		}
		if(e.getComponent().equals(num7)){
			this.setReleasedButtonStyle(this.num7);
		}
		if(e.getComponent().equals(num8)){
			this.setReleasedButtonStyle(this.num8);
		}
		if(e.getComponent().equals(num9)){
			this.setReleasedButtonStyle(this.num9);
		}
		if(e.getComponent().equals(num0)){
			this.setReleasedButtonStyle(this.num0);
		}
		if(e.getComponent().equals(numx)){
			this.setReleasedButtonStyle(this.numx);
		}
		if(e.getComponent().equals(numj)){
			this.setReleasedButtonStyle(this.numj);
		}
		if(e.getComponent().equals(this.jLabel_jc)){
			this.jLabel_jc.setForeground(Color.black);
			this.jLabel_jc.setOpaque(false);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setPressedButtonStyle(JLabel num,MouseEvent event){
		num.setForeground(Color.DARK_GRAY);
		num.setOpaque(true);
		num.setBackground(Color.yellow);
		this.psd = this.psd+((JLabel)event.getComponent()).getText();
		this.jtxPsd.setText(this.psd);
	}
	public void setPressedButtonStyle(JLabel num){
		num.setForeground(Color.DARK_GRAY);
		num.setOpaque(true);
		num.setBackground(Color.yellow);
	}
	public void setReleasedButtonStyle(JLabel num) {
		num.setForeground(Color.white);
		num.setOpaque(false);
		num.repaint();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		this.userJlabel.setText(this.userJList.getSelectedValue());
	}

}
