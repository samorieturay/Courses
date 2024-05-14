import java.util.ArrayList;
import java.util.Date;

public class AmazonCustomerOrderHistorySystem {
    enum ShipmentStatus {InProcess, Shipped, Delivered}
    public static void main(String[] args) {

// Format
        Customer customer = new Customer("Sam", "163 Haver Road", "Yorknew City, New York, 58016", "United States", 16622, "samsam@gmail.com");
        Date date = new Date(12, 2, 1994);
        Shipment shipment = new Shipment(date, "bruh", "bruh");
        Payment payment = new Payment(null, null, date, 31, "4864-2001-2015-2666");
        Order order = new Order("4844-5621-5655", date,20.00,10.00,1.00,31.00,customer,shipment, payment);
        Product product = new Product("Spiderman 2", "78945", "Spider-Men, Peter Parker and Miles Morales", "PlayStation",59.99, Condition.New);
        Invoice invoice = new Invoice("1", "1-800-986-4134");

        // Format
        String name = "Sam";
        // Order Placed Method
        order.orderPlaced(order.orderNumber, date, order.getGrandTotal());
        // Shipment Method
        shipment.Shipment(customer.getName(), shipment.getShippedDate(), date.toString(), shipment.getTrackingId());
        // Println: Item Ordered/Price
        System.out.println("Item Ordered/Price");
        // Product Method
        Product.productDetails(product.getName(), product.getProductId(), product.getProductDescription(), product.getSoldBy(), product.getPrice(), product.getCondition());
        // Customer MethodCustomer customer = new Customer();
        Customer.customerInfo(customer.getName(),customer.getStreetAddress(), customer.getCityStateZip(), customer.getCountry(), customer.getCustomerId(),customer.getContact());
        // Payment Method (Shipping Speed, Payment Method - Signature)
        Payment.paymentInfo(shipment.getShipmentSpeed(), PaymentType.CreditCard, payment.getAccountNumber(), payment.getPaymentDate(), payment.getPaymentAmount());
        // Order Calc Method
        order.orderCalc(order.getItemsSubtotal(), order.getGrandTotal());
        // Println: Invoice Info
        // Invoice Method
        invoice.invoiceContact(invoice.getOrder(), invoice.getInvoiceNumber());

    }
}

// All Enums
enum Condition {New, Used, Reconditioned;}
enum PaymentType {CreditCard, BankTransfer, Mail, AmazonRewardsVisa}
enum ShipmentSpeed {OneDay, TwoDay, Mail}
enum ShipmentStatus {InProcess, Shipped, Delivered}

// Product class

class Product {
    private String productId;
    private String name;
    private String productDescription;
    private String soldBy;
    private double price;
    private Condition Condition;

    public Product(String name, String productId,String productDescription, String soldBy, double price, Condition Condition) {
        this.name = name;
        this.productId = productId;
        this.productDescription = productDescription;
        this.soldBy = soldBy;
        this.price = price;
        this.Condition = Condition;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
    public String getProductDescription() {
        return productDescription;
    }

    public String getSoldBy() {
        return soldBy;
    }

    public double getPrice() {
        return price;
    }

    public Condition getCondition() {
        return Condition;
    }

    public static void productDetails (String name, String productId, String productDescription, String soldBy, double price, Condition Condition)
    {
        String details = ("Product ID: "+ productId + "\n" + "Product Decription: " + productDescription + "\n" + "Sold by: " + soldBy + "\n" + "Price: "+ "\n" + "Condition: " + Condition);
    }
}

// Customer class
class Customer {
    private String name;
    private String streetAddress;
    private String cityStateZip;
    private String Country;
    private Integer customerId;
    private String Contact;





    public Customer(String name, String streetAddress, String cityStateZip, String Country, Integer customerId, String Contact) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.cityStateZip = cityStateZip;
        this.Country = Country;
        this.customerId = customerId;
        this.Contact = Contact;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCityStateZip() {
        return cityStateZip;
    }

    public String getCountry() {
        return Country;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getContact() {
        return Contact;
    }

    public static String customerInfo(String name, String streetAddress, String cityStateZip, String Country, Integer customerId, String Contact)
    {
        String customer = "Name: " + name + "\n" + " CustomerID: " + customerId + "\n+" + "Contact: "+ Contact + "\n" +  "Full Address: " + streetAddress + " " + cityStateZip + "\n" + "Country: " + Country + "\n";
        return customer;
    }
}

// OrderDetail class
class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

// Order class
class Order {
    public String orderNumber;
    public Date orderDate;
    private ArrayList<OrderItem> orderDetails = new ArrayList<>();
    private Double itemsSubtotal;
    private Double shippingHandling;
    private Double tax;
    private Double grandTotal;
    private Customer Customer;
    private Shipment Shipment;
    private Payment Payment;
    public Order(String orderNumber, Date orderDate,Double itemsSubtotal, Double shippingHandling, Double tax, Double grandTotal, Customer Customer, Shipment Shipment, Payment Payment) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.itemsSubtotal = itemsSubtotal;
        this.shippingHandling = shippingHandling;
        this.tax = tax;
        this.grandTotal = grandTotal;
        this.Customer = Customer;
        this.Shipment = Shipment;
        this.Payment = Payment;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ArrayList<OrderItem> getOrderDetails() {
        return orderDetails;
    }

