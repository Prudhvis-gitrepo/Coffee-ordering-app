package com.example.android.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/




import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view) {
        if(numberOfCoffees > 1){
            Context context = getApplicationContext();
            CharSequence text = "You cannot order below 1 coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        else {
            numberOfCoffees=numberOfCoffees - 1;
            displayquantity(numberOfCoffees);
        }}



    public void increment(View view) {
        if(numberOfCoffees < 101){
             numberOfCoffees=numberOfCoffees + 1;
             displayquantity(numberOfCoffees); }
        else {Context context = getApplicationContext();
            CharSequence text = "You cannot order above 100 coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();}
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText txtname = (EditText) findViewById(R.id.name_editText);
        String name = txtname.getText().toString();

        int total = calculatePrice(numberOfCoffees ,hasWhippedCream, hasChocolate);

        String subject = "justjava order for " + name;
        String ordersummary = createOrderSummary(total, hasWhippedCream, hasChocolate,name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,ordersummary );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

      displayMessage(ordersummary);
    }


    /*
    * creates summary of the order
    *
    * @param total is the total price
    * @return summary of the order
    * */
    public String createOrderSummary(int total,boolean addCream,boolean addChocolate,String name){
        String orderSummary ="Name: " + name;
                orderSummary += "\nAdd whipped cream?" + addCream ;
                 orderSummary += "\nAdd whipped cream?" + addChocolate ;
                orderSummary = orderSummary + "\nQuantity: " + numberOfCoffees ;
                orderSummary = orderSummary + "\nTotal: $" + total ;
                orderSummary = orderSummary + "\nThank You!" ;
        return orderSummary ;
    }
    /**
     * Calculates the price of the order.
     *
     * @return total price
     * @param hasChocolate tells need of chocolate
     * @param hasWhippedCream tells need of whippedcream
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity,boolean hasWhippedCream,boolean hasChocolate) {
        int pricePerCup = 5;

       if(hasWhippedCream)
        { pricePerCup += 1; }

         if(hasChocolate)
        { pricePerCup += 2; }

        int total = quantity * pricePerCup ;
            return total;
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }/**
     * This method displays the given price on the screen.
     */
    public boolean addCream(View view){
       boolean addCream = true;
               return addCream;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
