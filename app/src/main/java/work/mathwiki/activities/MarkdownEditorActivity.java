package work.mathwiki.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import work.mathwiki.R;

/***
 *      Markdown 编辑器
 */

public class MarkdownEditorActivity extends AppCompatActivity implements View.OnClickListener {

    private static String segments =
            "Tab\n" +
            "-\n" +
            "B\n" +
            "[]\n" +
            "()\n" +
            "{}\n" +
            "``\n" +
            "f\n" +
            "";
    private static String segments_raw =
            "\t\n" +
            "-\n" +
            "B\n" +
            "[]\n" +
            "()\n" +
            "{}\n" +
            "``\n" +
            "`$$`\n" +
            "";
    LinearLayout toolbar_container = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown_editor);
        toolbar_container = findViewById(R.id.mdEditor_input_toolbar_container);
        initToolbar();
    }

    private void initToolbar() {
        if(toolbar_container!=null){
            String[] tools = segments.split("\n");
            String[] raw = segments_raw.split("\n");
            for(int i=0;i<tools.length;i++){
                toolbar_container.addView(createView(i,tools[i],raw[i]));
            }
        }
    }

    private AppCompatTextView createView(int position,String appearence,String input_raw){
        AppCompatTextView text = new AppCompatTextView(this);
        text.setText(appearence);
        text.setOnClickListener(this);
        text.setTag(position);
        return text;
    }

    @Override
    public void onClick(View v) {
        switch ((int)(v.getTag())){
            case 0:

                break;
        }
    }
}
