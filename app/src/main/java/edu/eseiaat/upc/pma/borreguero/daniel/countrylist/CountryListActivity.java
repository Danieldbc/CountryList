package edu.eseiaat.upc.pma.borreguero.daniel.countrylist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CountryListActivity extends AppCompatActivity {

    private ArrayList<String> countryList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        String[] countries= getResources().getStringArray(R.array.countries);
        countryList =new ArrayList<>(Arrays.asList(countries));
        ListView list=(ListView)findViewById(R.id.CountryListView);
        adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countryList);
        list.setAdapter(adapter);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String sel=getResources().getString(R.string.selection);
               Toast.makeText(
                       CountryListActivity.this,
                       String.format(sel +"  '%s'", countryList.get(pos)),
                       Toast.LENGTH_SHORT).show();
           }
       });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int pos, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(CountryListActivity.this);
                builder.setTitle(R.string.confirm);
                String msg=getResources().getString(R.string.sure);
                builder.setMessage(msg + " " + countryList.get(pos)+ "?");
                builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        countryList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel,null);
                builder.create().show();
                return true;
            }
        });
    }
}
