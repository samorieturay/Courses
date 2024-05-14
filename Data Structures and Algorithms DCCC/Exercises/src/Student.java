public class Student {
    private String name;
    private int age;
    private String address;
    private int zipcode;

    // Default constructor with default zipcode value (19090)
    public Student() {
        zipcode = 19090;
    }

    // Parameterized constructor
    public Student(String name, int age, String address, int zipcode) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.zipcode = zipcode;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Setter method for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for address
    public String getAddress() {
        return address;
    }

    // Setter method for zipcode
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    // Getter method for zipcode
    public int getZipcode() {
        return zipcode;
    }

    // Modified toString method to include zipcode
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Address: " + address + ", Zipcode: " + zipcode;
    }

    public static void main(String[] args) {
        // Create a new student with default values
        Student defaultStudent = new Student();

        // Set some attributes using setter methods
        defaultStudent.setName("Whitehead");
        defaultStudent.setAge(25);
        defaultStudent.setAddress("123 Main Street");

        // Print the student information using the toString method
        System.out.println("Default Student Information:");
        System.out.println(defaultStudent);

        // Create another student with custom values
        Student customStudent = new Student("Don", 42, "456 Elm Avenue", 20000);

        // Print the custom student information using the toString method
        System.out.println("\nCustom Student Information:");
        System.out.println(customStudent);
    }
}