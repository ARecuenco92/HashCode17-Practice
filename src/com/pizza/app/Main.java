package com.pizza.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.pizza.model.Pizza;
import com.pizza.model.Slice;

public class Main {

	private static void store(List<Slice> slices, String file){
		List<String> lines = new ArrayList<String>();
		lines.add(Integer.toString(slices.size()));
		for(Slice slice : slices){
			lines.add(slice.toString());
		}
		
		try {
			Files.createDirectories(Paths.get("out"));
			Files.write(Paths.get("out/"+file+".out"), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		List<String> files = new ArrayList<String>();
		files.add("example");
		files.add("small");
		files.add("medium");
		files.add("big");
		
		for(String file : files){
			store(new Pizza("files/"+file+".in").cut(), file);
		}
	}
}
