import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

interface shape {
    double getArea();

    double getPerimeter();
}

class circle implements shape {
    private double radius;

    public circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class rectangle implements shape {
    private double length;
    private double width;

    public rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
}

class triangle implements shape {
    private double a;
    private double b;
    private double c;

    public triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }
}

public class GeometricShapes extends JPanel {
    public void drawShapesGraphically(ArrayList<shape> shapes) {
        JFrame f = new JFrame("Shapes Drawing");
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(this);
        f.setSize(800, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graphics2D g2d = (Graphics2D) getGraphics();

        int x = 20;
        for (shape shape : shapes) {
            if (shape instanceof circle) {
                drawCircle(g2d, x, 100, 100, Color.BLUE, shape);
            } else if (shape instanceof rectangle) {
                drawRectangle(g2d, x, 100, 80, 100, Color.RED, shape);
            } else if (shape instanceof triangle) {
                // Implement drawing for the triangle here
            }
            x += 180; // Adjust the x-coordinate for the next shape
        }
    }

    public void drawCircle(Graphics2D g2, int x, int y, int diameter, Color fillColor, shape shape) {
        g2.setColor(fillColor);
        g2.fillOval(x, y, diameter, diameter);
        DecimalFormat df = new DecimalFormat("#,###.00");
        g2.drawString("Area: " + df.format(shape.getArea()), x, y + diameter + 20);
        g2.drawString("Circumference: " + df.format(shape.getPerimeter()), x, y + diameter + 40);
    }

    public void drawRectangle(Graphics2D g2, int x, int y, int width, int height, Color fillColor, shape shape) {
        g2.setColor(fillColor);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.BLACK);
        DecimalFormat df = new DecimalFormat("#,###.00");
        g2.drawString("Area: " + df.format(shape.getArea()), x, y + height + 20);
        g2.drawString("Perimeter: " + df.format(shape.getPerimeter()), x, y + height + 40);
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        circle circle = new circle(5.0);
        rectangle rectangle = new rectangle(4.0, 6.0);
        triangle triangle = new triangle(3.0, 4.0, 5.0);

        ArrayList<shape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(rectangle);
        shapes.add(triangle);

        // Call the drawShapesGraphically method to draw the shapes graphically
        SwingUtilities.invokeLater(() -> {
            GeometricShapes testShape = new GeometricShapes();
            testShape.drawShapesGraphically(shapes);
        });
    }
}
