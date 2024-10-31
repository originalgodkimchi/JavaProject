import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Pro1 extends JFrame {
    private JTextField displayField;
    private String operator = ""; // 현재 연산자
    private double firstOperand = 0; // 첫 번째 피연산자
    private boolean isNewInput = true; // 새로운 입력 여부

    public Pro1() {
        // 프레임 설정
        setTitle("계산기");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 텍스트 필드 설정(계산화면)
        displayField = new JTextField();
        displayField.setPreferredSize(new Dimension(100, 100));
        displayField.setFont(new Font("Arial", Font.BOLD, 36)); // 폰트 크기 조정
        displayField.setBackground(Color.LIGHT_GRAY);
        displayField.setEditable(false); // 텍스트 필드를 수정 불가능하게 설정
        displayField.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
        add(displayField, BorderLayout.NORTH);

        // 버튼 패널 생성
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
            button.setFont(new Font("Arial", Font.BOLD, 24)); // 버튼 폰트 크기 조정
            button.setForeground(label.matches("[÷x\\-+C←=]|CE") ? Color.RED : Color.BLACK); //CE는 두글자이기에 | 연산자 사용
            button.addActionListener(new ButtonActionListener());  // 액션 리스너 추가
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);  // 버튼 패널을 중앙에 배치
    }

    // 버튼 클릭 이벤트 처리
    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();  // 클릭된 버튼
            String command = clickedButton.getText();  // 버튼의 텍스트 가져오기

            // 버튼에 따른 동작 정의
            switch (command) {
                case "CE":
                    displayField.setText("");  // 화면 지우기
                    firstOperand = 0;
                    operator = "";
                    break;
                case "C":
                    displayField.setText("");  // 화면 지우기
                    firstOperand = 0;
                    operator = "";
                    break;
                case "←":
                    String currentText = displayField.getText();
                    if (currentText.length() > 0) {
                        // 마지막 글자 삭제
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
                case "+":
                case "-":
                case "x":
                case "÷":
                    handleOperator(command);
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
                    if (!operator.isEmpty()) {
                        double result = compute(firstOperand, Double.parseDouble(displayField.getText()), operator);
                        if (Double.isNaN(result)) {
                            displayField.setText("Error."); // 0으로 나눌 경우 에러
                        } else {
                            displayField.setText(String.valueOf(result));  // 결과 출력
                        }
                        operator = "";  // 연산자 초기화
                    }
                    break;
                default:
                    // 나머지 버튼들은 텍스트 필드에 추가
                    if (isNewInput) {
                        displayField.setText(command);  // 숫자 추가
                        isNewInput = false;
                    } else {
                        displayField.setText(displayField.getText() + command);  // 숫자 추가
                    }
                    break;
            }
        }

        private void handleOperator(String op) {
            if (!operator.isEmpty() && !isNewInput) {
                double currentValue = Double.parseDouble(displayField.getText());
                double result = compute(firstOperand, currentValue, operator);
                if (Double.isNaN(result)) {
                    displayField.setText("Error"); // 0으로 나눌 경우 에러
                } else {
                    firstOperand = result;
                    displayField.setText(String.valueOf(firstOperand));  // 결과 출력
                }
            } else {
                firstOperand = Double.parseDouble(displayField.getText());
            }
            operator = op.equals("x") ? "*" : op.equals("÷") ? "/" : op;
            isNewInput = true;
        }

        private double compute(double num1, double num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "x":
                case "*":
                    return num1 * num2;
                case "÷":
                case "/":
                    if (num2 == 0) {
                        return Double.NaN;
                    }
                    return num1 / num2;
                default:
                    return 0;
            }
        }
    }

    public static void main(String[] args) {
        // GUI 생성
        Pro1 calculator = new Pro1();
        calculator.setVisible(true);
    }
}
