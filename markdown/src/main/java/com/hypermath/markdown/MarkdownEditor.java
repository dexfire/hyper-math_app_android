package com.hypermath.markdown;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.hypermath.markdown.util.TextUtil;

import java.util.Stack;

public class MarkdownEditor extends AppCompatEditText {

    // 写入锁
    private boolean lock = false;

    private Stack<Action> undo = new Stack<>();

    private Stack<Action> redo = new Stack<>();

    public MarkdownEditor(Context context) {
        super(context,null);
    }

    public MarkdownEditor(Context context, AttributeSet attrs) {
        super(context, attrs,android.R.style.Widget_Material_Light_EditText);
    }

    public MarkdownEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

    private void inti() {
        addTextChangedListener(new InputTextWatcher());
        addTextChangedListener(new ActionWatcher());
    }



    private void onTextChanged(CharSequence text) {

    }

    // 文本的内容对象被替换？
    private void onEditableChanged(Editable s){

    }


    /***
     *  监听弹出菜单，粘贴时自动将缩进换为空格
     * @param id
     * @return
     */
    @Override
    public boolean onTextContextMenuItem(int id) {
        switch (id){
            case android.R.id.paste:
                ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                String content;
                if (clip!=null && TextUtil.isEmpty(content = clip.getText().toString())){
                    clip.setText( content.replace("\t","    ") );
                }
                break;
        }
        return super.onTextContextMenuItem(id);
    }

    public void undo(){
        if (undo.isEmpty()) return;
        // 锁定输入
        lock = true;
        Action last = undo.pop();
        if (last!=null){

        }

    }

    private class Action{
        int type;

    }

    private class InputTextWatcher implements TextWatcher{

        /**
         *  这个方法用于通知你，s中的从下标start的一段长度为count的文本，
         *  即将被新的长度为after的文本代替。
         *  你**不应该**在这里进行文本修改。
         *
         * @param s             文本内容
         * @param start      起始光标位置
         * @param count     选择数量
         * @param after      新长度
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if(lock) return;
            int end = start + count;
            //
            if (end > start && end <= s.length()){
                CharSequence chars = s.subSequence(start,end);
                if(chars.length() > 0){
                    onDeleteText(s,chars,start);
                }
            }
        }

        /**
         * 当锁定文本时不产生动作
         *
         * @param s             文本内容
         * @param start      被替换内容的起点下标
         * @param before    旧的长度
         * @param count     新的长度
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (lock) return;
            int end = start + count;
            if (end>start){
                CharSequence chars = s.subSequence(start,end);
                if (chars.length()>0){
                    onInsertText(s,chars,start);
                }
            }
        }

        /**
         *  这个方法用于通知你：
         *  在s中的一些文本已经被更改了，你有权对s进行后期修改
         *  但是小心不要陷入死循环，因为任何改变会再次引起本函数被调用。
         *  (你不会被告知哪里作了修改，因为其他WatcherCallback中的
         *  afterTextChanged()函数可能已经作了其他修改但是如果你想要知道
         *  你可以在onTextChanged()中使用Spannable.setSpan() ，然后
         *  在这个函数里面查找span的位置)
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
            if (s != getText()){
                onEditableChanged(s);
            }
            MarkdownEditor.this.onTextChanged(s);
        }
    }

    /***
     *  插入文本的回调。
     *  1. 如果插入的换行符是\n，则对其进行替换。
     * @param s
     * @param chars
     * @param start
     */
    private void onInsertText(CharSequence s, CharSequence chars, int start) {
        lock = true;
        if ("‘\n".equals(chars.toString())){
            performInsertEnter(getText(),s,start);
        }
    }

    private void performInsertEnter(Editable text, CharSequence s, int start) {
    }


    /***
     *  从S中移除文本chars的回调
     *
     * @param s
     * @param chars
     * @param start
     */
    private void onDeleteText(CharSequence s, CharSequence chars, int start) {
        boolean old = lock;
        lock = true;
        // TODO: onDeleteText
        lock = old;
    }

    private class ActionWatcher implements TextWatcher {

        /**
         *  监听
         * @param s
         * @param start
         * @param count
         * @param after
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
