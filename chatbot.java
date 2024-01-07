import java.util.Scanner;

public class chatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm a simple chatbot. Ask me something.");

        while (true) {
            
            String userInput = scanner.nextLine();

           
            String response = getBotResponse(userInput);

            
            System.out.println("Bot: " + response);

            
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bot: Goodbye! Have a great day.");
                break;
            }
        }

        
        scanner.close();
    }

    private static String getBotResponse(String userInput) {
       
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hi there! How can I help you?";
        } else if (userInput.contains("how are you")) {
            return "I'm just a computer program, but thanks for asking!";
        } else if (userInput.contains("bye")) {
            return "Goodbye!";
        } else {
            return "I'm not sure how to respond to that. Ask me something else.";
        }
    }
}