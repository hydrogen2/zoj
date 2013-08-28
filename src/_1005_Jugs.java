import java.util.Scanner;

public class _1005_Jugs {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int a;
		int b;
		int n;
		final int MAX_STATES_COUNT = 1000;
		int states_a[] = new int[MAX_STATES_COUNT];
		int states_b[] = new int[MAX_STATES_COUNT];
		int states_prev[] = new int[MAX_STATES_COUNT];
		int states_action[] = new int[MAX_STATES_COUNT];
		
		int new_state_a;
		int new_state_b;
		
		int stack[] = new int [MAX_STATES_COUNT];
		int stackp;
		
		final String[] ACTIONS = { "fill A", "fill B", "empty A", "empty B", "pour A B", "pour B A" };
		
		while (in.hasNextInt()) {
			a = in.nextInt();
			b = in.nextInt();
			n = in.nextInt();
			
			int i = 0;
			int j = 1;
			int k;
			int l;
			states_a[0] = 0;
			states_b[0] = 0;
			
			while (true) {
				
				if (states_b[i] == n) {
					stackp = 0;
					while (i != 0) {
						stack[stackp++] = i;
						i = states_prev[i];
					}
					while (stackp > 0) {
						System.out.println(ACTIONS[states_action[stack[--stackp]]]);
					}
					System.out.println("success");
					break;
				}
				
				for (k = 0; k < 6; ++k) {
					
					new_state_a = states_a[i];
					new_state_b = states_b[i];
					
					switch (k) {
					case 0:
						if (states_a[i] < a) {
							new_state_a = a;
						}
						break;
					case 1:
						if (states_b[i] < b) {
							new_state_b = b;
						}
						break;
					case 2:
						if (states_a[i] > 0) {
							new_state_a = 0;
						}
						break;
					case 3:
						if (states_b[i] > 0) {
							new_state_b = 0;
						}
						break;
					case 4:
						if (states_a[i] > 0 && states_b[i] < b) {
							if (states_a[i] > (b - states_b[i])) {
								new_state_a = states_a[i] - (b - states_b[i]);
								new_state_b = b;
							} else {
								new_state_a = 0;
								new_state_b = states_a[i] + states_b[i];
							}
						}
						break;
					case 5:
						if (states_b[i] > 0 && states_a[i] < a) {
							if (states_b[i] > (a - states_a[i])) {
								new_state_a = a;
								new_state_b = states_b[i] - (a - states_a[i]);
							} else {
								new_state_a = states_a[i] + states_b[i];
								new_state_b = 0;
							}
						}
						break;
					}
					
					for (l = i; l >= 0; --l) {
						if (states_a[l] == new_state_a &&
							states_b[l] == new_state_b) {
							break;
						}
					}
					
					if (l == -1) {
						states_a[j] = new_state_a;
						states_b[j] = new_state_b;
						states_prev[j] = i;
						states_action[j] = k;
						++j;
					}
				}
				++i;
			}
			
		}

	}

}
