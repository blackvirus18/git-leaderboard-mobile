package Adapters;

/**
 * Created by deepeshnaini on 29/08/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blackvirus.deepeshnaini.go_git_leaderboard.RepoStatsFragment;
import com.blackvirus.deepeshnaini.go_git_leaderboard.UserStatsFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new UserStatsFragment();
            case 1:
                return new RepoStatsFragment();

        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }

}
