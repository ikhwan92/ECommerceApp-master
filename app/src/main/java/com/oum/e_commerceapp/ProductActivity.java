package com.oum.e_commerceapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oum.e_commerceapp.adapter.CategoryAdapter;
import com.oum.e_commerceapp.adapter.ProductAdapter;
import com.oum.e_commerceapp.modal.ProductDomain;

import java.util.ArrayList;

import static android.view.View.inflate;

public class ProductActivity extends Activity {

    GridView gridView;
    TextView txtItemCount;
    int itemCount;
    ArrayList<ProductDomain> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productList = new ArrayList<>();

        gridView = findViewById(R.id.grid_product);

        int position = getIntent().getIntExtra("position", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.description_layout, null));
        builder.create();

        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
        ProductDomain productDomain;
        switch (position) {
            case 0:
                String[] clothList = {"SHIRTS", "JEANS", "BLOUSE"};
                int[] clothImageList = {R.drawable.shirt, R.drawable.jeans, R.drawable.blouse};
                String[] clothPriceList = {"RM 50", "RM 90", "RM 30"};


                for (int i = 0; i < clothList.length; i++) {

                    productDomain = new ProductDomain(clothList[i], clothPriceList[i], clothImageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));


                break;

            case 1:
                String[] electronicsList = {"RADIO", "DRYER", "WALKMAN", "FAN"};
                int[] electronicsImageList = {R.drawable.radio, R.drawable.dryer, R.drawable.walkman, R.drawable.fan};
                String[] electronicsPriceList = {"RM 150", "RM 298", "RM 199", "RM 300"};

                for (int i = 0; i < electronicsList.length; i++) {

                    productDomain = new ProductDomain(electronicsList[i], electronicsPriceList[i], electronicsImageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 2:
                String[] softwareList = {"ADOBE PHOTOSHOP", "SECURITY", "WINDOWS"};
                int[] softwareImageList = {R.drawable.photoshop, R.drawable.security, R.drawable.windows};
                String[] softwarePriceList = {"RM 130", "RM 99", "RM 350"};

                for (int i = 0; i < softwareList.length; i++) {

                    productDomain = new ProductDomain(softwareList[i], softwarePriceList[i], softwareImageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 3:
                String[] cellphonesList = {"SAMSUNG", "LG", "PIXEL", "SAMSUNG", "LG", "PIXEL", "SAMSUNG"};
                int[] cellphonesImageList = {R.drawable.samsung_galaxy, R.drawable.lg_g, R.drawable.pixel, R.drawable.samsung_j, R.drawable.lg_g, R.drawable.pixel, R.drawable.samsung_j};
                String[] cellphonesPriceList = {"RM 200", "RM 250", "RM 100", "RM 300", "RM 250", "RM 200", "RM 300"};

                for (int i = 0; i < cellphonesList.length; i++) {

                    productDomain = new ProductDomain(cellphonesList[i], cellphonesPriceList[i], cellphonesImageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 4:
                String[] automobilesList = {"HONDA", "TOYOTA", "PROTON", "HYUNDAI"};
                int[] automobilesImageList = {R.drawable.honda, R.drawable.toyota, R.drawable.proton, R.drawable.hyundai};
                String[] automobilesPriceList = {"RM 40k", "RM 90k", "RM 30k", "rm 90k"};

                for (int i = 0; i < automobilesList.length; i++) {

                    productDomain = new ProductDomain(automobilesList[i], automobilesPriceList[i], automobilesImageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
                productDetails(productList.get(i).getProductName(), productList.get(i).getProductPrice(), productList.get(i).getImageId(), i);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.cart_menu, menu);

            final View notifications = menu.findItem(R.id.cart_item).getActionView();

            txtItemCount = (TextView) notifications.findViewById(R.id.cart_badge);
            updateHotCount(itemCount++);
            //txtItemCount.setOnClickListener

            return true;
        }

/*
    @Override
    public boolean onOptionItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_item:
                // newGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void productDetails(String productName, String productPrice, int imgId, final int position) {
        final AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // get the layout inflater
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.description_layout, null);

        builder.setView(view);
        alert = builder.create();
        alert.show();

        TextView txtProduct = view.findViewById(R.id.txt_product_name);
        TextView txtPrice = view.findViewById(R.id.txt_price);
        ImageView imageView = view.findViewById(R.id.img_product);
        Button btnCart = view.findViewById(R.id.button);

        txtProduct.setText(productName);
        txtPrice.setText(productPrice);
        imageView.setImageResource(imgId);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                cartArray.add(productDomainArrayList.get(position));
                itemCount++;
                updateHotCount(itemCount);
                alert.dismiss();
            }
        });


    }





        public void updateHotCount(final int new_number) {
            itemCount = new_number;
            if (itemCount < 0) return;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (itemCount == 0)
                        txtItemCount.setVisibility(View.GONE);
                    else {
                        txtItemCount.setVisibility(View.VISIBLE);
                        txtItemCount.setText(Integer.toString(itemCount));


                    }
                }
            });
    }
}


