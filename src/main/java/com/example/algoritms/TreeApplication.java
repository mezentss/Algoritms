package com.example.algoritms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TreeApplication extends Application {

    private BinarySearchTree tree = new BinarySearchTree();
    private Pane treePane = new Pane();

    @Override
    public void start(Stage primaryStage) {
        Button insertButton = new Button("Insert");
        Button searchButton = new Button("Search");
        Button deleteButton = new Button("Delete");

        TextField valueField = new TextField();

        insertButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(valueField.getText());
                tree.insert(value);
                drawTree();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        });

        searchButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(valueField.getText());
                TreeNode node = tree.search(value);
                if (node != null) {
                    System.out.println("Value " + value + " found in the tree.");
                } else {
                    System.out.println("Value " + value + " not found in the tree.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(valueField.getText());
                tree.delete(value);
                drawTree();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        });

        HBox inputBox = new HBox(valueField, insertButton, searchButton, deleteButton);
        VBox root = new VBox(treePane, inputBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Binary Search Tree Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree() {
        treePane.getChildren().clear();
        drawNode(tree.getRoot(), 400, 50, 200);
    }

    private void drawNode(TreeNode node, double x, double y, double xOffset) {
        if (node != null) {
            Circle circle = new Circle(x, y, 20, Color.TRANSPARENT);
            circle.setStroke(Color.BLACK);
            Text text = new Text(x - 5, y + 5, String.valueOf(node.getValue()));
            treePane.getChildren().addAll(circle, text);

            if (node.getLeft() != null) {
                drawNode(node.getLeft(), x - xOffset, y + 50, xOffset / 2);
            }
            if (node.getRight() != null) {
                drawNode(node.getRight(), x + xOffset, y + 50, xOffset / 2);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

class BinarySearchTree {
    private TreeNode root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), value));
        }

        return current;
    }

    public TreeNode search(int value) {
        return searchRecursive(root, value);
    }

    private TreeNode searchRecursive(TreeNode current, int value) {
        if (current == null || current.getValue() == value) {
            return current;
        }

        if (value < current.getValue()) {
            return searchRecursive(current.getLeft(), value);
        }

        return searchRecursive(current.getRight(), value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.getValue()) {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                int smallestValue = findSmallestValue(current.getRight());
                current.value = smallestValue;
                current.right = deleteRecursive(current.getRight(), smallestValue);
            }
        } else if (value < current.getValue()) {
            current.left = deleteRecursive(current.getLeft(), value);
        } else {
            current.right = deleteRecursive(current.getRight(), value);
        }
        return current;
    }

    private int findSmallestValue(TreeNode root) {
        return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
    }

    public TreeNode getRoot() {
        return root;
    }
}

