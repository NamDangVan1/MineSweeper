package mine;

import javax.swing.JButton;

public class Box {
    private boolean isOpen, isHasMine;
    private int numberMineAround;
    
    public Box() {
        isOpen = false;
        isHasMine = false;
    }
    
    public boolean isOpen() {
        return isOpen;
    }
    
    public boolean isHasMine() {
        return isHasMine;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsHasMine(boolean isHasMine) {
        this.isHasMine = isHasMine;
    }

    public void setNumberMineAround(int numberMineAround) {
        this.numberMineAround = numberMineAround;
    }

    public int getNumberMineAround() {
        return numberMineAround;
    }
    
    
}
