package com.pizza.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private char[][] pizza;
	private int R;
	private int C;
	private int I;
	private int H;

	public Pizza(String file) {
		read(file);
		System.out.println(file + ": " + this.width() + " x " + this.height());
	}

	private void read(String file) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(file));

			String[] values = lines.get(0).split(" ");
			R = Integer.parseInt(values[0]);
			C = Integer.parseInt(values[1]);
			I = Integer.parseInt(values[2]);
			H = Integer.parseInt(values[3]);
			pizza = new char[R][C];

			String line;
			for (int r = 0; r < R; r++) {
				line = lines.get(r + 1);
				for (int c = 0; c < C; c++) {
					pizza[r][c] = line.charAt(c);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Slice> cut() {
		List<Slice> slices = new ArrayList<Slice>();
		
		for (int i = H; i > I * 2 - 1; i--) {
			slices.addAll(cutRows(i));
			slices.addAll(cutCols(i));
			slices.addAll(cutSquares(i));
		}
		
		return slices;
	}

	private List<Slice> cutSquares(int length) {
		List<Slice> slices = new ArrayList<Slice>();

		int t, m, x;
//		int s = (int) Math.sqrt(length);
//		int s2 = (int) Math.sqrt(length);
		int s = (int) length/2;
		int s2 = 2;
		if(s < 2){
			return slices;
		}
				
		for(int r = 0; r < R-s; r++){
			for(int c = 0; c < C-s2; c++){
				t = 0;
				m = 0;
				x = 0;
				for(int i = 0; i < s; i++){
					for(int k = 0; k < s2; k++){
						if (pizza[r+i][c+k] == 'T') {
							t++;
						} else if (pizza[r+i][c+k] == 'M') {
							m++;
						} else {
							
							x++;
						}
					}
				}
				if (t >= I && m >= I && x == 0) {
					slices.add(new Slice(r, r+s-1, c, c+s2-1));
					for(int i = 0; i < s; i++){
						for(int k = 0; k < s2; k++){
							pizza[r+i][c+k] = 'x';
						}
					}
				}
			}
		}
		
		return slices;
	}
	
	private List<Slice> cutRows(int length) {
		List<Slice> slices = new ArrayList<Slice>();

		int t, m, x, max;
		for (int i = 0; i <= C; i++) {
			max = Math.min(C, length + i);
			for (int r = 0; r < R; r++) {
				t = 0;
				m = 0;
				x = 0;
				for (int c = 0 + i; c < max; c++) {
					if (pizza[r][c] == 'T') {
						t++;
					} else if (pizza[r][c] == 'M') {
						m++;
					} else {
						x++;
					}
				}
				if (t >= I && m >= I && x == 0) {
					slices.add(new Slice(r, r, 0 + i, max - 1));
					for (x = 0 + i; x < max; x++) {
						pizza[r][x] = 'x';
					}
				}
			}
		}

		return slices;
	}

	private List<Slice> cutCols(int length) {
		List<Slice> slices = new ArrayList<Slice>();

		int t, m, x, max;
		for (int i = 0; i <= R; i++) {
			max = Math.min(R, length + i);
			for (int c = 0; c < C; c++) {
				t = 0;
				m = 0;
				x = 0;
				for (int r = 0 + i; r < max; r++) {
					if (pizza[r][c] == 'T') {
						t++;
					} else if (pizza[r][c] == 'M') {
						m++;
					} else {
						x++;
					}
				}
				if (t >= I && m >= I && x == 0) {
					slices.add(new Slice(0 + i, max - 1, c, c));
					for (x = 0 + i; x < max; x++) {
						pizza[x][c] = 'x';
					}
				}
			}
		}

		return slices;
	}

	public int height() {
		return R;
	}

	public int width() {
		return C;
	}

	private void print() {
		for (int i = 0; i < pizza.length; i++) {
			for (int j = 0; j < pizza[i].length; j++) {
				System.out.print(pizza[i][j] + " ");
			}
			System.out.println();
		}
	}
}
