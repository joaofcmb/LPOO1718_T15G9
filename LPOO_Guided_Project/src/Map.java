public class Map {
	public static void toString(char[][] map) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) 
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
	}
}