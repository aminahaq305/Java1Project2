import java.util.Scanner;
//0 1 4 2 6 0 1
//1 7 4 2 2 6 0
//0 0 0 8 1 4 1
//1 1 2 7 3 4 2
//5 1 6 4 2 2 1


//4, 6
//2 3 6 1 9 7
//3 1 0 2 3 8
//3 6 7 2 9 0
//1 4 9 5 4 3
//1, 2

public class mazerunner1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		//Ask user for number of rows
		System.out.print("How many rows are in the maze? ");
		int rows = in.nextInt();
		
		//Ask user for number of columns
		System.out.print("How many columns are in the maze? ");
		int columns = in.nextInt(); 
		
		//initialize some variables
		int[][] maze = new int[rows][columns];
		int totaldanger = 0;
		int north;
		int south;
		int west;
		int east;
		boolean edgereached = false;
		String visited = "";
		int dangerlevel;
		
		//close scanner
		in.nextLine(); 
		
		//iterate through arrays to create maze
		for (int i = 0; i < rows; i++) {
			String dangerstring = "";
			String[] dangernum = new String[columns];
			System.out.print("Enter the danger in row " + i + ", separated by spaces: ");
			dangerstring = in.nextLine();
			dangernum = dangerstring.split(" ");
			for (int j = 0; j < columns; j++) {
				maze[i][j] = Integer.parseInt(dangernum[j]);
		}
	}
		//Ask user for x coordinate
		System.out.print("Enter the starting x coordinate: ");
		int x = in.nextInt();
		
		//Ask user for y coordinate
		System.out.print("Enter the starting y coordinate: ");
		int y = in.nextInt();
		
		//display the maze with asterisk in place of the coordinate, until the player reaches the edge
		while (edgereached == false) {
			//use for loops to iterate through the maze and choose where to display the asterisk
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (i == x && j == y) {
						System.out.print("* ");
						totaldanger = totaldanger + maze[x][y];
						visited = visited + String.valueOf(x) + String.valueOf(y);
					} else {
						System.out.print(maze[i][j] + " ");
					}
					
				}
				System.out.println(" ");	
				
			}
			//change the boolean value of edgereached if the player has reached the edge
			if (x == 0 || y == 0 || y == (columns-1) || x == (rows-1)) {
				edgereached = true;
			}
			//if the player has reached the edge, output the appropriate prompts
			if (edgereached == true) {
				System.out.println("Exited the world at " + x + ", " + y);
				System.out.println("Total danger faced: " + totaldanger);
			} 
			//otherwise, check whether the next move should be to the north, south, east, or west
			else {
			north = maze[x-1][y];
			south = maze[x+1][y];
			east = maze[x][y+1];
			west = maze[x][y-1]; 
			//move to the position that hasn't been visited before
			//and is the smallest
			
			//if all have been visited except west, go to west
			if (visited(visited, (x-1),y) && visited(visited, (x+1),y) && visited(visited, x,(y+1)) && !visited(visited, x, (y-1))) {
				y = y - 1;
				dangerlevel = west;
			}
			
			//if all have been visited except east, go to east
			else if (visited(visited, (x-1),y) && visited(visited, (x+1),y) && visited(visited, x,(y-1)) && !visited(visited, x,(y+1))) {
				y = y + 1;
				dangerlevel = east;
			}			
			//if all have been visited except south, go to south
			else if (visited(visited, (x-1),y) && visited(visited, x,(y+1)) && visited(visited, x,(y-1)) && !visited(visited, (x+1),y)) {
				x = x+1;
				dangerlevel = south;
			}
			//if all have been visited except north, go to north
			else if (visited(visited, x,(y+1)) && visited(visited, x,(y-1)) && visited(visited, (x+1),y) && !visited(visited, (x-1),y)) {
				x = x-1;
				dangerlevel = north;
			}
			//if all have been visited except east and west, go to smallest
			else if (visited(visited, (x-1),y) && visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest3(east, west) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					y = y - 1;
					dangerlevel = west;
				}
			}
			//if all have been visited except east and south, go to smallest
			else if (visited(visited, (x-1),y) && !visited(visited, (x+1),y) && visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest3(east, south) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					x = x + 1;
					dangerlevel = south;
				}
			}
			//if all have been visited except east and north, go to smallest
			else if (!visited(visited, (x-1),y) && visited(visited, (x+1),y) && visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest3(east, north) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					x = x - 1;
					dangerlevel = north;
				}
			
			}
			//if all have been visited except south and west, go to smallest
			else if (visited(visited, (x-1),y) && !visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && visited(visited, x,(y+1))) {
				if (smallest3(west, south) == west) {
					y = y - 1;
					dangerlevel = west;
				}
				else {
					x = x + 1;
					dangerlevel = south;
				}
			}
			//if all have been visited except north and west, go to smallest
			else if (!visited(visited, (x-1),y) && visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && visited(visited, x,(y+1))) {
				if (smallest3(west, north) == west) {
					y = y - 1;
					dangerlevel = west;
				}
				else {
					x = x - 1;
					dangerlevel = north;
				}
			}
			//if all have been visited except south and north, go to smallest
			else if (!visited(visited, (x-1),y) && !visited(visited, (x+1),y) && visited(visited, x, (y-1)) && visited(visited, x,(y+1))) {
				if (smallest3(south, north) == south) {
					x = x + 1;
					dangerlevel = south;
				}
				else {
					x = x - 1;
					dangerlevel = north;
				}
			}
			//if only north has been visited, go to the smallest from remaining three
			else if (visited(visited, (x-1),y) && !visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest2(south, east, west) == south) {
					x = x + 1;
					dangerlevel = south;
				}
				else if (smallest2(south, east, west) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					y = y - 1;
					dangerlevel = west;
				}
			}
			//if only south has been visited, go to the smallest from remaining three
			else if (!visited(visited, (x-1),y) && visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest2(north, east, west) == north) {
					x = x - 1;
					dangerlevel = north;
				}
				else if (smallest2(north, east, west) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					y = y - 1;
					dangerlevel = west;
				}
			}
			//if only west has been visited, go to the smallest from remaining three
			else if (!visited(visited, (x-1),y) && !visited(visited, (x+1),y) && visited(visited, x, (y-1)) && !visited(visited, x,(y+1))) {
				if (smallest2(north, east, south) == north) {
					x = x - 1;
					dangerlevel = north;
				}
				else if (smallest2(south, east, north) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					x = x + 1;
					dangerlevel = south;
				}
			}
			//if only east has been visited, go to the smallest from remaining three
			else if (!visited(visited, (x-1),y) && !visited(visited, (x+1),y) && !visited(visited, x, (y-1)) && visited(visited, x,(y+1))) {
				if (smallest2(north, west, south) == north) {
					x = x - 1;
					dangerlevel = north;
				}
				else if (smallest2(south, west, north) == west) {
					y = y - 1;
					dangerlevel = west;
				}
				else {
					x = x + 1;
					dangerlevel = south;
				}
			}
			//if none have been visited, go to smallest from the four
			else {
				if (smallest(north, south, east, west) == north) {
					x = x - 1;
					dangerlevel = north;
				}
				else if (smallest(north, south, east, west) == south) {
					x = x + 1;
					dangerlevel = south;
				}
				else if (smallest(north, south, east, west) == east) {
					y = y + 1;
					dangerlevel = east;
				}
				else {
					y = y - 1;
					dangerlevel = west;
				}
			}
			//once the new position has been decided output the appropriate prompt 
			//and move to the beginning of the while loop
			//and restart until edgereached is true
			System.out.println("Moving to " + x + ", " + y + " (danger level " + dangerlevel + ")");
			System.out.println(" ");	
	}
		}
	in.close();
	
	}
	//this method checks whether the new position has been visited already or not
	public static boolean visited(String visited, int x, int y) {
		boolean visited1 = false;
		String place = String.valueOf(x) + String.valueOf(y);
		if (visited.contains(place)) {
					visited1 = true;
			}
		return(visited1);
	}
	//this method checks the smallest if none of the positions have been visited
	public static int smallest(int w, int x, int y, int z) {
		if (w <= x && w <= y && w <=z) {
			return(w);
		}
		else if (x <=w && x <=y && x<=z) {
			return(x);
		}
		else if (y <=w && y <=x && y<=z) {
			return(y);
		} else {
			return(z);
		}
	}
	//this method checks the smallest if only one of the positions has been visited
	public static int smallest2(int x, int y, int z) {
		if(x<=y && x<=z) {
			return(x);
		}
		else if(y<=x && y<=z) {
			return(y);
		}
		else {
			return(z);
		}
	}
	//this method checks the smallest if two of the positions have been visited
	public static int smallest3(int x, int y) {
		if (x<=y) {
			return(x);
		}
		else {
			return(y);
		}
	}
}
	
