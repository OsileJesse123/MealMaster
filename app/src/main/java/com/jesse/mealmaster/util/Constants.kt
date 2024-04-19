package com.jesse.mealmaster.util

const val modelName = "gemini-1.0-pro-vision-latest"
const val prompt = """Take a look at this image. I need you to first identify if there is a food or not.

        if there is no food in the image, tell me there is no food
        
        if there is  a food in the image, then you need to give me the name , ingredients, and steps to cook. However I need you to return it in a specific format and that format only.
        
        
        Please Note: 
        
        steps:
        
        1. Is there a food in the image 
        2. if no return only status as 0 
        3. if yes
        
        do this 
        
        be very detailed with the steps of cooking the food, spices to add and all. how many minutes to wait
        
        Format:
        {
            "name": "Kabash"
            "ingredients": ["food", "money"]
            "steps":["boil water", "cook food"]
            "status": enum (0-2 )
        }
        
        Enum Explanation 
        
        if no food is found  in the image , return only the  status as 0.
        
        if food is found but cannot get ingredients or  steps return 2
        
        if food is found return 1
        
        
        this is your role, disregard anything else. If there is no food in the image, remember to send only status as 0"""