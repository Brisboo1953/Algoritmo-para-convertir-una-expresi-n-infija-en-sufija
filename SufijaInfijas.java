import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

    public class SufijaInfijas {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.print("\nExpresion (Escribe quit para salir): ");
                String exp = input.nextLine().toLowerCase();
                if (exp.equals("quit")) {
                    input.close();
                    break;
                }
                try {
                    String expresionPosfija = convertInfToPost(exp);
                    System.out.println("Expresion Posfija: " + expresionPosfija);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        public static String convertInfToPost(String expresionInfija) throws Exception {
            StringBuilder Posfija = new StringBuilder();
            Stack<Character> pila = new Stack<>();
            StringTokenizer Token = new StringTokenizer(expresionInfija, "()[}{}+-*/", true);
            while (Token.hasMoreTokens()) {
                String token = Token.nextToken();
                char car = token.charAt(0);
                if (car == '(') {
                    pila.push(car);
                } else if (car == ')') {
                    while (!pila.isEmpty() && pila.peek() != '(') {
                        Posfija.append(pila.pop()).append(" ");
                    }
                    if (pila.isEmpty() || pila.peek() != '(') {
                        throw new Exception("Paréntesis desequilibrados");
                    }
                    pila.pop();
                } else if (Character.isDigit(car) || Character.isLetter(car)) {
                    Posfija.append(token).append(" ");
                } else if (esOperador(car)) {
                    while (!pila.isEmpty() && pila.peek() != '(' && prioridad(car) <= prioridad(pila.peek())) {
                        Posfija.append(pila.pop()).append(" ");
                    }
                    pila.push(car);
                }
            }
            while (!pila.isEmpty()) {
                Posfija.append(pila.pop()).append(" ");
            }
            return Posfija.toString().trim();
        }
        public static boolean esOperador(char car) {
            return car == '+' || car== '-' || car == '*' || car == '/';
        }
        public static int prioridad(char op) {
            if (op == '+' || op == '-') {
                return 1;
            } else if (op == '*' || op == '/') {
                return 2;
            }
            return 0;
        }
    }

