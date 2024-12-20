public class SportsClubSystemMain {
    public static void main(String[] args) {
        MemberManager memberManager = new MemberManager();
        EventManager eventManager = new EventManager();

        memberManager.addMember("M001", "Alice", 3, "Tennis");
        memberManager.addMember("M004562", "Bob", 1, "Swimming");
        memberManager.addMember("M003", "John", 5, "Boxing");
        memberManager.addMember("M00234", "Annie", 4, "Swimming");
        memberManager.addMember("M005", "Babken", 2, "Wrestling");
        memberManager.addMember("M0065", "Poghos", 6, "Swimming");

        eventManager.registerForEvent("E001", "M001", "Yoga");
        eventManager.registerForEvent("E002", "M002", "Aerobics");
        eventManager.registerForEvent("E003", "M003", "Karate");
        eventManager.registerForEvent("E004", "M004", "Boxing");
        eventManager.registerForEvent("E005", "M005", "Swimming");
        eventManager.registerForEvent("E006", "M006", "Karate");

        eventManager.cancelRegistration("E004");
        System.out.println(eventManager.toString());

        memberManager.sortMembersByStatus();
        memberManager.printTree();

        System.out.println(memberManager.searchMember("M00234"));
    }
}
