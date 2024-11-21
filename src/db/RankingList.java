package db;
import java.io.*;
public class RankingList {
	private int people = 10;
	private String[] rank = new String[people];
	private String file;
	private String[] player = new String[people];
	private int[] scores = new int[people];
	
	public RankingList(String txt) {
		file = txt;
		getRank();
	}
	
	private void getRank() {
		try( BufferedReader br = new BufferedReader(new FileReader(file))){
			for(int i = 0; i < rank.length; i++) {
				rank[i] = br.readLine();
				String h = rank[i]+".";
				String[] s = h.split(" ");
				scores[i] = Integer.parseInt(s[0]);
				player[i] = s[1];
			}
		} catch(IOException e) {
			System.err.println(e);
		}
	}

	public void enterRank(int s, String n) {
		int i = 0;
		while(s <= scores[i]) {
			i++;
		}
		String t = s+" "+n;
		int x = people-1;
		rank[people-1] = null;
		while(i!=x) {
			rank[x] = rank[x-1];
			x--;
		}
		rank[i] = t;
		try( BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(int j = 0; j < rank.length; j++){
				bw.write(rank[j]+"\n");
			}
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	public String[] PlayerName() {
		return player;
	}
	public int[] Scores() {
		return scores;
	}
	public String[] outputRank() {
		return rank;
	}
	public int getLowestScore() {
		return scores[people-1];
	}
}
