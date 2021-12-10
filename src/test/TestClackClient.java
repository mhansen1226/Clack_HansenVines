package test;

import main.ClackClient;

/**
 * Test class to test Clack Client
 *
 * @author Matt Vines
 */

public class TestClackClient {
    public static void main(String[] args) {

        try {
            ClackClient CC0 = new ClackClient();
        }catch (IllegalArgumentException iae){
            System.err.println(iae.getMessage());
        }
        try {
            ClackClient CC0 = new ClackClient("USERNAME", null);
        }catch (IllegalArgumentException iae){
            System.err.println(iae.getMessage());
        }
        try {
            ClackClient CC0 = new ClackClient("USERNAME", "HOSTNAME", 1023);
        }catch (IllegalArgumentException iae){
            System.err.println(iae.getMessage());
        }

        ClackClient CC1 = new ClackClient("USERNAME","HOSTNAME", 7000);
        System.out.println("--- CC1 --- \n" +  CC1);
        System.out.println("getUserName(): " + CC1.getUserName());
        System.out.println("getHostName(): " + CC1.getHostName());
        System.out.println("getPort(): " + CC1.getPort());
        System.out.println("hashCode(): "+ CC1.hashCode() + "\n");
        CC1.start();

        ClackClient CC2 = new ClackClient("USERNAME", "HOSTNAME");
        System.out.println("--- CC2 --- \n" +  CC2);
        System.out.println("getUserName(): " + CC2.getUserName());
        System.out.println("getHostName(): " + CC2.getHostName());
        System.out.println("getPort(): " + CC2.getPort());
        System.out.println("hashCode(): "+ CC2.hashCode() + "\n");
        CC2.start();

        ClackClient CC3 = new ClackClient("USERNAME");
        System.out.println("--- CC3 --- \n" +  CC3);
        System.out.println("getUserName(): " + CC3.getUserName());
        System.out.println("getHostName(): " + CC3.getHostName());
        System.out.println("getPort(): " + CC3.getPort());
        System.out.println("hashCode(): "+ CC3.hashCode() + "\n");
        CC3.start();

        ClackClient CC4 = new ClackClient();
        System.out.println("--- CC4 --- \n" +  CC4);
        System.out.println("getUserName(): " + CC4.getUserName());
        System.out.println("getHostName(): " + CC4.getHostName());
        System.out.println("getPort(): " + CC4.getPort());
        System.out.println("hashCode(): "+ CC4.hashCode() + "\n");
        CC4.start();


        System.out.println(CC1.equals(CC2));
        System.out.println(CC1.equals(CC4));


    }
}
