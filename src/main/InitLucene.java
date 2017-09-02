package main;

import java.io.File;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class InitLucene {
	
	
	
	Directory indexDir = FSDirectory.open(new File("index-directory").toPath());
	//test
	
	
}
