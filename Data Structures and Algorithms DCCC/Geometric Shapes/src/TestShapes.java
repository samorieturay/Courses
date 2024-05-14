import java.text.DecimalFormat;
import java.util.ArrayList;

interface Shape {
    double getArea();

    double getPerimeter();
}

class Circle implements shape {
    private double radius;

    public Circle(double radius) {
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

class Rectangle implements shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
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

class Triangle implements shape {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
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

public class TestShapes {
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

        for (shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + " area: " + df.format(shape.getArea()));
            System.out.println(shape.getClass().getSimpleName() + " perimeter " + df.format(shape.getPerimeter()));
        }
    }
}
