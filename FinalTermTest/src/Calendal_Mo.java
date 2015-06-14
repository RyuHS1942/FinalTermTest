import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calendal_Mo<todaydate> extends JFrame{
	Calendar cal = Calendar.getInstance();
	MyActionListener ActionListener = new MyActionListener();
	
	int year,month,date,dayorder;//����� ����
	int firstdayofdate;//ù���� ������ ����
	int lastdayofdate;//������ ��¥
	
	String[] day = {"SUN","MON","TUS","WED","THU","FRI","SAT"};//����
	int Month[] = {31,28,31,30,31,30,31,31,30,31,30,31};//���� ��;
	String Date[] = {"1","2","3","4","5","6","7","8","9","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30",
			"31"};
	
	JPanel CalendarP;
	JPanel UpP;
	JPanel DownP;
	
	JButton LeftMove;
	JButton Leftmove;
	JLabel YM;
	JButton RightMove;
	JButton Rightmove;
	
	JButton dayB;
	JButton dateB;
	
	public Calendal_Mo(){
		this.setTitle("Calendal");
		this.setSize(700, 400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		CalendarP = new JPanel();
		CalendarP.setSize(690,390);
		CalendarP.setLayout(new BorderLayout());
		this.add(CalendarP);
		this.add(CalendarP,BorderLayout.CENTER);
		//
		UpP = new JPanel();
		UpP.setSize(680,50);
		CalendarP.add(UpP,BorderLayout.NORTH);
		UpP.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		
		LeftMove = new JButton("<<");
		LeftMove.addActionListener(ActionListener);
		UpP.add(LeftMove);
		//
		
		Leftmove = new JButton("<");
		Leftmove.addActionListener(ActionListener);
		UpP.add(Leftmove);
		//
		
		setToday();
		YM = new JLabel(year+"  /  "+month);
		YM.setSize(60, 0);
		UpP.add(YM);
		
		Rightmove = new JButton(">");
		Rightmove.addActionListener(ActionListener);
		UpP.add(Rightmove);
		//
		
		RightMove = new JButton(">>");
		RightMove.addActionListener(ActionListener);
		UpP.add(RightMove);
		//
		
		DownP = new JPanel();
		DownP.setSize(685,340);
		CalendarP.add(DownP,BorderLayout.CENTER);
		DownP.setLayout(new GridLayout(7,7));
		
		ShowCalendar();
				
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void ShowCalendar(){//�޷��� ���̷���
		firstdayofdate = FirstdateOfDay(date,dayorder);
		February();//������ ��
		
		for(int i=0;i<day.length;i++){
			dayB = new JButton(day[i]);//��Ȱ��ȭ
			dayB.setEnabled(false);
			DownP.add(dayB);
			if(i==0){
				dayB.setBackground(Color.RED);
			}else if(i==6){
				dayB.setBackground(Color.BLUE);
			}
		}
		
		for(int i=0;i<firstdayofdate;i++){
			dateB = new JButton();
			DownP.add(dateB);
			dateB.setEnabled(false);
		}
		for(int i=firstdayofdate-1;i<Month[month];i++){
			dateB = new JButton();
			DownP.add(dateB);
			dateB.setText(Date[i]);
		}
		for(int i=firstdayofdate+Month[month];i<5*7;i++){
			dateB = new JButton();
			DownP.add(dateB);
			dateB.setEnabled(false);
		}
	}
	
	public void setToday(){
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		date = cal.get(Calendar.DATE);//���� ����
		dayorder = cal.get(Calendar.DAY_OF_WEEK);
	}

	public void February(){//���� ���
		if(year%4==0&&year%100 !=0||year%400==0){
			 Month[1]++;
		}
	}
	
	public int FirstdateOfDay(int date,int dayorder){
		int time = 0;
		while(date<8){//ù° ��
			date = date-7;
		}
		while(date==1){//1�ϰ� date�� ����
			date--;
			time++;
		}
		dayorder -= time;
		return dayorder;//1���� ���� ����
	}
	
	public String dayordertoday(int dayorder){//������ ���ڷ�
		return day[dayorder];
	}
	//�׼�
	public void moveMonth(int mon){
		month += mon;
		if(month>11){
			while(month>11){
				year++;
				month -= 12;
			}
		}else if(month<0){
			while(month<0){
				year--;
				month += 12;
			}
		}
	}
	
	public void removeCalender(){
		for(int i=0;i<5*7;i++){
			dateB.removeAll();
		}
	}
	
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			removeCalender();
			if(e.getSource()==dateB){
				Date_Mo Date = new Date_Mo();
			}else if(e.getSource()==LeftMove){
				moveMonth(-12);
				YM.setText(year+"  /  "+month);
			}else if(e.getSource()==Leftmove){
				moveMonth(-1);
				YM.setText(year+"  /  "+month);
			}else if(e.getSource()==Rightmove){
				moveMonth(+1);
				YM.setText(year+"  /  "+month);
			}else if(e.getSource()==RightMove){
				moveMonth(+12);
				YM.setText(year+"  /  "+month);
			}
			ShowCalendar();//���� ���� �ʱ�ȭ
		}
	}
}

