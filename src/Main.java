import exceptions.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try {
            Client client = new Client("Jan", "Kowalski", "Warszawa", "s18917@pjwstk.edu.pl", "janki");
            Client moderator = new Client("Yehor", "Voiko", "Warszawa", "alamakota@gmail.com", "s18917");
            Chat chat = new Chat("PJWSTK");
            client.joinChat(chat);
            moderator.moderateChat(chat);
            client.test();
            moderator.test();
            chat.test();
            System.out.println("Testing leaving moderated chat client-sided");
            moderator.leaveChat(chat);
            chat.test();
            moderator.test();
            System.out.println("Testing adding moderator chat-sided and removing moderator chat-sided");
            chat.addModerator(moderator);
            chat.test();
            moderator.test();
            chat.removeModerator(moderator);
            chat.test();
            moderator.test();
            System.out.println("Adding the moderator back and kicking him off chat-sided");
            chat.addModerator(moderator);
            chat.test();
            moderator.test();
            chat.removeMember(moderator);
            chat.test();
            moderator.test();
            System.out.println("Removing the client from extension");
            Client.removeClient(client);
            chat.test();
            client.test();
            System.out.println("Joining the chat with moderator and removing the chat");
            moderator.moderateChat(chat);
            chat.test();
            moderator.test();
            Chat.removeChat(chat);
            chat.test();
            moderator.test();

            System.out.println("////////////////////////////////////////////\n");

            System.out.println("Testing the Client-Seller transactions");
            Seller seller = new Seller("Selling", "Guy", "Warszawa", LocalDate.of(2001, 4, 5));
            Transaction dogToys = seller.addTransaction(moderator, "Some toys for dog", LocalDate.of(2021, 5, 22), 22.5f);
            System.out.println("==Added the transaction. Showing the transaction extension, seller and moderator transaction lists");
            Transaction.showTransactions();
            seller.showTransactions();
            moderator.showTransactions();
            moderator.removeTransaction(dogToys);
            System.out.println("==Now moderator has removed the transaction");
            Transaction.showTransactions();
            seller.showTransactions();
            moderator.showTransactions();
            System.out.println("==Adding two transactions in a row, will they be stored properly?");
            Transaction dogToysOnceMore = moderator.addTransaction(seller, "More toys for dog", LocalDate.of(2021, 5, 23), 24.3f);
            Transaction catFood = seller.addTransaction(moderator, "Some food for cat", LocalDate.of(2021, 5, 23), 12f);
            Transaction.showTransactions();
            System.out.println();
            seller.showTransactions();
            System.out.println();
            moderator.showTransactions();
            System.out.println("==Adding one more client for the last test");
            Client buyer = new Client("Alina", "Muller", "Krakow", "alimu@gmail.com", "alimu");
            buyer.addTransaction(seller, "Some toys for cat", LocalDate.of(2021, 5, 23), 30f);
            System.out.println("\nTransactions extension");
            Transaction.showTransactions();
            System.out.println("\nSeller transactions");
            seller.showTransactions();
            System.out.println("\nModerator transactions");
            moderator.showTransactions();

            System.out.println("////////////////////////////////////////////\n");

            System.out.println("==Animal XOR association tests");
            Animal animal = new Animal("Alex", "Shiba Inu", LocalDate.of(2020, 12, 20));
            moderator.addOwnedAnimal(animal);
            buyer.addOwnedAnimal(animal);
//            seller.addAnimalToSell(animal);
//            animal.addSeller(seller);
            animal.showXorAssociation();

            System.out.println("////////////////////////////////////////////\n");

            System.out.println("==Testing the unique, own and attribute restriction");
            //Client buyer = new Client("Alina", "Muller", "Krakow", "alimu@gmail.com", "alimu");
//            Client nonUniqueClient = new Client("Ala", "Muller", "Krakow", "alamu@gmail.com", "alimu"); EXCEPTION
//            Client notValidEmail = new Client("Martin", "Muller", "Krakow", "martin", "marteen");
//            Seller youngSeller = new Seller("Janek", "Mlody", "Gdansk", LocalDate.of(2010, 5, 23));
        } catch (UniqueClientException | InvalidClientDataException | InvalidChatDataException | InvalidSellerDataException | InvalidAnimalDataException | XorAnimalException | AttributeSellerRestriction e) {
            e.printStackTrace();
        }
    }
}
