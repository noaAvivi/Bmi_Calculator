import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class bmiCalc extends JFrame implements ActionListener{

    JFrame frame;
    JPanel detailsPanel;
    JTextField fName;
    JTextField lName;
    JTextField ag, yIw;
    JLabel bodySize, weight,age;
    JTextField w;
    double W;
    double H;
    double roundBmi;
    double a;
    double idWeight;
    double iWeight;
    JPanel heightPanel;
    JPanel bodyPanel;
    JSlider heightSlider;
    JRadioButton s,m,l;
    JPanel bResultsPanel;
    JPanel wResultsPanel;
    JTextField tBmi;
    JTextField twStatus;
    JButton calc;
    double num1,num2,rBmi,slimness;

    Font font1 = new Font(".", Font.BOLD, 18);
    Font font2 = new Font(".", Font.BOLD, 16);
    Font font3 = new Font(".", Font.BOLD, 14);

    private final String[] genderBoxText = {"male", "female"};
    JComboBox chooseGender = null;

    public bmiCalc() {

        frame = new JFrame("BMI calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(3, 4, 10, 10));
        detailsPanel.setBounds(10, 10, 460, 100);

        JLabel firstName = new JLabel("First name:");
        JTextField fName = new JTextField();
        firstName.setFont(font2);
        JLabel lastName = new JLabel("Last name:");
        JTextField lName = new JTextField();
        lastName.setFont(font2);
        age = new JLabel("Age:");
        ag = new JTextField();
        age.setFont(font2);
        JLabel gender = new JLabel("gender:");
        JComboBox chooseGender = new JComboBox(genderBoxText);
        gender.setFont(font2);
        weight = new JLabel("weight:");
        w = new JTextField();
        weight.setFont(font2);

        detailsPanel.add(firstName);
        detailsPanel.add(fName);
        detailsPanel.add(lastName);
        detailsPanel.add(lName);
        detailsPanel.add(age);
        detailsPanel.add(ag);
        detailsPanel.add(gender);
        detailsPanel.add(chooseGender);
        detailsPanel.add(weight);
        detailsPanel.add(w);

        JSeparator line1 = new JSeparator();
        line1.setOrientation(SwingConstants.HORIZONTAL);
        line1.setBounds(0, 130, 480, 100);
        frame.add(line1);

        heightPanel = new JPanel();
        heightPanel.setLayout(new GridLayout(3, 1, 1, 1));
        heightPanel.setBounds(10, 140, 420, 120);

        JLabel height = new JLabel("your height is:");
        height.setFont(font1);
        heightSlider = new JSlider(JSlider.HORIZONTAL, 140, 190, 165);
        heightSlider.setPaintTrack(true);
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);
        heightSlider.setMinorTickSpacing(1);
        heightSlider.setMajorTickSpacing(5);
        JLabel height1 = new JLabel("165");
        height1.setFont(font1);
        heightSlider.setFont(font3);

        heightSlider.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //System.out.println("Your height is:" + heightSlider.getValue());
                height1.setText(String.valueOf(heightSlider.getValue()));
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        heightPanel.add(height);
        heightPanel.add(height1);
        heightPanel.add(heightSlider);

        JSeparator line2 = new JSeparator();
        line2.setOrientation(SwingConstants.HORIZONTAL);
        line2.setBounds(0, 280, 480, 100);
        frame.add(line2);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1, 3, 1, 1));
        bodyPanel.setBounds(10, 330, 420, 30);
        bodySize = new JLabel("your body structure is:");
        bodySize.setFont(font1);
        bodySize.setBounds(10, 290, 200, 40);
        frame.add(bodySize);

        ButtonGroup bg = new ButtonGroup();
        s = new JRadioButton("Small");
        s.setFont(font2);
        m = new JRadioButton("Medium");
        m.setFont(font2);
        l = new JRadioButton("Large");
        l.setFont(font2);
        bg.add(s);
        bg.add(m);
        bg.add(l);

        bodyPanel.add(s);
        bodyPanel.add(m);
        bodyPanel.add(l);

        JSeparator line = new JSeparator();
        line.setOrientation(SwingConstants.HORIZONTAL);
        line.setBounds(0, 380, 480, 100);

        frame.add(line);

        bResultsPanel = new JPanel();
        bResultsPanel.setLayout(new GridLayout(1, 5, 10, 10));
        bResultsPanel.setBounds(10, 400, 400, 40);

        JLabel bmi = new JLabel("BMI:");
        bmi.setFont(font3);
        tBmi = new JTextField();
        tBmi.setEditable(false);
        tBmi.setFont(font2);
        JLabel wStatus = new JLabel("body status:");
        wStatus.setFont(font3);
        twStatus = new JTextField();
        twStatus.setFont(font3);
        twStatus.setEditable(false);

        bResultsPanel.add(bmi);
        bResultsPanel.add(tBmi);
        bResultsPanel.add(wStatus);
        bResultsPanel.add(twStatus);

        wResultsPanel = new JPanel();
        wResultsPanel.setLayout(new GridLayout(1, 1, 10, 10));
        wResultsPanel.setBounds(10, 480, 200, 40);

        JLabel Iw = new JLabel("Ideal weight:");
        Iw.setFont(font3);
        yIw = new JTextField();
        yIw.setFont(font1);
        yIw.setEditable(false);

        calc = new JButton("CHECK ME");
        calc.setFont(font3);
        calc.setBounds(260, 480, 150, 50);
        calc.setBackground(Color.green);
        calc.addActionListener(this);
        frame.add(calc);

        wResultsPanel.add(Iw);
        wResultsPanel.add(yIw);

        frame.add(detailsPanel);
        frame.add(heightPanel);
        frame.add(bodyPanel);
        frame.add(bResultsPanel);
        frame.add(wResultsPanel);
        frame.setVisible(true);
    }

    public void calcu(){

        H = heightSlider.getValue();
        W = Double.parseDouble(w.getText());

        try {
            num1 = Double.parseDouble(String.valueOf(H));
            num2 = W;
            calculateBMI(num1, num2);

        } finally {

            if (roundBmi < 15) {

                twStatus.setText("Anorexic!!");

            } else if (roundBmi >= 15 & roundBmi < 18.5) {

                twStatus.setText("Underweight");

            } else if (roundBmi >= 18.5 & roundBmi < 25) {

                twStatus.setText("Normal:)");

            } else if (roundBmi >= 25 & roundBmi < 30) {

                twStatus.setText("Overweight!");

            }else if (roundBmi >= 30 & roundBmi <= 35) {

                twStatus.setText("Obese!");

            }else if (roundBmi > 35) {

                twStatus.setText("Extra Obese");
            }
        }
    }
    private void calculateBMI(double num1, double num2) {

        rBmi =((num2)/((num1/100) * (num1/100)));
        roundBmi = Math.round(rBmi*100)/100;
    }
    public void calcSlim(){

        if(s.isSelected()){

            slimness = 0.9;
            bodySize.setForeground(Color.BLACK);
        }
        else if(m.isSelected()){

            slimness = 1;
            bodySize.setForeground(Color.BLACK);
        }
        else if(l.isSelected()){

            slimness = 1.1;
            bodySize.setForeground(Color.BLACK);
        }
        else{
            bodySize.setForeground(Color.RED);
        }

        a = Double.parseDouble((ag.getText()));
        idWeight = (H - 100 + (a / 10)) * 0.9 * slimness;
        iWeight = Math.round(idWeight*100)/100;
        yIw.setText(String.valueOf(iWeight));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        calcu();
        tBmi.setText(String.valueOf(roundBmi));
        calcSlim();
    }
    public static void main (String[]args){

        bmiCalc bmiCal = new bmiCalc();
    }
}
