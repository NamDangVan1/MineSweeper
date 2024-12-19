package mine;

import java.util.Random;

public class Board {

    public static final int rows = 10;
    public static final int colums = 10;
    public static final int mines = rows * colums / 5;
    private int total = rows * colums - mines;
    private Box[][] boxs; 
    private boolean win = true; 
    Random random = new Random();

    public Board() {
        boxs = new Box[rows][colums]; // tạo một mảng hai chiều
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                boxs[i][j] = new Box();
            }
        }

        for (int i = 0; i < mines; i++) {
            int x = random.nextInt(0, rows);
            int y = random.nextInt(0, colums);

            while (boxs[x][y].isHasMine()) { // kiểm tra nếu đã có mìn rồi thì đặt chỗ khác
                x = random.nextInt(0, rows);
                y = random.nextInt(0, colums);
            }

            boxs[x][y].setIsHasMine(true);
        }

        for (int i = 0; i < boxs.length; i++) {
            for (int j = 0; j < boxs[i].length; j++) {
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        if ((!(m + i == i && n + j == j)) && (m + i) < rows && (m + i) >= 0 && (n + j) < colums && (n + j) >= 0 && boxs[m + i][n + j].isHasMine()) {
                            ++count;
                        }
                    }
                }
                boxs[i][j].setNumberMineAround(count);
            }
        }
    }

    public Box[][] getBoxs() {
        return boxs;
    }

    public boolean play(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= colums) { // kiểm tra nếu ở ngoài bảng thì không làm gì cả
            return true;
        }

        if (boxs[x][y].isOpen()) { // kiểm tra nếu đã mở rồi thì không làm gì cả
            return true;
        } else { // chưa mở thì set đã mở và trừ tổng số hộp chưa mở đi 1
            boxs[x][y].setIsOpen(true);
            total -= 1;
        }

        if (boxs[x][y].isHasMine()) { // nếu hộp có mìn thì thua
            win = false;
            return false;
        }

        if (boxs[x][y].getNumberMineAround() == 0) { // kiểm tra nếu số mìn xung quanh bằng không thì mở các ô xung quanh
            for (int m = -1; m <= 1; m++) {
                for (int n = -1; n <= 1; n++) {
                    if ((x + m) >= 0 && (x + m) < rows && (y + n) >= 0 && (y + n) < colums && !boxs[x + m][y + n].isHasMine()) {
                        play(x + m, y + n);
                    }
                }
            }
        }
        return true;
    }

    public boolean isWin() {
        return win;
    }

    public void showAllBoxs() { // hàm mở tất cả hộp
        total = 0;
        for (int i = 0; i < boxs.length; i++) {
            for (int j = 0; j < boxs[i].length; j++) {
                boxs[i][j].setIsOpen(true);
            }
        }
    }

    public Box getBox(int x, int y) {
        return boxs[x][y];
    }

    public int getTotal() {
        return total;
    }

}
