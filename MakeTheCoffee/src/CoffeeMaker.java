import javax.management.MBeanNotificationInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.LongUnaryOperator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class CoffeeMaker extends JFrame {
    private JPanel UpMenu = new JPanel();
    private JPanel InformationBar = new JPanel();
    private JPanel LaunchBar = new JPanel();
    private JButton BtnGrind = new JButton("Load");
    private JButton BtnStart = new JButton("Start");
    private JButton BtnStop = new JButton("Stop");
    private JProgressBar Time = new JProgressBar();
    public JTextArea Output = new JTextArea();
    private JLabel Timer = new JLabel();

    public CoffeeMaker() {
        //Init of the windows
        setTitle("Make The Coffee");
        setSize(500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Init content of windows
        setLayout(new BorderLayout());
        this.getContentPane().add(UpMenu, BorderLayout.NORTH);
        this.getContentPane().add(Output, BorderLayout.CENTER);

        UpMenu.setLayout(new GridLayout(2,1));
        UpMenu.add(InformationBar);
        UpMenu.add(LaunchBar);

        InformationBar.setLayout(new GridLayout(1,2));
        InformationBar.add(Timer);
        InformationBar.add(BtnGrind);

        LaunchBar.setLayout(new BorderLayout());
        LaunchBar.add(BtnStart, BorderLayout.WEST);
        LaunchBar.add(BtnStop, BorderLayout.EAST);
        LaunchBar.add(Time, BorderLayout.CENTER);

        //Init content values
        Time.setValue(0);
        Time.setForeground(Color.decode("#5F6FE6"));
        Time.setBackground(Color.decode("#F5F6F3"));
        Time.setBorderPainted(true);
        Time.setStringPainted(true);
        BtnGrind.setBackground(Color.decode("#3AAE9B"));
        BtnGrind.setForeground(Color.WHITE);
        BtnStop.setBackground(Color.decode("#552C98"));
        BtnStop.setForeground(Color.WHITE);
        BtnStart.setBackground(Color.decode("#552C98"));
        BtnStart.setForeground(Color.WHITE);

        //Output.setEditable(false);
        Output.setForeground(Color.decode("#F5F6F3"));
        Output.setBackground(Color.black);
        Output.setFont(new Font("Monospace", 10, 15));
        Output.setEditable(false);
        Output.setAutoscrolls(true);

        //Listener
        BtnGrind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cleanOutput();
                printInOutput("Start to grind Coffee.");
                doGringCoffee();
            }
        });

        setAlwaysOnTop(true);
        setVisible(true);
    }

    public void cleanOutput(){
        Output.setText("");
    }

    public void printInOutput(String text){
        LocalTime myObj = LocalTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss.n");
        String formattedDate = myObj.format(myFormatObj);
        Output.setText(Output.getText() + "[" + formattedDate + "] " + text + "\n");
        Timer.setText(" Last message : " + myObj.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    public void printErreurOutput(String Type, String Text){
        printInOutput("[" + Type + "] " + Text);
    }

    public void doGringCoffee(){
        JSONParser jsonP = new JSONParser();
        ArrayList<Bean> listOfBean = new ArrayList<>();
        try{
            JSONObject obj = (JSONObject)jsonP.parse(new FileReader("C:\\CoffeeConfig\\Config.json"));
            JSONArray Beans = (JSONArray) obj.get("Bean");
            for(Object BeanObject: Beans){
                try {
                    if (BeanObject instanceof JSONObject) {
                        String Parfum = (String) ((JSONObject) BeanObject).get("Parfum");
                        long Start = (long) ((JSONObject) BeanObject).get("Start");
                        boolean JustPressed = (boolean) ((JSONObject) BeanObject).get("JustPressed");
                        try {
                            Bean currentBean;
                            if (JustPressed) {
                                listOfBean.add(new Bean(Parfum, (int) Start));
                            } else {
                                long Stop = (long) ((JSONObject) BeanObject).get("Stop");
                                listOfBean.add(new Bean(Parfum, (int) Start, (int) Stop));
                            }
                            printInOutput("Load success : " + listOfBean.get(listOfBean.size()-1).toString());
                        } catch (UnvalidParfum e) {
                            printErreurOutput("UnvalidParfum", e.toString());
                        }
                    }
                }catch (NullPointerException e){
                    printErreurOutput("Null Pointer", e.toString());
                }
            }
        } catch(FileNotFoundException exc) {
            printErreurOutput("File","Erreur : \"C:/CoffeeConfig/Config.json\" n'existe pas.");
        } catch (IOException e) {
            printErreurOutput("Read","Erreur de lecture du fichier.");
        } catch (Exception Other){
            printErreurOutput("Unknwon Erreur", Other.toString());
        }

        printInOutput(listOfBean.size() + " Bean(s) has been load with success.");

    }

    public static void main(String[] args) {
        new CoffeeMaker();
    }
}


