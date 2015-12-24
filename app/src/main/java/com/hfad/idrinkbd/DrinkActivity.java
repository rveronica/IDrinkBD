package com.hfad.idrinkbd;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

// добавила к классу интерфейс
public class DrinkActivity extends Activity implements View.OnClickListener {

   public static final String EXTRA_DRINKNO = "drinkNo";
    //добавила
    final String LOG_TAG = "myLogs";
    ListView listView;
    Drink drink;
    String[] names;
    Cursor cursor;
    Cursor myCursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get drink number from intent
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);

        //create a cursor
        try {
            SQLiteOpenHelper drinkDatabaseHelper = new DrinkDatabaseHelper(this);
            db = drinkDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);
            // move to rhe first record in the Cursor - it will have our drinkNo id
            if (cursor.moveToFirst()){
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                //populate the drink name, description, image
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                listView = (ListView)findViewById(R.id.list_ingredients);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                /*String sqlquery = "SELECT INGREDIENT.INGR, DRINKINGR.AMOUNT "
                    + " FROM DRINKINGR,INGREDIENT,DRINK "
                    + " WHERE (DRINK._id=?) AND (DRINK._id=DRINKINGR.DRINK_ID) AND (DRINKINGR.INGR_ID=INGREDIENT._id);";
*/
                myCursor = db.query("DRINKINGR",
                        new String[]{"_id","INGRLIST"},
                        "DRINK_ID = ?",
                        new String[]{Integer.toString(drinkNo)},
                        null, null, null);
                CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                        R.layout.my_item,
                        myCursor,
                        new String[] {"INGRLIST"},
                        new int[]{R.id.textMy},
                        0);
               /* CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                        android.R.layout.simple_list_item_multiple_choice,
                        cursor,
                        new String[] {"NAME"},
                        new int[]{android.R.id.text1},
                        0);*/
                listView.setAdapter(listAdapter);
                Button btnShoppingList = (Button) findViewById(R.id.btnShoppingList);
                btnShoppingList.setOnClickListener(this);
                Button btnSearchByIngredients = (Button) findViewById(R.id.btnSearchByIngredients);
                btnSearchByIngredients.setOnClickListener(this);

            }


        } catch(SQLiteException e){
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG, e.getMessage());
        }



        /*
        listView = (ListView)findViewById(R.id.list_ingredients);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] ingredientsValue = new String[drink.getIngedientsLength()];
        for (int i =0; i < ingredientsValue.length; i++){
            ingredientsValue[i] = drink.getIngredientsItem(i) + " " + Float.toString(drink.getAmountItem(i)) + " cl";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.my_item, ingredientsValue);
        listView.setAdapter(adapter);
*/
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        myCursor.close();
        cursor.close();
        db.close();
    }

    // это для button и в буущем для списка покупок и поиска коктейлей по ингредиентам
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnShoppingList:
                //будет код
                break;
            case R.id.btnSearchByIngredients:
                // будет код
                break;
        }
        // пишем в лог выделенные элементы
        Log.d(LOG_TAG, "checked: ");
        SparseBooleanArray sbArray = listView.getCheckedItemPositions();
        for (int i = 0; i < sbArray.size(); i++) {
            // какая позиция из списка была кликнута
            int key = sbArray.keyAt(i);

            // эта позиция выделена?
            if (sbArray.get(key)) {
                //Log.d(LOG_TAG, drink.getIngredientsItem(key));
                // помечаем в бд, что этот элемент в shopping list
            }
        }
    }
}
