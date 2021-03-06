package test;

import data.*;

import java.io.IOException;

/**
 * Test class for ClackData
 *
 * @author Matt Hansen
 */
public class TestClackData {
    public static void main(String[] args) throws IOException {

        final String CONSTANT_TESTINGKEY = "woah";
        // Testing MessageClackData
        System.out.println("====== Testing MessageClackData ======");
        MessageClackData MCD1 = new MessageClackData("mhansen", "Hello!", 2);
        System.out.println("--- MCD1 ---\n" + MCD1);
        System.out.println("getType(): " + MCD1.getType());
        System.out.println("getUsername(): " + MCD1.getUsername());
        System.out.println("getDate(): " + MCD1.getDate());
        System.out.println("getData(): " + MCD1.getData());
        System.out.println("hashCode(): " + MCD1.hashCode() + "\n");

        MessageClackData MCD2 = new MessageClackData();
        System.out.println("--- MCD2 ---\n" + MCD2);
        System.out.println("getType(): " + MCD2.getType());
        System.out.println("getUsername(): " + MCD2.getUsername());
        System.out.println("getDate(): " + MCD2.getDate());
        System.out.println("getData(): " + MCD2.getData());
        System.out.println("hashCode(): " + MCD2.hashCode() + "\n");

        MessageClackData MCD3 = new MessageClackData("mhansen", "Hello!", 2);
        System.out.println("--- MCD3 ---\n" + MCD3);
        System.out.println("getType(): " + MCD3.getType());
        System.out.println("getUsername(): " + MCD3.getUsername());
        System.out.println("getDate(): " + MCD3.getDate());
        System.out.println("getData(): " + MCD3.getData());
        System.out.println("hashCode(): " + MCD3.hashCode() + "\n");

        MessageClackData MCD4 = new MessageClackData("mhansen", "What is up man!", CONSTANT_TESTINGKEY, 2);
        System.out.println("--- MCD4 ---\n" + MCD4);
        System.out.println("getType(): " + MCD4.getType());
        System.out.println("getUsername(): " + MCD4.getUsername());
        System.out.println("getDate(): " + MCD4.getDate());
        System.out.println("getData(): " + MCD4.getData());
        System.out.println("getData(key): " + MCD4.getData(CONSTANT_TESTINGKEY));
        System.out.println("hashCode(): " + MCD4.hashCode() + "\n");

        System.out.println(MCD1.equals(MCD2));
        System.out.println(MCD1.equals(MCD3));

        // Testing FileClackData
        System.out.println("====== Testing FileClackData ======");
        FileClackData FCD1 = new FileClackData("mhansen", "src/test/Part2_document.txt",3);
        System.out.println("--- FCD1 ---\n" + FCD1);
        System.out.println("getType(): " + FCD1.getType());
        System.out.println("getUsername(): " + FCD1.getUsername());
        System.out.println("getDate(): " + FCD1.getDate());
        System.out.println("getFileName(): " + FCD1.getFileName());
        System.out.println("hashCode(): " + FCD1.hashCode() + "\n");
        FCD1.readFileContents();
        System.out.println("getData(): " + FCD1.getData());

        FCD1.setFileName("src/test/writeFileTest.txt");
        System.out.println("--- Changed fileName FCD1 ---\n" + FCD1);
        System.out.println("getType(): " + FCD1.getType());
        System.out.println("getUsername(): " + FCD1.getUsername());
        System.out.println("getDate(): " + FCD1.getDate());
        System.out.println("getFileName(): " + FCD1.getFileName());
        System.out.println("hashCode(): " + FCD1.hashCode() + "\n");
        FCD1.writeFileContents();
        System.out.println("getData(): " + FCD1.getData());

        FileClackData FCD2 = new FileClackData("mvines", "src/test/Part2_document.txt",3);
        System.out.println("--- FCD2 ---\n" + FCD2);
        System.out.println("getType(): " + FCD2.getType());
        System.out.println("getUsername(): " + FCD2.getUsername());
        System.out.println("getDate(): " + FCD2.getDate());
        System.out.println("getFileName(): " + FCD2.getFileName());
        System.out.println("hashCode(): " + FCD2.hashCode() + "\n");
        FCD2.readFileContents(CONSTANT_TESTINGKEY);
        System.out.println("getData(): " + FCD2.getData());
        System.out.println("getData(key): " + FCD2.getData(CONSTANT_TESTINGKEY));

        FCD2.setFileName("src/test/writeFileTest.txt");
        System.out.println("--- Changed fileName FCD2 ---\n" + FCD2);
        System.out.println("getType(): " + FCD2.getType());
        System.out.println("getUsername(): " + FCD2.getUsername());
        System.out.println("getDate(): " + FCD2.getDate());
        System.out.println("getFileName(): " + FCD2.getFileName());
        System.out.println("hashCode(): " + FCD2.hashCode() + "\n");
        FCD2.writeFileContents(CONSTANT_TESTINGKEY);
        System.out.println("getData(): " + FCD2.getData());
        System.out.println("getData(key): " + FCD2.getData(CONSTANT_TESTINGKEY));
    }
}
