import exceptions.InvalidClientDataException;
import exceptions.UniqueClientException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Client {
    private static ArrayList<Client> clients = new ArrayList<>();
    private static HashSet<String> emails = new HashSet<>();

    private HashSet<Chat> memberOf = new HashSet<>();
    private HashSet<Chat> moderatorOf = new HashSet<>();

    private ArrayList<Transaction> transactions = new ArrayList<>();

    private String name;
    private String surname;
    private String town;
    private String email;

    public Client(String name, String surname, String town, String email) throws InvalidClientDataException, UniqueClientException {
        setName(name);
        setSurname(surname);
        setTown(town);
        setEmail(email);
        addClient(this);
    }

    //TOREMOVE
    public void test() {
        showChats();
        showModeratedChats();
        System.out.println();
    }

    public Transaction addTransaction(Seller seller, String description, LocalDate date, float price) {
        return new Transaction(seller, this, description, date, price);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.getBuyer().equals(this))
            transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transactions.remove(transaction);
            Transaction.removeTransaction(transaction);
        }
    }

    public void showTransactions() {
        for (Transaction transaction : transactions)
            System.out.println(transaction);
    }

    private static void addClient(Client client) throws UniqueClientException {
        if (!clients.contains(client) && !emails.contains(client.getEmail())) {
            clients.add(client);
            emails.add(client.getEmail());
        } else
            throw new UniqueClientException("Client's email must be unique!");
    }

    public static void removeClient(Client client) {
        clients.remove(client);
        emails.remove(client.getEmail());
        for (Chat member : client.memberOf) {
            member.removeMember(client);
        }
    }

    public static void showClients() {
        for (Client c : clients)
            System.out.println(c.toString());
    }

    public boolean isMember(Chat chat) {
        return memberOf.contains(chat);
    }

    public boolean isModerator(Chat chat) {
        return moderatorOf.contains(chat);
    }

    public void joinChat(Chat chat) {
        if (!isMember(chat)) {
            memberOf.add(chat);
            chat.addMember(this);
        }
    }

    public void moderateChat(Chat chat) {
        if (!isMember(chat))
            joinChat(chat);
        if (!isModerator(chat)) {
            moderatorOf.add(chat);
            chat.addModerator(this);
        }
    }

    public void leaveChat(Chat chat) {
        if (isMember(chat)) {
            memberOf.remove(chat);
            chat.removeMember(this);
        }
        if (isModerator(chat))
            moderatorOf.remove(chat);
    }

    public void stopModerating(Chat chat) {
        if (isModerator(chat))
            moderatorOf.remove(chat);
    }

    public void showModeratedChats() {
        System.out.println("CLIENT " + getName() + " MODERATED CHATS");
        for (Chat chat : moderatorOf)
            System.out.println(chat.getName());
    }

    public void showChats() {
        System.out.println("CLIENT " + getName() + " JOINED CHATS");
        for (Chat chat : memberOf)
            System.out.println(chat.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidClientDataException {
        if (name == null)
            throw new InvalidClientDataException();
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidClientDataException {
        if (surname == null)
            throw new InvalidClientDataException();
        this.surname = surname;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) throws InvalidClientDataException {
        if (town == null)
            throw new InvalidClientDataException();
        this.town = town;
    }

    private boolean validateEmail(String email) {
        if (email == null)
            return false;
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).find();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidClientDataException {
        if (validateEmail(email))
            this.email = email;
        else
            throw new InvalidClientDataException();
    }
}
