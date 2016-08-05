package com.codepath.mypizza;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codepath.mypizza.fragments.PizzaDetailFragment;
import com.codepath.mypizza.fragments.PizzaMenuFragment;

public class MainActivity extends AppCompatActivity implements PizzaMenuFragment.OnItemSelectedListener {

  int orientation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Check layout
    orientation = getResources().getConfiguration().orientation;

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
  }

  /**
   * Fragment will communicate to me (activity) on this callback method.....
   * @param position
   */
  @Override
  public void onPizzaItemSelected(int position) {

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
      // Load Pizza Detail Fragment
      PizzaDetailFragment secondFragment = new PizzaDetailFragment();
      Bundle args = new Bundle();
      args.putInt("position", position);
      secondFragment.setArguments(args);

      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flContainer, secondFragment)
          .addToBackStack(null)
          .commit();
    }else{
      PizzaDetailFragment pizzaDetailFragment = (PizzaDetailFragment) getSupportFragmentManager()
          .findFragmentById(R.id.pizza_detail_fragment);
      if(pizzaDetailFragment != null){
        pizzaDetailFragment.updateView(position);
      }
    }
  }
}
