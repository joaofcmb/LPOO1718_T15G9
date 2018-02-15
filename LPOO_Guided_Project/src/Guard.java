import java.util.Arrays;

public class Guard {
	public enum Direction{ UP, DOWN, LEFT, RIGHT }

	public static Direction guardDirection = Direction.LEFT;
	static int[] guardPos = {1,8};

	public static void move() {
		switch(Arrays.toString(guardPos)) {
		case "[1][8]":
		case "[5][7]":guardDirection = Direction.LEFT;

		case "[1][7]":
		case "[5][1]":guardDirection = Direction.DOWN;

		case "[6][1]":guardDirection = Direction.RIGHT;
		case "[6][8]":guardDirection = Direction.UP;
		}
	}
}


