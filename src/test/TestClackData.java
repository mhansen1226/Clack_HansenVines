package test;

import data.*;

/**
 * Test class for ClackData
 *
 * @author Matt Hansen
 */
public class TestClackData {
    public static void main(String[] args) {
        // Testing MessageClackData
        System.out.println("====== Testing MessageClackData ======");
        MessageClackData MCD1 = new MessageClackData("mhansen", "Hello!", 2);
        System.out.println("--- MCD1 ---\n" + MCD1);
        System.out.println("getData(): " + MCD1.getData());
        System.out.println("hashCode(): " + MCD1.hashCode() + "\n");

        MessageClackData MCD2 = new MessageClackData();
        System.out.println("--- MCD2 ---\n" + MCD2);
        System.out.println("getData(): " + MCD2.getData());
        System.out.println("hashCode(): " + MCD2.hashCode() + "\n");

        MessageClackData MCD3 = new MessageClackData("mhansen", "Hello!", 2);
        System.out.println("--- MCD3 ---\n" + MCD3);
        System.out.println("getData(): " + MCD3.getData());
        System.out.println("hashCode(): " + MCD3.hashCode() + "\n");

        System.out.println(MCD1.equals(MCD2));
        System.out.println(MCD1.equals(MCD3));

        // Testing FileClackData
        System.out.println("====== Testing FileClackData ======");
        FileClackData FCD1 = new FileClackData("mhansen", "file.txt", 3);
        System.out.println("--- FCD1 ---\n" + FCD1);
        System.out.println("getFileName(): " + FCD1.getFileName());
        System.out.println("getData(): " + FCD1.getData());
        System.out.println("hashCode(): " + FCD1.hashCode() + "\n");

        FCD1.setFileName("newFile.txt");
        System.out.println("--- Changed fileName FCD1 ---\n" + FCD1);
        System.out.println("getFileName(): " + FCD1.getFileName());
        System.out.println("getData(): " + FCD1.getData());
        System.out.println("hashCode(): " + FCD1.hashCode() + "\n");

        FileClackData FCD2 = new FileClackData();
        System.out.println("--- FCD2 ---\n" + FCD2);
        System.out.println("getFileName(): " + FCD2.getFileName());
        System.out.println("getData(): " + FCD2.getData());
        System.out.println("hashCode(): " + FCD2.hashCode() + "\n");

        FCD2.setFileName("newFile.txt");
        System.out.println("--- Changed fileName FCD2 ---\n" + FCD2);
        System.out.println("getFileName(): " + FCD2.getFileName());
        System.out.println("getData(): " + FCD2.getData());
        System.out.println("hashCode(): " + FCD2.hashCode() + "\n");
    }
}
