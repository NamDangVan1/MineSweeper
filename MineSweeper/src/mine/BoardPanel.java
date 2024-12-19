package mine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BoardPanel extends JPanel {

    private JButton[][] Boxs;

    public BoardPanel(Board board, JFrame fr, HeaderPanel pn) {
        setLayout(new GridLayout(Board.rows, Board.colums)); // set layout cho panel
        Border boder = BorderFactory.createLineBorder(Color.BLACK); // tạo biến boder để tạo viền màu đen
        Boxs = new JButton[Board.rows][Board.colums]; // tạo mảng hai chiều  JButton;

        for (int i = 0; i < Boxs.length; i++) {
            for (int j = 0; j < Boxs[i].length; j++) {
                Boxs[i][j] = new JButton();
                final int x = i, y = j;
                Boxs[i][j].setBackground(Color.white); // set nền trắng cho nút
                Boxs[i][j].setBorder(boder); // set border cho nút
                Boxs[i][j].addActionListener(new ActionListener() { // thêm hành động khi nhấn nút
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!board.play(x, y)) { // nếu thua thì sẽ mở tất cả hộp
                            board.showAllBoxs();
                        }
                        for (int i = 0; i < Boxs.length; i++) {
                            for (int j = 0; j < Boxs[i].length; j++) { // đặt giao diện cho các nút
                                if (board.getBox(i, j).isOpen()) {
                                    if (board.getBox(i, j).isHasMine()) {
                                        Boxs[i][j].setText("*");
                                    } else {
                                        Boxs[i][j].setText(String.valueOf(board.getBox(i, j).getNumberMineAround()));
                                    }
                                }
                            }
                        }
                        int total = board.getTotal(); // đặt nếu hết ô trống thì kiểm tra kết quả
                        if (total == 0) {
                            if (!board.isWin()) {
                                int choice = JOptionPane.showConfirmDialog(fr, "You Lose! \n New Game?"); // đưa ra bảng thông báo
                                if (choice == 0) {
                                    restartGame(fr, pn);
                                    
                                }
                            } else {
                                int choice = JOptionPane.showConfirmDialog(fr, "You Win! \n New Game?");
                                if (choice == 0) {
                                    restartGame(fr, pn);
                                }
                            }
                        }
                    }

                });

                add(Boxs[i][j]);
            }
        }
    }
    
    private void restartGame(JFrame fr, HeaderPanel pn) {
        // bỏ hết các thành phần trong frame
        fr.remove(this);
        fr.remove(pn);
        
        // tạo các thành phần mới và thêm vào frame
        Board board = new Board(); // tạo một Board mới
        fr.setLayout(new BorderLayout()); // set layout cho frame
        fr.add(new BoardPanel(board, fr , pn), BorderLayout.CENTER); // đặt BoardPanel ở trung tâm
        fr.add(new HeaderPanel(board, fr), BorderLayout.NORTH); // đặt HeaderPanel ở đầu frame
        
        // vẽ lại frame
        fr.revalidate();
        fr.repaint();
    }
}
