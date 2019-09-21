package com.example.money_tracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    static class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);
        final ItemsAdapter adapter = new ItemsAdapter();

        items.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               OkHttpClient client = new OkHttpClient();

                try {
                    run(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }

              /*  OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://192.168.0.109:8081/registration")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override public void onFailure(Request request, Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override public void onResponse(Response response) throws IOException {
                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                        System.out.println(response.body().string());
                    }
                });
*/

                adapter.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));
            }
        });
    }

    private class ItemsAdapter extends ArrayAdapter<Item> {
        ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final Item item = getItem(position);
            ((TextView) view.findViewById(R.id.name)).setText(item.name);
            ((TextView) view.findViewById(R.id.price)).setText(String.valueOf(item.price));
            return view;
        }
    }


    public void run(OkHttpClient client) throws Exception {
        Request request = new Request.Builder()
                .url("http://192.168.0.109:8081/registration")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(123);
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                System.out.println(response.body().string());
            }


        });
    }

}
