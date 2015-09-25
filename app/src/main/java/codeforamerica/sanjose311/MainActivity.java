package codeforamerica.sanjose311;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import codeforamerica.sanjose311.fragments.RequestsFeed;

public class MainActivity extends AppCompatActivity {

    static int SELECTED = 255;
    static int UNSELECTED = 77;
    TabView requestTabView;
    TabView alertTabView;
    FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatButton = (FloatingActionButton) findViewById(R.id.fab);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post_request = new Intent(MainActivity.this, PostRequestActivity.class);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                startActivity(post_request);
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);

        requestTabView = new TabView((LinearLayout)getLayoutInflater().inflate(R.layout.tab_view,
                null, true));
        alertTabView = new TabView((LinearLayout)getLayoutInflater().inflate(R.layout.tab_view,
                null, true));

        for (int i=0; i<tabLayout.getTabCount();i++) {
            tabLayout.getTabAt(i).setCustomView(getImages(i));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                setAlpha(tab.getPosition(), MainActivity.SELECTED);

                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setAlpha(tab.getPosition(), MainActivity.UNSELECTED);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(0);
        setAlpha(0, MainActivity.SELECTED);
        //tabLayout.getTabAt(0).getIcon().setAlpha(MainActivity.SELECTED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                startActivity(search);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RequestsFeed(getResources().getColor(R.color.accent_material_light)), "");
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_light)), "");
        viewPager.setAdapter(adapter);
    }

    private View getImages(int i) {
        switch(i) {
            case 0:
                requestTabView.setTabIcon(R.mipmap.request);
                requestTabView.getTabIcon().setAlpha(MainActivity.UNSELECTED);
                return requestTabView.getView();
            case 1:
                alertTabView.setTabIcon(R.mipmap.alerts);
                alertTabView.getTabIcon().setAlpha(MainActivity.UNSELECTED);
                return alertTabView.getView();
        }
        return null;
    }

    private void setAlpha(int i, int value) {
        switch(i) {
            case 0:
                requestTabView.getTabIcon().setAlpha(value);
                break;
            case 1:
                alertTabView.getTabIcon().setAlpha(value);
                break;
        }
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static class DummyFragment extends Fragment {
        int color;

        public DummyFragment() {
        }

        @SuppressLint("ValidFragment")
        public DummyFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            return view;
        }
    }

    class TabView{
        private ImageView tabIcon;
        private View view;
        public TabView(LinearLayout linearLayout){
            view = linearLayout;
            tabIcon = (ImageView) view.findViewById(R.id.tabicon);
        }

        public void setTabIcon(int id){
            tabIcon.setImageResource(id);
        }

        public ImageView getTabIcon() {
            return tabIcon;
        }

        public View getView(){
            return view;
        }
    }
}
