package work.mathwiki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

public class BasicTestActivity extends ActivityInstrumentationTestCase2 {

    private Context context;

    public BasicTestActivity() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getActivity().getApplicationContext();
    }

    public void testStart() {
        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
