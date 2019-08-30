package com.codepath.mypizza.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.mypizza.R;
import com.codepath.mypizza.data.Pizza;

/**
 * Created by Shyam Rokde on 8/5/16.
 */
public class PizzaMenuFragment extends Fragment {


  ArrayAdapter<String> itemsAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Pizza.pizzaMenu);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
    // Inflate the xml file for the fragment
    return inflater.inflate(R.layout.fragment_pizza_menu, parent, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {

    ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
    lvItems.setAdapter(itemsAdapter);

    lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // go to activity to load pizza details fragment
        listener.onPizzaItemSelected(position); // (3) Communicate with Activity using Listener
      }
    });
  }

  private OnItemSelectedListener listener;



  //--OnItemSelectedListener listener;
  // This event fires 1st, before creation of fragment or any views
  // The onAttach method is called when the Fragment instance is associated with an Activity.
  // This does not mean the Activity is fully initialized.
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if(context instanceof OnItemSelectedListener){      // context instanceof YourActivity
      this.listener = (OnItemSelectedListener) context; // = (YourActivity) context
    } else {
      throw new ClassCastException(context.toString()
        + " must implement PizzaMenuFragment.OnItemSelectedListener");
    }
  }


  // Define the events that the fragment will use to communicate
  public interface OnItemSelectedListener {
    // This can be any number of events to be sent to the activity
    void onPizzaItemSelected(int position);
  }

}
