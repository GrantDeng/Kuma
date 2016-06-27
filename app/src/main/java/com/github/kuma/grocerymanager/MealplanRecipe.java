package com.github.kuma.grocerymanager;

public class MealplanRecipe
{
    private int dayNum;
    private String planType;    // "breakfast" "lunch" "dinner"
    private String recipeName;

    public MealplanRecipe(int num, String type, String name)
    {
        dayNum = num;
        planType = type;
        name = recipeName;
    }

    public int getDayNum()  {return dayNum;}
    public String getPlanType() {return planType;}
    public String getRecipeName() {return  recipeName;}
}
