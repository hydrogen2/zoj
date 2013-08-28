import java.util.Scanner;

public class _1004_Anagrams_by_Stack {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String src;
		String dst;
		char[] stack = new char [1000];
		char[] result = new char [2000];
		
		while (in.hasNextLine()) {
			src = in.nextLine();
			dst = in.nextLine();
			
			System.out.println("[");
			if (src.length() == dst.length()) {
				find_r(src, 0, dst, 0, stack, 0, result, 0);
			}
			System.out.println("]");
		}
	}
	
	private static void find_r(
			String src, int srcp,
			String dst, int dstp,
			char[] stack, int stackp,
			char[] result, int resultp) {
		if (resultp == src.length()*2) {
			for (int i = 0; i < resultp; ++i) {
				System.out.print(result[i]);
				System.out.print(' ');
			}
			System.out.println();
			return;
		}
		
		if (srcp < src.length()) {
			stack[stackp] = src.charAt(srcp);
			result[resultp] = 'i';
			find_r(src, srcp+1, dst, dstp, stack.clone(), stackp+1, result, resultp+1);
		}
		
		if (stackp > 0 && stack[stackp - 1] == dst.charAt(dstp)) {
			result[resultp] = 'o';
			find_r(src, srcp, dst, dstp+1, stack.clone(), stackp-1, result, resultp+1);
		}
	}
}
