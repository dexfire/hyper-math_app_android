/* The MIT License (MIT)
 * Copyright (c) 2018 OSChina.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package work.mathwiki.introduce;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.mathwiki.R;
import work.mathwiki.base.activities.BaseActivity;
import work.mathwiki.utility.AppSharedPreference;

public class IntroduceActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_introduce;
    }
    public static void show(Context context){
        IS_ACTIVE = true;
        context.startActivity(new Intent(context,IntroduceActivity.class));
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
        AppSharedPreference.getInstance().putFirstInstall();
        setSwipeBackEnable(false);
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {
        private FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? FragmentIntroOne.newInstance() : FragmentIntroTwo.newInstance();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
