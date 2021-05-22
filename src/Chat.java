import exceptions.InvalidChatDataException;

import java.util.ArrayList;
import java.util.HashSet;

public class Chat {
    private static ArrayList<Chat> chats = new ArrayList<>();

    private HashSet<Client> consistsOf = new HashSet<>();
    private HashSet<Client> moderatedBy = new HashSet<>();

    private String name;
    private String description;

    Chat(String name) throws InvalidChatDataException {
        setName(name);
        setDescription("This chat doesn't posses a description");
        addChat(this);
    }

    Chat(String name, String description) throws InvalidChatDataException {
        this(name);
        setDescription(description);
    }

    //TOREMOVE
    public void test() {
        showMembers();
        showModerators();
        System.out.println();
    }

    public boolean isMember(Client client) {
        return consistsOf.contains(client);
    }

    public boolean isModeratedBy(Client client) {
        return isMember(client) && moderatedBy.contains(client);
    }

    public void addMember(Client client) {
        if (!isMember(client)) {
            consistsOf.add(client);
            client.joinChat(this);
        }
    }

    public void addModerator(Client client) {
        addMember(client);
        if (!isModeratedBy(client)) {
            moderatedBy.add(client);
            client.moderateChat(this);
        }
    }

    public void removeMember(Client client) {
        if (isMember(client)) {
            consistsOf.remove(client);
            client.leaveChat(this);
            if (isModeratedBy(client))
                moderatedBy.remove(client);
        }
    }

    public void removeModerator(Client client) {
        if (isModeratedBy(client)) {
            moderatedBy.remove(client);
            client.stopModerating(this);
        }
    }

    private void showMembers() {
        System.out.println("CHAT " + getName() + " IS JOINED BY");
        for (Client client : consistsOf)
            System.out.println(client.getName());
    }

    private void showModerators() {
        System.out.println("CHAT " + getName() + " IS MODERATED BY");
        for (Client client : moderatedBy)
            System.out.println(client.getName());
    }

    private static void addChat(Chat chat) {
        if (!chats.contains(chat))
            chats.add(chat);
    }

    public static void removeChat(Chat chat) {
        chats.remove(chat);
    }

    public static void showChats() {
        for (Chat chat : chats)
            System.out.println(chat + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidChatDataException {
        if (name == null)
            throw new InvalidChatDataException("Chat name is invalid!");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chat name: ")
                .append(getName())
                .append("\nDescription: ")
                .append(getDescription());
        return sb.toString();
    }
}
