import java.util.*;

// INPUTS
/*
26
a b c d e f g h i j k l m n o p q r s t u v w x y z
2
1
1
2
1 to a -> 2
1 to b -> 2
1 to c -> 2
1 to d -> 2
1 to e -> 2
1 to f -> 2
1 to g -> 2
1 to h -> 2
1 to i -> 2
1 to j -> 2
1 to k -> 2
1 to l -> 2
1 to m -> 2
1 to n -> 2
1 to o -> 2
1 to p -> 2
1 to q -> 2
1 to r -> 2
1 to s -> 2
1 to t -> 2
1 to u -> 2
1 to v -> 2
1 to w -> 2
1 to x -> 2
1 to y -> 2
1 to z -> 2
2 to a -> 2
2 to b -> 2
2 to c -> 2
2 to d -> 2
2 to e -> 2
2 to f -> 2
2 to g -> 2
2 to h -> 2
2 to i -> 2
2 to j -> 2
2 to k -> 2
2 to l -> 2
2 to m -> 2
2 to n -> 2
2 to o -> 2
2 to p -> 2
2 to q -> 2
2 to r -> 2
2 to s -> 2
2 to t -> 2
2 to u -> 2
2 to v -> 2
2 to w -> 2
2 to x -> 2
2 to y -> 2
2 to z -> 2
abcdefghijklmnopqrstuvwxyz


// TEST CASE 2
2
0 1
5
1
1
4
1 0 2
1 1 4
2 1 3
2 0 5
3 0 5
3 1 4
4 1 4
4 0 2
5 0 5
5 1 5

*/

class practical2 {
    static class State {
        int fromState;
        char symbol;
        int toState;

        public State(int fromState, char symbol, int toState) {
            this.fromState = fromState;
            this.symbol = symbol;
            this.toState = toState;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of input symbols: ");
        int numSymbols = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Input symbols : ");
        String[] symbols = scanner.nextLine().split(" ");
        Set<Character> inputSymbols = new HashSet<>();
        for (String symbol : symbols) {
            inputSymbols.add(symbol.charAt(0));
        }

        System.out.print("Enter number of states: ");
        int numStates = scanner.nextInt();

        System.out.print("Initial state: ");
        int initialState = scanner.nextInt();

        System.out.print("Number of accepting states: ");
        int numAcceptStates = scanner.nextInt();

        System.out.print("Accepting states : ");
        Set<Integer> acceptingStates = new HashSet<>();
        
        for (int i = 0; i < numAcceptStates; i++) {
            acceptingStates.add(scanner.nextInt());
        }

        System.out.println("State table: ");
        ArrayList<State> States = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < numStates * inputSymbols.size(); i++) {
        // for (int i = 0; i < 11; i++) {
            String[] parts = scanner.nextLine().split(" ");
            // String line = scanner.nextLine();
            int from = Integer.parseInt(parts[0]);
            // int from = Integer.parseInt(Character.toString(line.charAt(0)));
            char symbol = parts[1].charAt(0);
            // Character symbol = line.charAt(5);
            int to = Integer.parseInt(parts[2]);
            // int to = Integer.parseInt(Character.toString(line.charAt(10)));
            States.add(new State(from, symbol, to));
        }

        System.out.print("Input string: ");
        String inputString = scanner.nextLine();

        boolean isValid = validateString(inputSymbols, initialState, acceptingStates, States, inputString);

        if(isValid) {  
            System.out.println("Valid String");
        }
        else{
            System.out.println("Invalid String");
        }

    }

    private static boolean validateString(Set<Character> inputSymbols, int initialState, Set<Integer> acceptingStates,List<State> States, String inputString) {
        int currentState = initialState;
        Map<Integer, Map<Character, Integer>> StateMap = buildStateMap(States);

        for (char ch : inputString.toCharArray()) {
            if (!inputSymbols.contains(ch)) {
                return false;
            }
            Map<Character, Integer> stateStates = StateMap.getOrDefault(currentState, new HashMap<>());
            if (!stateStates.containsKey(ch)) {
                return false;
            }
            currentState = stateStates.get(ch);
        }

        return acceptingStates.contains(currentState);
    }

    private static Map<Integer, Map<Character, Integer>> buildStateMap(List<State> States) {
        Map<Integer, Map<Character, Integer>> StateMap = new HashMap<>();

        for (State State : States) {
            StateMap.putIfAbsent(State.fromState, new HashMap<>());
            StateMap.get(State.fromState).put(State.symbol, State.toState);
        }

        return StateMap;
    }
}
