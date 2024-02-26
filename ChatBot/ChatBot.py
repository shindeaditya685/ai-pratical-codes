print("Welcome to Pizza  Ordering ChatBot!!!!")

userInput = str(input("User> ")).lower()

if (userInput == "hi" or userInput == "hello"):
    print("Bot> Hello! sir, How can i help you?")
    userInput = str(input("User> ")).lower()

if (userInput == "i want to order pizza"):
    print("Bot> Which pizza do you want to order?\n")
    print("1. Chicago Pizza\n2. Pepperoni Pizza\n3. Hawaiian Pizza\n4. California Style Pizza")
    print("**Enter which number pizza do you want? (Note: just enter the numeric value)**")
    userInput = str(input("User> ")).lower()

if (userInput == "1" or userInput == "2" or userInput == "3" or userInput == "4"):
    print("Bot> Please tell size of pizza (S, M, L, XL)")
    userInput = str(input("User> ")).lower()

    if (userInput == "s" or userInput == "m" or userInput == "l" or userInput == "xl"):
        print("Do you want extra toppings? (y/n)")
        userInput = str(input("User> ")).lower()

        if (userInput == "yes" or userInput == "y"):
            print("Please select from below (Note: just enter the numeric value): ")
            print("1. Meats\n2. Vegetables\n3. Cheeses")
            userInput = str(input("User> ")).lower()

            if (userInput == "1" or userInput == "2" or userInput == "3"):
                print("Bill : $100")
                print("Thank you for ordering the pizza!")
                userInput = str(input("User> ")).lower()

                if (userInput == "bye"):
                    print("Bye!!!!!!")
                    system.exit()

if (userInput == "bill" or userInput == "pls give bill" or userInput == "generate bill"):
    print("Bill : $100")


