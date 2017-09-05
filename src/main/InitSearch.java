package main;

import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import entities.Paragraph;
import entities.ParagraphsIndexer;
import entities.SearchEngine;

public class InitSearch {

	// Main Function
	public static void main(String[] args) {
		String queryStr1 = "power nap benefits";
		String queryStr2 = "whale vocalization production of sound";
		String queryStr3 = "pokemon puzzle league";
		int default_size = 10;

		System.out.println("Start reading data set...");
		System.setProperty("file.encoding", "UTF-8");

		searchWithDefaultEngine(queryStr1, default_size);
	}

	public static void searchWithDefaultEngine(String queryStr, int size) {
		try {
			ArrayList<Paragraph> dataList = ReadDataSet.getAllParagraphFromDataSet();

			ParagraphsIndexer indexer = new ParagraphsIndexer();
			indexer.rebuildIndexes(dataList);

			System.out.println("Perform search with search query ===> " + queryStr);

			/*
			 * SearchEngine(boolean default_engine), while true using default
			 * Lucene scoring function.
			 */
			SearchEngine se = new SearchEngine(true);
			TopDocs topDocs = se.performSearch(queryStr, size);

			System.out.println("Result found: " + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				Document doc = se.getDocument(hits[i].doc);
				System.out.println((i + 1) + ". " + doc.get("id") + " " + doc.get("text") + " (" + hits[i].score + ")");

			}
			System.out.println("performSearch done");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