    public Double getItemsSubtotal() {
        return itemsSubtotal;
    }

    public Double getShippingHandling() {
        return shippingHandling;
    }

    public Double getTax() {
        return tax;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public Shipment getShipment() {
        return Shipment;
    }

    public Payment getPayment() {
        return Payment;
    }
    public void orderPlaced(String orderNumber,Date orderDate ,Double grandTotal)
    {
        // Order Number
        System.out.println("Order Number: " + orderNumber);
        // Order Placed Date
        System.out.println("Order Date: " + orderDate);
        // Order total
        System.out.println("Grand Total: " + grandTotal);
    }
    public void orderCalc(Double itemsSubtotal, Double grandTotal)
    {
        // Get Item Subtotal
        System.out.println("Item subtotal: " + itemsSubtotal);
        // Calc shipping and Handling
        System.out.println("Shipping and Handling: " + shippingHandling);
        Double beforeTax = itemsSubtotal + shippingHandling;
        // Total before Tax
        System.out.println("Total before Tax: " + beforeTax);
        Double totalBeforeTax = itemsSubtotal + beforeTax;
        // Estimated Tax to be Collected
        Double tax = totalBeforeTax * 0.06;
        System.out.println("Estimated Tax to be Collected: " + tax );
        // Print Grand total
        Double total = tax + totalBeforeTax;
        System.out.println("Grand Total: " +  total);
    }
//    public void addOrderDetail(OrderItem orderDetail) {
//        orderDetails.add(orderDetail);
//    }

    // Calculate the subtotal of the order
//    public double calculateSubtotal() {
//        double subtotal = 0;
//
//        for (OrderItem orderDetail : orderDetails) {
//            subtotal += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
//        }
//        return subtotal;
//    }
}

// Payment class
class Payment {
    private PaymentType paymentType;
    private String accountNumber;
    private String bankOrlssuer;
    private Order order;
    private Date paymentDate;
    private double paymentAmount;


    public Payment(PaymentType paymentType, Order order, Date paymentDate, double paymentAmount, String accountNumber) {
        this.paymentType = paymentType;
        this.order = order;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.accountNumber = accountNumber;

    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankOrlssuer() {
        return bankOrlssuer;
    }

    public Order getOrder() {
        return order;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }
    public static void paymentInfo(ShipmentSpeed shipmentSpeed, PaymentType paymentType, String accountNumber, Date paymentDate, double paymentAmount)
    {
        System.out.println("Payment Speed: " + ShipmentStatus.Delivered + "\n" +  "Payment Type: " + paymentType + "\n" +"Account Number: " +"**** **** **** " + accountNumber.substring(accountNumber.length() -4 ) + "\n" +"Payment Date: " + paymentDate + "\n" + "Payment Amount: " + paymentAmount);
    }

}
// Shipment class
class Shipment {
    private String shipmentId;
    private String Carrier;
    private ShipmentStatus shipmentStatus;
    private String trackingId;
    private Date shippedDate;
    private Order order;
    private java.lang.String deliveryDate;
    private ShipmentSpeed shipmentSpeed;
    // Add shipping-related attributes as needed

    public String getShipmentId() {
        return shipmentId;
    }

    public String getCarrier() {
        return Carrier;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public Order getOrder() {
        return order;
    }

    public java.lang.String getDeliveryDate() {
        return deliveryDate;
    }

    public ShipmentSpeed getShipmentSpeed() {
        return shipmentSpeed;
    }

    public Shipment(Date shippedDate, String deliveryDate, String trackingId) {
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.trackingId = trackingId;
    }
    public static String Shipment(String Name, Date shippedDate, String deliveryDate, String trackingId)
    {

        //System.out.println(shippedDate.format(shippedDate));   // Convert Date to String
        //System.out.println(deliveryDate.format(deliveryDate)); // Convert Date to String
        String shipmentInformation = (Name + "\n" + "Shipped Date : " + shippedDate + "\n" +"Delivery Date : " + deliveryDate.format(deliveryDate) + "\n" + "Tracking ID : " + trackingId);
        return shipmentInformation;

    }



}
class Invoice
{
    public String order;
    public String invoiceNumber;

    public Invoice(String order, String invoiceNumber) {
        this.order = order;
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrder() {
        return order;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public static void invoiceContact(String order, String invoiceNumber)
    {
        System.out.println("Invoice Info" + "\n" + "Order: " + order + "\n" + "Invoice Number: " + invoiceNumber);
    }
}
