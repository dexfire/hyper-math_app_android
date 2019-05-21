package work.mathwiki;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        for(int i=0;i<10;i++)
            System.out.print("junt test case "+i+"\n");
    }

//    @Test
//    public void TestReflect() throws Exception{
//        Class cl = SettingsManager.ConfigNames.class;
//        Field[] fields = cl.getFields();
//        for (Field f : fields){
//            System.out.println(f.getName());
//            System.out.println(f.toGenericString());
//        }
//        System.out.println("\n\n=========================\n\n");
//        Class cll = SettingsManager.ConfigNames.class;
//        SettingsManager.ConfigNames cn = new SettingsManager.ConfigNames();
//        Field[] fieldss = cl.getFields();
//        for (Field f : fieldss){
//            System.out.println(f.getName());
//            System.out.println(f.get(cn));
//            System.out.println(f.toGenericString());
//        }
//
//        System.out.println("\n\n=========================\n\n");
//        cll = SettingsManager.ConfigNames.class;
//        cn = null;
//        fieldss = cl.getFields();
//        for (Field f : fieldss){
//            System.out.println(f.getName());
//            System.out.println(f.get(cn));
//            System.out.println(f.toGenericString());
//        }
//
//
//    }
}