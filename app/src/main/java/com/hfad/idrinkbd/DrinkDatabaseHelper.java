package com.hfad.idrinkbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DrinkDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "drinkdatabase";
    private static final int DB_VERSION = 1;

    DrinkDatabaseHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db, oldVersion, newVersion);

    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(db,
                    "Swimming Pool",
                    "Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
                    R.drawable.i1);
            insertDrink(db,
                    "Cosmopolitan",
                    "Chill a cocktail glass with ice and pour it out when you are ready to pour the ingredients in the glass. Pour the cointreau, cranberry juice, lime juice, and lemon vodka into a cocktail shaker filled with ice. Shake well, and strain in large, cocktail glass. Garnish with lemon slice or lime wedge.",
                    R.drawable.i2);
            insertDrink(db,
                    "Apple Martini",
                    "Chill the glass with fresh ice and pour it out when ready to pour the ingredients in the glass. Pour the vodka, orange liqueur and apple schnapps into mixing glass with ice cubes. Stir well, and strain in chilled cocktail glass. Garnish with a slice of apple.",
                    R.drawable.i3);
            insertDrink(db,
                    "Blue Lady",
                    "Chill the glass with fresh ice and pour it out when ready to pour the ingredients in the glass. Pour the gin, lemon juice and blue cura?ao in a shaker with ice. Shake well, and strain in chilled cocktail glass. We recommend adding sugar to the rim of the glass.",
                    R.drawable.i4);
            insertDrink(db,
                    "Pina Colada",
                    "Pour the creme de coconut, pineapple juice and white rum into blender and blend until smooth, or into a shaker with crushed ice and shake well. Pour into chilled glass and top with soda water. Garnish with pineapple wedge and/or a maraschino cherry.",
                    R.drawable.i5);

           /* db.execSQL("CREATE TABLE INGREDIENT (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "INGR TEXT);");
            String[] ingrName = {"Vodka", "Cream", "Cream of coconut", "Curañao, blue", "Pineapple Juice", "Kahlãa", "Midori Melon Liqueur", "Tequila", "Lemon Juice","Cointreau"};
            for (String anIngrName : ingrName) {
                insertIngredient(db, anIngrName);
            }*/


            db.execSQL("CREATE TABLE DRINKINGR (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "DRINK_ID INTEGER,"
                    + "INGRNAME TEXT,"
                    + "INGRLIST TEXT);");
            /*db.execSQL("CREATE TABLE DRINKINGR (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "DRINK_ID INTEGER,"
                    + "INGR_ID INTEGER,"
                    + "AMOUNT REAL,"
                    + "FOREIGN KEY(DRINK_ID) REFERENCES DRINK(_id),"
                    + "FOREIGN KEY(INGR_ID) REFERENCES INGREDIENT(_id));");*/
            insertDrinkIngr(db, 1, "Vodka", "Vodka 3.75 cl");
            insertDrinkIngr(db, 1, "Cream", "Cream 2.25 cl");
            insertDrinkIngr(db, 1, "Cream of coconut", "Cream of coconut 2.25 cl");
            insertDrinkIngr(db, 1, "Curacao", "Curacao, blue 2.25 cl");
            insertDrinkIngr(db, 1, "Pineapple Juice", "Pineapple Juice 9.75 cl");
            insertDrinkIngr(db, 2, "Cranberry Juice", "Cranberry Juice 3 cl");
            insertDrinkIngr(db, 2, "Lime Juice", "Lime Juice 1.5 cl");
            insertDrinkIngr(db, 2, "Cointreau", "Cointreau 1.5 cl");
            insertDrinkIngr(db, 2, "Vodka", "Vodka, lemon 3.75 cl");
            insertDrinkIngr(db, 3, "Vodka", "Vodka 4.5 cl");
            insertDrinkIngr(db, 3, "Cointreau", "Cointreau 1.5 cl");
            insertDrinkIngr(db, 3, "Liqueur", "Liqueur, apple 1.5 cl");
            insertDrinkIngr(db, 4, "Gin", "Gin 3 cl");
            insertDrinkIngr(db, 4, "Curacao", "Curacao, blue 1.5 cl");
            insertDrinkIngr(db, 4, "Lemon Juice", "Lemon Juice 3 cl");
            insertDrinkIngr(db, 5, "Creme de Coconut", "Creme de Coconut 3 cl");
            insertDrinkIngr(db, 5, "Pineapple Juice", "Pineapple Juice 9 cl");
            insertDrinkIngr(db, 5, "Rum", "Rum, white light 3 cl");


        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");

        }
    }

    private static void insertDrinkIngr(SQLiteDatabase db, int drink_id, String ingrName, String ingrList){
        ContentValues drinkingrvalues = new ContentValues();
        drinkingrvalues.put("DRINK_ID", drink_id);
        drinkingrvalues.put("INGRNAME", ingrName);
        drinkingrvalues.put("INGRLIST", ingrList);
        db.insert("DRINKINGR", null, drinkingrvalues);
    }

   /* private static void insertIngredient(SQLiteDatabase db, String ingredient){
        ContentValues ingrvalues = new ContentValues();
        ingrvalues.put("INGR", ingredient);
        db.insert("INGREDIENT", null, ingrvalues);
    }*/

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues drinkvalues = new ContentValues();
        drinkvalues.put("NAME", name);
        drinkvalues.put("DESCRIPTION", description);
        drinkvalues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("DRINK", null, drinkvalues);
    }

}
