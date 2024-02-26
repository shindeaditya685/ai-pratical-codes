def get_pizza_choice():
    print("Bot> Which pizza do you want to order?\n")
    print("1. Chicago Pizza\n2. Pepperoni Pizza\n3. Hawaiian Pizza\n4. California Style Pizza")
    print("5. Margherita Pizza\n6. Meat Lover's Pizza\n7. Veggie Delight Pizza\n8. BBQ Chicken Pizza")
    print("**Enter the number corresponding to the pizza you want to order**")
    return int(input("User> "))

def get_pizza_size():
    print("Bot> Please tell size of pizza (S, M, L, XL)")
    return input("User> ").lower()

def get_extra_toppings():
    print("Do you want extra toppings? (y/n)")
    return input("User> ").lower() == "y"

def get_toppings_choice():
    print("Please select topping category (Enter the number): ")
    print("1. Meats\n2. Vegetables\n3. Cheeses\n4. Sauces")
    return int(input("User> "))

def generate_bill(pizza, size, toppings):
    pizza_prices = {
        1: 10, 2: 12, 3: 11, 4: 14, 5: 9, 6: 13, 7: 10, 8: 15
    }
    topping_prices = {
        1: 2, 2: 1.5, 3: 1, 4: 0.75
    }
    total_price = pizza_prices[pizza]

    print("\nOrdered Pizza:", pizza_names[pizza])
    print("Size:", size)
    print("Base Price:", total_price)

    if toppings:
        total_topping_price = 0
        for topping in toppings:
            total_topping_price += topping_prices[topping]
            print("Topping:", topping_names[topping])
        print("Total Topping Price:", total_topping_price)
        total_price += total_topping_price

    print("Total Bill: $", total_price)
    print("---------------------------------")
    return total_price

pizza_names = {
    1: "Chicago Pizza",
    2: "Pepperoni Pizza",
    3: "Hawaiian Pizza",
    4: "California Style Pizza",
    5: "Margherita Pizza",
    6: "Meat Lover's Pizza",
    7: "Veggie Delight Pizza",
    8: "BBQ Chicken Pizza"
}

topping_names = {
    1: "Meats",
    2: "Vegetables",
    3: "Cheeses",
    4: "Sauces"
}

print("Welcome to Pizza Ordering ChatBot!!!!")

total_bill = 0

while True:
    userInput = input("User> ").lower()

    if userInput in ["hi", "hello"]:
        print("Bot> Hello! How can I help you?")
    elif userInput == "order":
        while True:
            pizza_choice = get_pizza_choice()
            pizza_size = get_pizza_size()
            toppings = []
            while get_extra_toppings():
                toppings_choice = get_toppings_choice()
                toppings.append(toppings_choice)
            total_bill += generate_bill(pizza_choice, pizza_size, toppings)
            print("Bot> Do you want to order another pizza? (yes/no)")
            another_pizza = input("User> ").lower()
            if another_pizza != "yes":
                break
    elif userInput == "generate bill":
        print("Bot> Your Total Bill is: $", total_bill)
    elif userInput in ["bye", "exit"]:
        print("Bot> Bye!")
        break
    else:
        print("Bot> Sorry, I didn't understand that.")
