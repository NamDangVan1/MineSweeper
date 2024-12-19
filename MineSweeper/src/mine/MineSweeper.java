package mine;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MineSweeper extends JFrame {

    public MineSweeper() {
        Board board = new Board();
        HeaderPanel headerPanel = new HeaderPanel(board, this);
        BoardPanel boardPanel = new BoardPanel(board, this, headerPanel);
        setTitle("Mine Sweeper");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper();
    }

}
