package com.isil.mypets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.isil.mypets.entity.UserEntity;
import com.isil.mypets.fragments.ComentariosNoticiaFragment;
import com.isil.mypets.fragments.NoticiaDetalleFragment;
import com.isil.mypets.fragments.NoticiaFragment;
import com.isil.mypets.listeners.OnCommentNoticeListener;
import com.isil.mypets.listeners.OnNoticeListener;
import com.isil.mypets.presenter.LogInView;
import com.isil.mypets.storage.PreferencesHelper;
import com.isil.mypets.listeners.OnNavListener;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnNavListener,OnNoticeListener,OnCommentNoticeListener{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userEntity=new UserEntity();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {


                    case R.id.menuLogout:
                        changeFragment(0,null);
                        return true;


                    case R.id.menuNoticias:
                        changeFragment(2,null);
                        return true;

                    default:
                        return true;

                }

            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void changeFragment(int i,String id) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (i) {

            case 0:
                logout();
                break;

            case 2:
                fragment= new NoticiaFragment();
                break;

            case 3:
                bundle.putString("idNoticia", id);
                fragment= new NoticiaDetalleFragment();
                fragment.setArguments(bundle);
                break;

            case 4:
                bundle.putString("idNoticia", id);
                fragment= new NoticiaDetalleFragment();
                fragment.setArguments(bundle);
                break;

            case 5:
                bundle.putString("idNoticia", id);
                fragment= new ComentariosNoticiaFragment();
                fragment.setArguments(bundle);
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        }
    }

    private void init() {
        String email= PreferencesHelper.getUserSession(getApplicationContext());
        String pass= PreferencesHelper.getPassSession(getApplicationContext());
        String id=PreferencesHelper.getIdSession(getApplicationContext());
        String alias=PreferencesHelper.getAliasSession(getApplicationContext());

        userEntity.setObjectId(id);
        userEntity.setEmail(email);
        userEntity.setPassword(pass);
        userEntity.setAlias(alias);
        //currentUser=crudUserOperations.logIn(email,pass);
    }

    private void logout() {
        PreferencesHelper.signOut(getApplicationContext());
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }



    @Override
    public void mostrarNoticiaDetalle(String id) {
        changeFragment(3,id);
    }

    @Override
    public void volverListarNoticia() {
        changeFragment(2,null);
    }

    @Override
    public void verComentariosNoticia(String id) {
        changeFragment(5,id);
    }

    @Override
    public void volverNoticiaDetalle(String id) {
        changeFragment(3,id);
    }
}
