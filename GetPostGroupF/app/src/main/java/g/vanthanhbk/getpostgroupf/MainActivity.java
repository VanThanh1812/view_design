package g.vanthanhbk.getpostgroupf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import g.vanthanhbk.getpostgroupf.api_facebook.ControlAPIFacebook;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabHost tabHost;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (AccessToken.getCurrentAccessToken() == null){
            goLoginActivity();
        }


        Bundle para2=new Bundle();
        para2.putString("fields","id,from,message,updated_time,picture,full_picture");
        new GraphRequest(AccessToken.getCurrentAccessToken(),"774199205937037/feed",
                para2,
                HttpMethod.GET,
                new GraphRequest.OnProgressCallback(){

                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject json =response.getJSONObject();
                        if (response.getJSONObject()!=(null)){

                            Log.d("groupf",json.optString("picture"));

                        }
                    }

                    @Override
                    public void onProgress(long current, long max) {

                    }
                }).executeAsync();

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/774199205937037_1278595975497355/comments",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */

                        if (response.getJSONObject()!=null){
                            JSONObject json =response.getJSONObject();
                            Log.d("group_comment",json.toString());

                        }

                    }
                }
        ).executeAsync();

        ControlAPIFacebook c =new ControlAPIFacebook(AccessToken.getCurrentAccessToken());
        c.getAllPostFromIdGroup("774199205937037");

        setupTabsLayout();
    }

    private void setupTabsLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewpaper);
        setUpViewPaper(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPaper(ViewPager viewPager) {
        ViewPaperAdapter adapter = new ViewPaperAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(),"ONE");
        adapter.addFragment(new TwoFragment(),"TWO");
        adapter.addFragment(new ThreeFragment(),"THREE");
        adapter.addFragment(new ThreeFragment(),"FOUR");
        adapter.addFragment(new ThreeFragment(),"FIVE");
        adapter.addFragment(new ThreeFragment(),"SIX");
        adapter.addFragment(new ThreeFragment(),"SEVEN");
        adapter.addFragment(new ThreeFragment(),"EIGHT");
        adapter.addFragment(new ThreeFragment(),"NINE");
        adapter.addFragment(new ThreeFragment(),"TEN");
        viewPager.setAdapter(adapter);
    }

    private void goLoginActivity() {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            LoginManager.getInstance().logOut();
            goLoginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
