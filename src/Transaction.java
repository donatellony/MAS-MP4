import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    private Seller seller;
    private Client buyer;
    private String description;
    private LocalDate date;
    private float price;

    public Transaction(Seller seller, Client buyer, String description, LocalDate date, float price) {
        setSeller(seller);
        setBuyer(buyer);
        setDescription(description);
        setDate(date);
        setPrice(price);
        addTransaction(this);
    }

    private static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.getBuyer().addTransaction(transaction);
        transaction.getSeller().addTransaction(transaction);
    }

    public static void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.getBuyer().removeTransaction(transaction);
        transaction.getSeller().removeTransaction(transaction);
    }

    public static void showTransactions() {
        for (Transaction t : transactions)
            System.out.println(t.toString());
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Client getBuyer() {
        return buyer;
    }

    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction between seller " +
                getSeller().getName() +
                " " +
                getSeller().getSurname() +
                " and client " +
                getBuyer().getName() +
                " " +
                getBuyer().getSurname() +
                ", date: " + getDate() +
                ", description: " + getDescription() +
                ", price: " + getPrice();
    }
}
