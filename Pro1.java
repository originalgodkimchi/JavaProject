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
            button.setForeground(label.matches("[÷x\\-+CE←=]") ? Color.RED : Color.BLACK);
            button.addActionListener(new ButtonActionListener());
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
                    Pvp2 = "";
                    break;
                case "C":
                    displayField.setText("");  // 화면 숫자 지우기
                    Pvp2 = "";
                    Pvp1 = "";
                    break;
                case "←":
                    String currentText = displayField.getText();
                    if (currentText.length() > 0) {
                        // 마지막 글자 삭제
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
                case "÷":
                case "x":
                case "-":
                case "+":
                    if (!Pvp2.isEmpty()) {
                        compute(Pvp2, displayField.getText(), Pvp1);
                    }
                    Pvp1 = command;
                    Pvp2 = displayField.getText();
                    displayField.setText("");  // 다음 숫자를 입력받기 위해 필드 초기화
                    break;
                case "+/-":
                    double currentValue = Double.parseDouble(displayField.getText());
                    displayField.setText(String.valueOf(-currentValue));  // 숫자 앞에 부호 변경
                    break;
                case ".":
                    if (!displayField.getText().contains(".")) {
                        displayField.setText(displayField.getText() + ".");  // 소수점 추가
                    }
                    break;
                case "=":
                    if (!Pvp2.isEmpty()) {
                        compute(Pvp2, displayField.getText(), Pvp1);
                    }
                    Pvp1 = "";  // 연산자 초기화
                    Pvp2 = "";  // 이전 입력 초기화
                    break;
                default:
                    // 나머지 버튼들은 텍스트 필드에 추가
                    displayField.setText(displayField.getText() + command);  // 숫자 추가
                    break;
            }
        }
    }
    // 계산 메소드 추가
    private void compute(String pvp2, String currentText, String operator) {
        double num1 = Double.parseDouble(pvp2); // 첫 번째 숫자
        double num2 = Double.parseDouble(currentText); // 두 번째 숫자
        double result = 0; // 결과 저장을 위한 변수

        // 연산자에 따른 조건문
        switch (operator) {
            case "÷":
                result = num1 / num2;
                break;
            case "x":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "+":
                result = num1 + num2;
                break;
        }
        // 결과를 텍스트 필드에 표시
        displayField.setText(String.valueOf(result));
    }
    /**
     * @see
     * @param args
     */
    public static void main(String[] args) {
        // GUI 생성
        Pro1 Lsg = new Pro1();
        Lsg.setVisible(true);
    }
}
