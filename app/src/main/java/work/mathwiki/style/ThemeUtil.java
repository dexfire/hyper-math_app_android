package work.mathwiki.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;


public class ThemeUtil {
    /**
     * @param context
     * @param resId
     * @return
     */


    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int resId){
        ColorStateList colorStateList = null;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(resId,typedValue,true);
        if(typedValue.type>0x1c && typedValue.type < 0x1f){
            return colorStateList.valueOf(context.getResources().getColor(typedValue.resourceId));
        }
        String resname = typedValue.string.toString();
        if(!resname.endsWith("xml")){
            return null;
        }

        try {
            int next;
            XmlPullParser xmlResourceParser = context.getResources().getAssets().openXmlResourceParser(typedValue.assetCookie,resname);
            AttributeSet attr = Xml.asAttributeSet(xmlResourceParser);
            do {
                next=xmlResourceParser.next();
                if(next==2)
                    break;
            }while (next!=1);
            if(next!=2){
                throw(new Exception("Error Data"));
            }
            //colorStateList =
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colorStateList;
    }

}
