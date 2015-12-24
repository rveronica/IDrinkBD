package com.hfad.idrinkbd;

/**
 * Created by Veronica on 21.12.2015.
 */
public class Drink {
    private String name;
    private String[] ingredients;
    private float [] amount;
    private String description;
    private int imageResourceId;

    //each Drink has name, list of ingredients, description, imageID
    private Drink(String name, String[]ingredients,float[]amount, String description, int imageResourceId){
        this.name = name;
        this.ingredients = ingredients;
        this.amount = amount;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    //drinks is an array of Drinks
    public static final Drink[] drinks = {
            new Drink("Swimming Pool", new String[]{"Vodka","Cream","Cream of coconut","Cura?ao, blue","Pineapple Juice"},
            new float[]{3,2,2,2,9},"Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
            R.drawable.i1),
            new Drink("Cosmopolitan", new String[]{"Vodka","Cream","Cream of coconut","Cura?ao, blue","Pineapple Juice"},
                    new float[]{3.50f,2.70f,1.64f,2.65f,2.15f,9.50f},"Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
                    R.drawable.i2),
            new Drink("Apple Martini", new String[]{"Vodka","Cream","Cream of coconut","Cura?ao, blue","Pineapple Juice"},
                    new float[]{3.50f,2.70f,1.64f,2.65f,2.15f,9.50f},"Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
                    R.drawable.i3),
            new Drink("Blue Lady", new String[]{"Vodka","Cream","Cream of coconut","Cura?ao, blue","Pineapple Juice"},
                    new float[]{3.50f,2.70f,1.64f,2.65f,2.15f,9.50f},"Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
                    R.drawable.i4),
            new Drink("Shit On Grass", new String[]{"Vodka","Cream","Cream of coconut","Cura?ao, blue","Pineapple Juice"},
                    new float[]{3.50f,2.70f,1.64f,2.65f,2.15f,9.50f},"Chill a hurricane glass with ice and pour it out when you are ready to pour the ingredients in the glass.Pour pineapple juice, vodka, cream and cream of coconut with ice into a blender.Blend until smooth.Pour into a Hurricane glass.Float the blue curacao on top and serve it.Add a straw and garish with a slice of pineapple and a cherry.",
                    R.drawable.i5),
    };

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }
    public String getIngredientsItem(int item) {
        return ingredients[item];
    }
    public int getIngedientsLength(){
        return ingredients.length;
    }

    public float[] getAmount() {
        return amount;
    }
    public float getAmountItem(int item) {
        return amount[item];
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
