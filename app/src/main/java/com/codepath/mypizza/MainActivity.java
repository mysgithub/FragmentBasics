package com.codepath.mypizza;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.codepath.mypizza.fragments.PizzaDetailFragment;
import com.codepath.mypizza.fragments.PizzaMenuFragment;

public class MainActivity extends AppCompatActivity implements PizzaMenuFragment.OnItemSelectedListener  {

  int orientation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Check layout
    orientation = getResources().getConfiguration().orientation;

    // PORTRAIT
    if(orientation == Configuration.ORIENTATION_PORTRAIT){
      if (savedInstanceState == null) {
        // Instance of first fragment
        PizzaMenuFragment firstFragment = new PizzaMenuFragment();

        // Add Fragment to FrameLayout (flContainer), using FragmentManager
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
        ft.add(R.id.flContainer, firstFragment);                                // add    Fragment
        ft.commit();                                                            // commit FragmentTransaction
      }
    }

    // LANDSCAPE - Fragments are already present in XML Layout file - check activity_main.xml(land)
    // so no need to do anything here...

    Log.e("DEBUG", "Activity - onCreate()");
  }


  /**
   * Fragment will communicate to me (activity) on this callback method.....
   * @param position
   */
  @Override
  public void onPizzaItemSelected(int position) {

    // PORTRAIT
    if(orientation == Configuration.ORIENTATION_PORTRAIT){
      // Load Pizza Detail Fragment
      PizzaDetailFragment secondFragment = new PizzaDetailFragment();

      Bundle args = new Bundle();
      args.putInt("position", position);
      secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle

      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flContainer, secondFragment)
          .addToBackStack(null)
          .commit();
    }else{
      // LANDSCAPE - Fragments are already present in XML Layout file - check activity_main.xml(land)
      PizzaDetailFragment pizzaDetailFragment = (PizzaDetailFragment) getSupportFragmentManager()
          .findFragmentById(R.id.pizza_detail_fragment);

      if(pizzaDetailFragment != null){
        pizzaDetailFragment.updateView(position); // (2) Communicate with Fragment by calling Method
      }
    }
  }




  @Override
  protected void onStart() {
    super.onStart();
    Log.e("DEBUG", "Activity - onStart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.e("DEBUG", "Activity - onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.e("DEBUG", "Activity - onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.e("DEBUG", "Activity - onStop()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.e("DEBUG", "Activity - onRestart()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.e("DEBUG", "Activity - onDestroy()");
  }
}
