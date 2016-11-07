package g.vanthanhbk.getpostgroupf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 05/10/2016.
 */
public class ViewPaperAdapter  extends FragmentPagerAdapter{

    private final List<Fragment> fragmentList= new ArrayList<>();
    private final List<String> mTitleFragment= new ArrayList<>();

    public ViewPaperAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fg,String title){
        fragmentList.add(fg);
        mTitleFragment.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleFragment.get(position);
    }
}
