package com.example.algoritms;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class StackCalculator extends Application {
    private Stack<Double> stack;

    public StackCalculator() {
        stack = new Stack<>();
    }

    @Override
    public void start(Stage primaryStage) {
        Label inputLabel = new Label("Введите выражение:");
        TextField inputField = new TextField();
        Button calculateButton = new Button("Вычислить");
        Label outputLabel = new Label();

        calculateButton.setOnAction(e -> {
            try {
                String expression = inputField.getText();
                double result = calculateExpression(expression);
                outputLabel.setText("Результат: " + result);
            } catch (Exception ex) {
                outputLabel.setText("Ошибка: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(inputLabel, inputField, calculateButton, outputLabel);

        Scene scene = new Scene(root, 350, 150);
        primaryStage.setTitle("Stack Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public double calculateExpression(String expression) throws Exception {
        char[] tokens = expression.toCharArray();
        Stack<Character> operators = new Stack<>();
        Stack<Double> numbers = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

            if (Character.isDigit(token) || token == '.') {
                StringBuilder number = new StringBuilder();
                number.append(token);
                while (i + 1 < tokens.length && (Character.isDigit(tokens[i + 1]) || tokens[i + 1] == '.')) {
                    number.append(tokens[++i]);
                }
                numbers.push(Double.parseDouble(number.toString()));
            }else if (Character.isLetter(token)) {
                throw new Exception("Проверьте корректность введённых данных!");
            }
            else if (token == '+' || token == '-' && (i == 0 || tokens[i-1] == '(')) {
                numbers.push(0.0);
                operators.push(token);
            } else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '^' || token == '√') {
                while (!operators.empty() && precedence(operators.peek()) >= precedence(token)) {
                    applyOp(operators.pop(), numbers);
                }
                operators.push(token);
            } else if (token == '(') {
                operators.push(token);
            } else if (token == ')') {
                while (!operators.empty() && operators.peek() != '(') {
                    applyOp(operators.pop(), numbers);
                }
                operators.pop();
            }
        }

        while (!operators.empty()) {
            applyOp(operators.pop(), numbers);
        }

        return numbers.pop();
    }

    private void applyOp(char op, Stack<Double> numbers) throws Exception {
        if (op == '+') {
            double b = numbers.pop();
            numbers.pop();
            double a = numbers.pop();
            numbers.push(a + b);
        } else if (op == '-') {
            double b = numbers.pop();
            double a = numbers.pop();
            numbers.push(a - b);
        } else if (op == '*') {
            numbers.push(numbers.pop() * numbers.pop());
        } else if (op == '/') {
            double b = numbers.pop();
            double a = numbers.pop();
            numbers.push(a / b);
        } else if (op == '^') {
            double exp = numbers.pop();
            double base = numbers.pop();
            numbers.push(Math.pow(base, exp));
        } else if (op == '√') {
            numbers.push(Math.sqrt(numbers.pop()));
        }
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        if (op == '^' || op == '√') {
            return 3;
        }
        return 0;
    }
}
