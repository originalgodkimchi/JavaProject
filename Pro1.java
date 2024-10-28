import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Pro1 extends JFrame {
    private JTextField displayField;
    public Pro1() {
    //프레임 설정
    setTitle("계산기");
    setSize(400, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    //텍스트 필드 설정
    displayField = new JTextField();
    displayField.setPreferredSize(new Dimension(100, 100));
    displayField.setFont(new Font("Arial", Font.BOLD, 28));
    displayField.setBackground(Color.LIGHT_GRAY);

    displayField.setEditable(false); // 텍스트 팔드를 수정 불가능하게 설정
    add(displayField, BorderLayout.NORTH);

    //버튼 패널 생성
    JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

    // 버튼 배열
    String[] buttonLabels = {
            "÷", "CE", "C", "←",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "=",
    };
        // 버튼을 추가 및 배치
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(Color.WHITE);
            if (label.equals("÷") || label.equals("CE") || label.equals("C") ||
            label.equals("←") || label.equals("x") || label.equals("-") ||
            label.equals("+") || label.equals("+/-") || label.equals(".") ||
            label.equals("=")) {button.setForeground(Color.RED);
            }

            else {
            button.setForeground(Color.BLACK);
            }
            button.addActionListener(new ButtonActionListener());  // 액션 리스너 추가
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);  // 버튼 패널을 중앙에 배치

    }

    // 버튼 클릭 이벤트 처리
    private class ButtonActionListener implements ActionListener {
        private String Pvp1 = ""; ///인용 https://velog.io/@lionjojo/JAVA-%EA%B3%84%EC%82%B0%EA%B8%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-Lv.2
        private String Pvp2 = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();  // 클릭된 버튼
            String command = clickedButton.getText();  // 버튼의 텍스트 가져오기

            // 버튼에 따른 동작 정의
            switch (command) {
                case "CE":
                    displayField.setText("");  // 화면 숫자 지우기
                    Pvp2 = "";  // 이전 입력 초기화
                    break;
                case "C":
                    displayField.setText("");  // 화면 숫자 지우기
                    Pvp2 = "";  // 이전 입력 초기화
                    Pvp1 = "";  // 연산자 초기화
                    break;
                case "←":
                    String currentText = displayField.getText();
                    if (currentText.length() > 0) {
                        // 마지막 글자 삭제
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;

                default:
                    //나머지 버튼들은 텍스트 필드에 추가
                    displayField.setText(displayField.getText() + command);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // GUI 생성
        Pro1 Lsg = new Pro1();
        Lsg.setVisible(true);
    }
}
