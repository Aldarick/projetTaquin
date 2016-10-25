package view;
import controller.MainController;
import model.GameGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Heeeeeey
 * Created by Hugues on 24/10/2016.
 */
public class MainView extends JFrame implements ActionListener {

    private static String gamePanelName = "GAMEPANEL";
    private static String menuSettingsName = "SETTINGSPANEL";
    private static String mainMenuName = "MAINMENUPANEL";

    private TileButton[][] buttonsGrid;
    private MainController controller;
    private CardLayout mainLayout;
    private JPanel mainPanel;
    private JButton btnPlay;
    private JButton btnSettings;

    public MainView(ArrayList<Integer> grid, int size, MainController controller){
        this.controller = controller;
        buttonsGrid = new TileButton[size][size];


        // Initialize the window
        this.initialize(grid, size);


        // Show the window
        this.setVisible(true);

    }

    private  void initialize(ArrayList<Integer> grid, int size){
        mainLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel = new JPanel();
        mainPanel.setLayout(mainLayout);
        this.setTitle("Jeu du taquin");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        mainPanel.add(initializeMenuLayout(), MainView.mainMenuName);
        mainPanel.add(initializeGameLayout(grid, size), MainView.gamePanelName);


        this.setContentPane(mainPanel);

        this.mainLayout.show(mainPanel, MainView.mainMenuName);





    }

    private JPanel initializeGameLayout(ArrayList<Integer> grid, int size){

        JPanel gamePanel = new JPanel();
        GridLayout gl = new GridLayout(size, size);
        gamePanel.setLayout(gl);


        for(int i=0;i < size*size;i++){
            int x = i/size;
            int y = i%size;
            TileButton btn = new TileButton(((grid.get(i)).toString()), x, y);
            gamePanel.add(btn);
            this.buttonsGrid[i/size][i%size] = btn;
            btn.addActionListener(this);

            if (btn.getLabel().equals(Integer.toString(GameGrid.emptyTileMarker))) btn.setEmpty();
        }
        return gamePanel;
    }

    private JPanel initializeMenuLayout(){

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);

        JLabel menuLabel = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();

        btnPlay.addActionListener(this);
        btnSettings.addActionListener(this);


        menuLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        menuLabel.setText("15 Puzzle");
        menuLabel.setBounds(130, 60, 150, 40);
        menuPanel.add(menuLabel);

        btnSettings.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSettings.setText("Settings");
        btnSettings.setBounds(130, 260, 150, 90);
        menuPanel.add(btnSettings);

        btnPlay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnPlay.setText("Play");
        btnPlay.setBounds(130, 150, 150, 80);
        menuPanel.add(btnPlay);

        return menuPanel;
    }




    /*
    /**
     * Swaps the text of two buttons
     * @param btn1 : the first button
     * @param btn2 : the second button
     */
    /*
    public void swapButtons(Button btn1, Button btn2){
        String txt = btn1.getLabel();
        btn2.setLabel(btn1.getLabel());
        btn1.setLabel(txt);
    }
    */

    /**
     * Swaps the text of two buttons.
     * /!\ The second button must be the empty one /!\
     * @param x1 : the x coordinate of the first button
     * @param y1 : the y coordinate of the first button
     * @param x2 : the x coordinate of the second button
     * @param y2 : the y coordinate of the second button
     */
    public void swapButtons(int x1, int y1, int x2, int y2){

        String txt = this.buttonsGrid[x2][y2].getLabel();
        this.buttonsGrid[x2][y2].setText(this.buttonsGrid[x1][y1].getLabel());
        this.buttonsGrid[x1][y1].setText(txt);

        this.buttonsGrid[x2][y2].setFilled();
        this.buttonsGrid[x1][y1].setEmpty();
    }



    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        if(e.getSource() == this.btnPlay){
            System.out.println("PLAY");
            this.mainLayout.show(mainPanel, MainView.gamePanelName);
        }
        else if(e.getSource() == this.btnSettings){
            System.out.println("SETTINGS");
            this.mainLayout.show(mainPanel, MainView.menuSettingsName);
        }
        else {
            TileButton btn = ((TileButton)(e.getSource()));
            System.out.println("Button : " + btn.getLabel());
            this.controller.requestSwap(btn.getSquareX(), btn.getSquareY());
        }

    }
}




