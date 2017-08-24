package pages;

import java.io.PrintStream;

public class MainPage {
    public void test1(PrintStream to, String name){
        to.println(createmem(name));
    }
    public String createmem(String name){
        return "Hello, " + name + "!";
    }
}
