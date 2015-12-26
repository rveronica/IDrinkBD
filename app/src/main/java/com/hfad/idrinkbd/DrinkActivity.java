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
        import android.widget.CheckBox;
        import android.widget.CursorAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

public class DrinkActivity extends Activity  {

    public static final String EXTRA_DRINKNO = "drinkNo";

    final String LOG_TAG = "myLogs";
    ListView listView;
    Cursor cursorDrink;
    Cursor cursorDrinkIngr;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get drink number from intent
        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);

        //create a cursorDrink
        try {
            SQLiteOpenHelper drinkDatabaseHelper = new DrinkDatabaseHelper(this);
            db = drinkDatabaseHelper.getReadableDatabase();
            cursorDrink = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID","FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);

            // move to rhe first record in the Cursor - it will have our drinkNo id
            if (cursorDrink.moveToFirst()) {

                //Get the drink details from the cursorDrink
                String nameText = cursorDrink.getString(0);
                String descriptionText = cursorDrink.getString(1);
                int photoId = cursorDrink.getInt(2);
                boolean isFavorite = (cursorDrink.getInt(3) == 1);

                //populate the drink image, favorite sign, name
               /* ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);*/

                CheckBox favorite =(CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                //footer populate the drink descriptions to the end of ingredients list
                View footer = getLayoutInflater().inflate(R.layout.footer_activity_drink, null);
                ((TextView) footer.findViewById(R.id.description)).setText(descriptionText);

                listView = (ListView) findViewById(R.id.list_ingredients);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.addFooterView(footer);

                /*String sqlquery = "SELECT INGREDIENT.INGR, DRINKINGR.AMOUNT "
                    + " FROM DRINKINGR,INGREDIENT,DRINK "
                    + " WHERE (DRINK._id=?) AND (DRINK._id=DRINKINGR.DRINK_ID) AND (DRINKINGR.INGR_ID=INGREDIENT._id);";
*/
                cursorDrinkIngr = db.query("DRINKINGR",
                        new String[]{"_id", "INGR_ID", "INGRLIST"},
                        "DRINK_ID = ?",
                        new String[]{Integer.toString(drinkNo)},
                        null, null, null);
                CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                        R.layout.my_item,
                        cursorDrinkIngr,
                        new String[]{"INGRLIST"},
                        new int[]{R.id.textMy},
                        0);
                listView.setAdapter(listAdapter);
            }
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavalable", Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    public void onFavoriteClicked (View view) {
        //Get drink number from intent
        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
        CheckBox favorite =(CheckBox) findViewById(R.id.favorite);

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());

        SQLiteOpenHelper drinkDatabaseHelper = new DrinkDatabaseHelper(DrinkActivity.this);
        try {
            db = drinkDatabaseHelper.getWritableDatabase();
            db.update("DRINK", drinkValues,"_id = ?", new String[] {Integer.toString(drinkNo)});
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavalable", Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    public void onClickButton (View v) {
        switch (v.getId()) {
            case R.id.btnShoppingList:
                addShoppingIngredient();
                break;
            case R.id.btnSearchByIngredients:
                //some code
                break;
        }
    }

    private void addShoppingIngredient(){

        SQLiteOpenHelper drinkDatabaseHelper = new DrinkDatabaseHelper(DrinkActivity.this);
        try {
            db = drinkDatabaseHelper.getWritableDatabase();
            SparseBooleanArray sbArray = listView.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    cursorDrinkIngr.moveToPosition(key);
                    int idShopping = cursorDrinkIngr.getInt(1);

                    Log.d(LOG_TAG, String.valueOf(idShopping));

                    ContentValues ingredValues = new ContentValues();
                    ingredValues.put("SHOPLIST", true);
                    db.update("INGREDIENT", ingredValues, "_id = ?", new String[]{Integer.toString(idShopping)});
                }
            }
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavalable", Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        cursorDrinkIngr.close();
        cursorDrink.close();
        db.close();
    }
}

