package test;

import main.ClackServer;

public class TestClackServer {
    public static void main(String[] args) {
        ClackServer CS1 = new ClackServer(1000);
        System.out.println("--- CS1 ---\n" + CS1);
        System.out.println("getPort(): " + CS1.getPort());
        System.out.println("hashCode(): " + CS1.hashCode());

        ClackServer CS2 = new ClackServer();
        System.out.println("--- CS2 ---\n" + CS2);
        System.out.println("getPort(): " + CS2.getPort());
        System.out.println("hashCode(): " + CS2.hashCode());

        ClackServer CS3 = new ClackServer(7000);
        System.out.println("--- CS3 ---\n" + CS3);
        System.out.println("getPort(): " + CS3.getPort());
        System.out.println("hashCode(): " + CS3.hashCode());

        System.out.println("CS1 == CS2 ? " + CS1.equals(CS2));
        System.out.println("CS2 == CS3 ? " + CS2.equals(CS3));
    }
}
