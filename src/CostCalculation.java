import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CostCalculation {
	static int cnt, n;
	static int max_count;
	static double max_cost;
	static double max_weight;
	static double[][] arr = new double[15][3];
	static int[] res = new int[15];
	static int[] temp_res = new int[15];
	static int[] vis = new int[15];
	
	static void combination(int level, int index, double cost, double rem_weight)
	{
		if(rem_weight >= 0 && cost > max_cost)
		{
			max_count = level;
			max_cost = cost;
			for(int i = 0; i < level; i++)
			{
				res[i] = temp_res[i];
			}
		}
		for(int i = index; i < n; i++)
		{
			if(vis[i] == 0 && rem_weight - arr[i][1] >=0)
			{
				vis[i] = 1;
				temp_res[level] = i;
				combination(level+1, i, cost + arr[i][2], rem_weight - arr[i][1]);
				vis[i] = 0;
			}
		}
		
	}
	public static void main(String[] args) throws Exception
	{
		Scanner s = new Scanner(new FileInputStream(".\\src\\input.txt"));
		if (s.hasNext());
		String inpt = s.nextLine();
		int i =0;
		int len = inpt.length();
		String temp = "";
		for(int j = 0; j < 15; j++)
		{
			vis[j] =0;
		}
		while (inpt.charAt(i) != ' ')
		{
			temp += inpt.charAt(i);
			i++;
		}
		
		max_weight = Integer.parseInt(temp);
		while ( i != len)
		{
			while(inpt.charAt(i) != '(')
			{
				i++;
			}
			i++;
			temp = "";
			while (inpt.charAt(i) != ',')
			{
				temp += inpt.charAt(i);
				i++;
			}
			i++;
			arr[n][0] = Integer.parseInt(temp);
			temp = "";
			while (inpt.charAt(i) != ',')
			{
				temp += inpt.charAt(i);
				i++;
			}
			i+=2;
			arr[n][1] = Double.parseDouble(temp);
			temp = "";
			while (inpt.charAt(i) != ')')
			{
				temp += inpt.charAt(i);
				i++;
			}
			arr[n][2] = Integer.parseInt(temp);
			temp = "";
			i++;
			n++;
		}
		combination(0, 0, 0, max_weight);
		if(max_count > 0)
		{
			for(int j = 0; j < max_count-1; j++)
			{
				System.out.print(res[j] + 1 + ",");
			}
			System.out.print(res[max_count - 1] + 1);
		}
		else
			System.out.print("-");
		s.close();
	}
}
