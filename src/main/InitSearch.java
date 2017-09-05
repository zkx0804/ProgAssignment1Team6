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
		System.out.println("Start reading data set...");
		System.setProperty("file.encoding", "UTF-8");

		// Variables
		String queryStr1 = "power nap benefits";
		String queryStr2 = "whale vocalization production of sound";
		String queryStr3 = "pokemon puzzle league";

		/*
		 * Change this boolean to use custom scoring function for Question 4
		 */
		boolean useDefaultScoringFunc = true;

		int default_size = 10;

		// search(queryStr1, default_size, useDefaultScoringFunc);

		// Get all query result
		ArrayList<String> queryList = new ArrayList<String>();
		queryList.add(queryStr1);
		queryList.add(queryStr2);
		queryList.add(queryStr3);

		// search(queryStr1, default_size, useDefaultScoringFunc);

		searchMultipleQuery(queryList, default_size, useDefaultScoringFunc);

	}

	public static void search(String queryStr, int size, boolean default_engine) {
		try {
			ArrayList<Paragraph> dataList = ReadDataSet.getAllParagraphFromDataSet();

			ParagraphsIndexer indexer = new ParagraphsIndexer();
			indexer.rebuildIndexes(dataList);

			System.out.println("Search with search query ===> " + queryStr);
			System.out.println("Default scoring function: " + default_engine);

			/*
			 * SearchEngine(boolean default_engine), while true using default
			 * Lucene scoring function.
			 */
			SearchEngine se = new SearchEngine(default_engine);
			TopDocs topDocs = se.performSearch(queryStr, size);

			System.out.println("Result found: " + topDocs.totalHits);

			System.out.println("Rank ----- Paragraph ID -------------------- Score ------------- Text ---------  ");
			ScoreDoc[] hits = topDocs.scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				Document doc = se.getDocument(hits[i].doc);
				System.out.println((i + 1) + ". " + doc.get("id") + " (" + hits[i].score + ") " + doc.get("text"));

			}
			System.out.println("Search done");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void searchMultipleQuery(ArrayList<String> queryStrList, int size, boolean default_engine) {
		try {
			ArrayList<Paragraph> dataList = ReadDataSet.getAllParagraphFromDataSet();

			ParagraphsIndexer indexer = new ParagraphsIndexer();
			indexer.rebuildIndexes(dataList);

			System.out.println("Query String List ===> " + queryStrList.toString());
			System.out.println("Default scoring function: " + default_engine);

			/*
			 * SearchEngine(boolean default_engine), while true using default
			 * Lucene scoring function.
			 */
			SearchEngine se = new SearchEngine(default_engine);

			if (!queryStrList.isEmpty()) {

				for (String queryStr : queryStrList) {
					System.out.println("Search with query ====> " + queryStr);
					TopDocs topDocs = se.performSearch(queryStr, size);

					System.out.println("Result found: " + topDocs.totalHits);

					System.out.println(
							"Rank ----- Paragraph ID -------------------- Score ------------- Text ---------  ");
					ScoreDoc[] hits = topDocs.scoreDocs;

					for (int i = 0; i < hits.length; i++) {
						Document doc = se.getDocument(hits[i].doc);
						System.out.println(
								(i + 1) + ". " + doc.get("id") + " (" + hits[i].score + ") " + doc.get("text"));
					}
					System.out.println("Search done");
				}

			} else {
				System.err.println("No query string input.");
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
