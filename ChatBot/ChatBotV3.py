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

def generate_bill(pizzas):
    total_price = 0
    print("\nOrdered Pizzas:")
    for pizza in pizzas:
        print("\nPizza:", pizza['name'])
        print("Size:", pizza['size'])
        print("Base Price:", pizza['price'])
        total_price += pizza['price']
        if pizza['toppings']:
            print("Toppings:")
            for topping in pizza['toppings']:
                print("- ", topping['name'], ": $", topping['price'])
                total_price += topping['price']
    print("\nTotal Bill: $", total_price)

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

ordered_pizzas = []

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
                toppings.append({"name": topping_names[toppings_choice], "price": 0.75})
            pizza_price = 10  # Default pizza price
            ordered_pizzas.append({"name": pizza_names[pizza_choice], "size": pizza_size, "price": pizza_price, "toppings": toppings})
            print("Bot> Pizza added to your order!")
            print("Bot> Do you want to order another pizza? (yes/no)")
            another_pizza = input("User> ").lower()
            if another_pizza != "yes":
                break
    elif userInput == "generate bill":
        generate_bill(ordered_pizzas)
        print("Bot> Thank you for your order!")
        break
    elif userInput in ["bye", "exit"]:
        print("Bot> Bye!")
        break
    else:
        print("Bot> Sorry, I didn't understand that.")
