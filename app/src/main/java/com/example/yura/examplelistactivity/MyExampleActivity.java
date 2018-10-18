package com.example.yura.examplelistactivity;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MyExampleActivity extends ListActivity implements AdapterView.OnItemLongClickListener {


    final String[] catNamesArray = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "сраный пёсик Бобик", "Кристина", "Пушок",
            "Дымка", "Кузя", "Китти", "сраный пёсик Барбос", "Масяня", "Симба", ""};

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> catNamesList = new ArrayList<>(Arrays.asList(catNamesArray));

    private ArrayAdapter<String> mMonthAdapter, mWeekOfDayAdapter;
    private String mMonth, mDayOfWeek;
    private String flag="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        flag=getIntent().getStringExtra("userflag");
        if (flag.equalsIgnoreCase("1")) {setContentView(R.layout.activity_customlist);}
        if (flag.equalsIgnoreCase("2")) {setContentView(R.layout.activity_customlist1);}

        if (flag.equals("0") || flag.equals("1")) {
            //Вариант 0 и 1
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, catNamesList);
            setListAdapter(mAdapter);
            getListView().setOnItemLongClickListener(this);
        } else {
            //Вариант 2
            mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, catNamesList);
        }
        setListAdapter(mAdapter);
        getListView().setOnItemLongClickListener(this);

    }

/*
    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (flag.equals("0")||flag.equals("1")) {
        return super.onListItemClick(inflater, container, position, savedInstanceState);} else
        {return inflater.inflate(R.layout.listfragment, null);
        }
    }
*/

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (flag.equals("0")||flag.equals("1"))
        {
            Toast.makeText(getApplicationContext(),
                    "Вы выбрали " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        } else
        {
            String prompt = "Вы выбрали: "
                    + getListView().getItemAtPosition(position).toString() + "\n";

            prompt += "Выбранные элементы: \n";
            int count = getListView().getCount();
            SparseBooleanArray sparseBooleanArray = getListView()
                    .getCheckedItemPositions();
            for (int i = 0; i < count; i++) {
                if (sparseBooleanArray.get(i)) {
                    prompt += getListView().getItemAtPosition(i).toString() + "\n";
                }
            }
            Toast.makeText(getApplicationContext(), prompt, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (flag.equals("0") || flag.equals("1")) {
            String selectedItem = parent.getItemAtPosition(position).toString();

            mAdapter.remove(selectedItem);
            mAdapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(),
                    selectedItem + " удалён.",
                    Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }


}





