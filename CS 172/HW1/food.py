# File Name:   food.py
# Purpose:     Class to represent a food item and calculate its
#              nutritional information. A food item has a name,
#              the grams of fat, carbs, and proteing per single
#              serving.
# Known facts: There are 9 calories per gram of fat
#              There are 4 calories per gram of carbs
#              There are 4 calories per gram of protein
# Author:      Adelaida Medlock
# Date:        January 5, 2024

class FoodItem:
    # constructor
    # fat, carbs, and protein given in grams (as float values)
    # serving size, given in grams or milliliters (as an integer value)
    def __init__(self, name, size, fat, carbs, protein):
        self.__name = name
        self.__servingSize = size
        self.__fat = fat
        self.__carbs = carbs
        self.__protein = protein
    
    # getters
    def getName(self):
        return self.__name
    
    def getServingSize(self):
        return self.__servingSize
    
    def getFat(self):
        return self.__fat
    
    def getCarbs(self):
        return self.__carbs
    
    def getProtein(self):
        return self.__protein
    
    # setters
    def changeName(self, name):
        self.__name = name
    
    # helper: calculate the calories in food item for 1 serving
    def calculateCalories(self, numServings):
        caloriesPerServing = (self.__fat * 9) + (self.__carbs * 4) + (self.__protein * 4)
        totalCalories = caloriesPerServing * numServings
        return totalCalories

