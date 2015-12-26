package com.hfad.idrinkbd;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Veronica on 26.12.2015.
 */
public class ShoppingActivity extends Activity{

    final String LOG_TAG = "myLogs";
    ListView listView;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        //create a cursorDrink
        try {
            SQLiteOpenHelper drinkDatabaseHelper = new DrinkDatabaseHelper(this);
            db = drinkDatabaseHelper.getReadableDatabase();

                listView = (ListView) findViewById(R.id.list_shopping);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                cursor = db.query("INGREDIENT",
                        new String[]{"_id", "INGREDNAME", "SHOPLIST"},
                        "SHOPLIST = 1",
                        null, null, null, null);
                CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                        R.layout.my_item,
                        cursor,
                        new String[]{"INGREDNAME"},
                        new int[]{R.id.textMy},
                        0);
                listView.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavalable", Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG, e.getMessage());
        }
    }



    public void onClickShoppingButton (View v) {
        switch (v.getId()) {
            case R.id.btn_shopping:

                break;
            case R.id.btn_clear:
                //some code
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        cursor.close();
        db.close();
    }
}

