package tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.MainPage;
import sun.tools.jar.resources.jar;

import javax.inject.Inject;
import java.io.PrintStream;

@RunWith(Arquillian.class)
public class LoginTestSet {

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).
                addClass(MainPage.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private MainPage mem;

    @Test
    public void first_test(){
        Assert.assertEquals("Hello, Friend!",
                mem.createmem("Friend"));
        mem.test1(System.out, "Friend");
    }
}
