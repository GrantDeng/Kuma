package com.github.kuma.grocerymanager;

public class MealplanRecipe
{
    private int dayNum;
    private int planType;    // 1:"breakfast" 2:"lunch" 3:"dinner"
    private String recipeName;

    public MealplanRecipe(int num, int type, String name)
    {
        dayNum = num;
        planType = type;
        name = recipeName;
    }

    public int getDayNum()  {return dayNum;}
    public int getPlanType() {return planType;}
    public String getRecipeName() {return  recipeName;}
}
