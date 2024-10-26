import java.awt.*;
import javax.swing.*;

public class Pro1 extends JFrame {
    private JTextField displayField;
    public Pro1() {
    //프레임 설정
    setTitle("계산기");
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    //텍스트 필드 설정
    displayField = new JTextField();
    displayField.setEditable(false); // 텍스트 팔드를 수정 불가능하게 설정
    add(displayField, BorderLayout.NORTH);

    //버튼 패널 생성
    JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(6, 5));

    }
    public static void main(String[] args) {
        new Pro1();
    }
}
