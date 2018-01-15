
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;

public class DrawAndSolveMaze {

    JFrame theFrame;
    JLabel filePrompt;
    JTextField fileInput;
    JPanel inputFilePanel;
    JScrollPane inputFilePane;
    JLabel startRowPrompt;
    JTextField startRowInput;
    JPanel startRowPanel;
    JLabel startColPrompt;
    JTextField startColInput;
    JPanel startColPanel;
    JLabel finishRowPrompt;
    JTextField finishRowInput;
    JPanel finishRowPanel;
    JLabel finishColPrompt;
    JTextField finishColInput;
    JPanel finishColPanel;
    JPanel inputPanel;
    JScrollPane inputPane;
    JButton getButton;
    JButton drawButton;
    JButton solveButton;
    Maze theMaze;
    MazePanel mazePanel;
    JScrollPane mazePane;
    MazeSolver solver;

    public DrawAndSolveMaze(MazeSolver solver, String solverName) {
        this.solver = solver;
        theFrame = new JFrame(solverName);
        Toolkit tk = Toolkit.getDefaultToolkit();
        theFrame.setSize(tk.getScreenSize());
        int width = (int) (tk.getScreenSize().getWidth());
        int height = (int) (tk.getScreenSize().getHeight());
        theFrame.setSize(width, height);
        theFrame.setDefaultCloseOperation(theFrame.EXIT_ON_CLOSE);
        theFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        filePrompt = new JLabel("Enter the name of the maze file");
        fileInput = new JTextField("10by15maze-data.zip", 15);
        inputFilePanel = new JPanel();
        inputFilePanel.add(filePrompt);
        inputFilePanel.add(fileInput);
        inputFilePane = new JScrollPane(inputFilePanel);
        theFrame.add(inputFilePane);
        getButton = new JButton("Get the Maze");
        getButton.addActionListener(new GetAction());
        theFrame.add(getButton);
        startRowPrompt = new JLabel("Start Row");
        startRowInput = new JTextField("0", 5);
        startRowPanel = new JPanel();
        startRowPanel.add(startRowPrompt);
        startRowPanel.add(startRowInput);
        startColPrompt = new JLabel("Start Column");
        startColInput = new JTextField("0", 5);
        startColPanel = new JPanel();
        startColPanel.add(startColPrompt);
        startColPanel.add(startColInput);
        finishRowPrompt = new JLabel("Finish Row");
        finishRowInput = new JTextField("9", 5);
        finishRowPanel = new JPanel();
        finishRowPanel.add(finishRowPrompt);
        finishRowPanel.add(finishRowInput);
        finishColPrompt = new JLabel("Finish Column");
        finishColInput = new JTextField("0", 5);
        finishColPanel = new JPanel();
        finishColPanel.add(finishColPrompt);
        finishColPanel.add(finishColInput);
        inputPanel = new JPanel();
        inputPanel.add(startRowPanel);
        inputPanel.add(startColPanel);
        inputPanel.add(finishRowPanel);
        inputPanel.add(finishColPanel);
        inputPane = new JScrollPane(inputPanel);
        theFrame.add(inputPane);
        drawButton = new JButton("Draw the Maze");
        drawButton.addActionListener(new DrawAction());
        theFrame.add(drawButton);
        drawButton.setEnabled(false);
        solveButton = new JButton("Solve the Maze");
        solveButton.addActionListener(new SolveAction());
        theFrame.add(solveButton);
        solveButton.setEnabled(false);
        mazePanel = new MazePanel(theFrame.getWidth() - 50, theFrame.getHeight() - 250);
        mazePane = new JScrollPane(mazePanel);
        theFrame.add(mazePane);
        theFrame.setVisible(true);
    }

    private class GetAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String mazeFile = fileInput.getText();
                FileInputStream fin = new FileInputStream(mazeFile);
                BufferedInputStream bin = new BufferedInputStream(fin);
                GZIPInputStream gin = new GZIPInputStream(bin);
                ObjectInputStream oin = new ObjectInputStream(gin);
                theMaze = (Maze) oin.readObject();
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, exp);
                theFrame.repaint();
                return;
            }
            getButton.setEnabled(false);
            drawButton.setEnabled(true);
        } // end actionperformaed
    } // end GetAction

    private class DrawAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            drawButton.setEnabled(false);
            mazePanel.drawMaze(theMaze);
            boolean startOK = drawStart(Color.green);
            if (!startOK) {
                return;
            }
            boolean finishOK = drawFinish(Color.green);
            if (!finishOK) {
                return;
            }
            solveButton.setEnabled(true);
        } // end actionperformed

        boolean drawStart(Color color) {
            int startRow = Integer.parseInt(startRowInput.getText());
            int startCol = Integer.parseInt(startColInput.getText());
            if (startRow < 0 || startRow >= theMaze.getRows()) {
                JOptionPane.showMessageDialog(null, "Start Row out of Range");
                drawButton.setEnabled(true);
                return false;
            } // end if
            if (startCol < 0 || startCol >= theMaze.getColumns()) {
                JOptionPane.showMessageDialog(null, "Start Column out of Range");
                drawButton.setEnabled(true);
                return false;
            } // end if
            mazePanel.drawRect(startRow, startCol, theMaze, color);
            return true;
        } // end drawStart

        boolean drawFinish(Color color) {
            int finishRow = Integer.parseInt(finishRowInput.getText());
            int finishCol = Integer.parseInt(finishColInput.getText());
            if (finishRow < 0 || finishRow >= theMaze.getRows()) {
                JOptionPane.showMessageDialog(null, "Finish Row out of Range");
                drawButton.setEnabled(true);
                return false;
            } // end if
            if (finishCol < 0 || finishCol >= theMaze.getColumns()) {
                JOptionPane.showMessageDialog(null, "Finish Column out of Range");
                drawButton.setEnabled(true);
                return false;
            } // end if
            mazePanel.drawRect(finishRow, finishCol, theMaze, color);
            return true;
        } // end drawFinish
    } // end DrawAction

    private class SolveAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList A = solveMaze(theMaze);
            mazePanel.drawSolution(theMaze, A);
            int startRow = Integer.parseInt(startRowInput.getText());
            int startCol = Integer.parseInt(startColInput.getText());
            int finishRow = Integer.parseInt(finishRowInput.getText());
            int finishCol = Integer.parseInt(finishColInput.getText());
            solveButton.setEnabled(false);
            getButton.setEnabled(true);
            drawButton.setEnabled(true);
        } // end actionPerfromed

        ArrayList solveMaze(Maze maze) {
            int startRow = Integer.parseInt(startRowInput.getText());
            int startCol = Integer.parseInt(startColInput.getText());
            int finishRow = Integer.parseInt(finishRowInput.getText());
            int finishCol = Integer.parseInt(finishColInput.getText());
            return solver.solveMaze(startRow, finishRow, startCol, finishCol, maze);
        } // end solveMaze
    } // end SolveAction

    public static void main(String[] args) {
        new DrawAndSolveMaze(new RecursiveMazeSolution(), "Recursive Solution");
    }
} // end DrawAndSolveMaze
