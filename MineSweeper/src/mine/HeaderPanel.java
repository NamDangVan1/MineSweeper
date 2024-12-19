package mine;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HeaderPanel extends JPanel {
    private final Timer timer; // tạo hàm Time
    private int total = 0;

    public HeaderPanel(Board board, JFrame fr) {
        setLayout(new GridLayout()); //set layout
        JLabel label = new JLabel("Số ô trống còn lại: " + total); // tạo label để hiển thị số ô trống
        add(label);
        timer = new Timer(100, e -> { // new Timer để cập nhập mỗi 0,1 giây
            total = board.getTotal(); // xem số ô còn lại
            label.setText("Số ô trống còn lại: " + total); // cập nhập lên label
            if (total == 0) { // kiểm tra nếu không còn ô nào thì dừng thời gian
                stopTime();
            }
            repaint(); // vẽ lại panel mỗi khi cập nhập
        });
        timer.start();
    }

    public void stopTime() { // hàm dừng thời gian
        if (timer != null ) {
            timer.stop();
        }
    }
}
