import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class StopWatch implements ActionListener{
	
	//Data declaration field
	JFrame frame = new JFrame(); //create frame for the watch 
	JButton startButton = new JButton("START"); //start button 
	JButton resetButton = new JButton("RESET"); //reset button
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d",seconds);
	String minutes_string = String.format("%02d",minutes);
	String hours_string = String.format("%02d",hours);
	
	//create a timer with 1000 miliseconds == 1 second
	Timer timer = new Timer(1000, new ActionListener() {
		
		//define action performed method 
		public void actionPerformed(ActionEvent e) {
			elapsedTime+=1000; //increase elapsed Time by 1 second
			hours = (elapsedTime/3600000); //amount of hours has passed 
			minutes = (elapsedTime/60000) % 60; //%60 to void display anything >60
			seconds = ( elapsedTime/1000) % 60;
			
			seconds_string = String.format("%02d",seconds);
			minutes_string = String.format("%02d",minutes);
			hours_string = String.format("%02d",hours);
			
			//update time label 
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
			
		}
	});
	
	//constructor 
	StopWatch(){
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(100,100,200,100);
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalTextPosition(JTextField.CENTER);
		
		//start button 
		startButton.setBounds(100,200,100,50);
		startButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		//reset button
		resetButton.setBounds(200,200,100,50);
		resetButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(timeLabel); //add time label 
		frame.add(startButton); //add start button 
		frame.add(resetButton); //add reset button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*once START button is clicked:
		 * if started == false (not currently running) 
		 * 		--> flip started to true
		 * 		--> change start button to stop 
		 * 		--> start the timer 
		 * if started == true ( the timer is currently running)
		 * 		--> flip started to false
		 * 		--> change stop button to START
		 * 		--> stop the timer
		 */
		if(e.getSource() == startButton) {
			if(started ==false) {
				started = true;
				startButton.setText("STOP");
				start();
			}else {
				started = false; 
				startButton.setText("START");
				stop();
			}
			
		}
		/* once RESET button is clicked:
		 * --> started flip to false 
		 * --> startButton change to START
		 * --> call reset method
		 */
		
		if(e.getSource() == resetButton) {
			started = false;
			startButton.setText("START");
			reset();
		}
		
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
	
	void reset() {
		timer.stop(); //stop the timer
		
		//set everything back to 0
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		
		seconds_string = String.format("%02d",seconds);
		minutes_string = String.format("%02d",minutes);
		hours_string = String.format("%02d",hours);
		
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		
		
	}


}
