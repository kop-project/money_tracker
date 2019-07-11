package com.example.money_tracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public class Item {
        String name;
        int price;

        public Item(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = findViewById(R.id.items);

        //items.setAdapter(new ItemsAdapter());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new Item(name.getText().toString(), Integer.valueOf(price.getText().toString()));
            }
        });
    }



    class ItemsAdapter extends ArrayAdapter<Item> {
        public ItemsAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, R.layout.item);
        }

      /*  @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
            getLayoutInflater().inflate(R.layout.item, parent);
        }*/
    }
}
