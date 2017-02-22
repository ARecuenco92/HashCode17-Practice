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

		int max = Math.min(C, H);
		int t, m;
		for (int r = 0; r < R; r++) {
			t = 0;
			m = 0;
			for (int c = 0; c < max; c++) {
				if (pizza[r][c] == 'T') {
					t++;
				} else {
					m++;
				}
			}
			if (t >= I && m >= I) {
				slices.add(new Slice(r, r, 0, max-1));
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
}
