# File Name:   main.py
# Purpose:     Script to create a FoodItem object, display its information,
#              and calculate the total calories consumed.
# Author:      Sam Turay Jr
# Date:        4/9/2024

from food import FoodItem
from inputroutines import intInRange, floatInRange

if __name__ == "__main__":
    # Get user input for food item's details
    print("Enter food item's name:", end=' ')
    name = input()
    print("Enter the serving size in grams (or mL for liquids):", end=' ')
    serving_size = intInRange(0, 500)
    print("Enter the grams of fat per serving:", end=' ')
    fat = floatInRange(0.00, 50.00)
    print("Enter the grams of carbs per serving:", end=' ')
    carbs = floatInRange(0.00, 50.00)
    print("Enter the grams of protein per serving:", end=' ')
    protein = floatInRange(0.00, 50.00)
    
    # Create FoodItem object
    food_item = FoodItem(name, serving_size, fat, carbs, protein)

    # Display nutritional information per serving
    print("\nNutritional information per serving of", food_item.getName())
    print("Serving Size:", food_item.getServingSize(), "grams / mL")
    print("Fat:", "{:.2f}".format(food_item.getFat()), "grams")
    print("Carbohydrates:", "{:.2f}".format(food_item.getCarbs()), "grams")
    print("Protein:", "{:.2f}".format(food_item.getProtein()), "grams")
    print("Number of calories for 1 serving:", "{:.2f}".format(food_item.calculateCalories(1)))
    print()
    # Get user input for number of servings consumed
    print("Enter the number of servings consumed:", end=' ')
    servings_consumed = intInRange(0, 10)
    # Calculate and display total number of calories consumed
    total_calories = food_item.calculateCalories(servings_consumed)
 
    print("Number of calories for", servings_consumed, "serving(s):", "{:.2f}".format(total_calories))
    