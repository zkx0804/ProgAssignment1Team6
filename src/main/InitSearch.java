package main;

import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import entities.Paragraph;
import entities.ParagraphsIndexer;
import entities.SearchEngine;

public class InitSearch {

	public static void main(String[] args) {
		String queryStr1 = "power nap benefits";
		String queryStr2 = "whale vocalization production of sound";
		String queryStr3 = "pokemon puzzle league";
		String testQueryStr = "Whatever the cause";

		try {
			ArrayList<Paragraph> dataList = ReadDataSet.getAllParagraphFromDataSet();

			ParagraphsIndexer indexer = new ParagraphsIndexer();
			indexer.rebuildIndexes(dataList);

			System.out.println("Perform search ...");
			SearchEngine se = new SearchEngine();
			TopDocs topDocs = se.performSearch(testQueryStr, 10);

			System.out.println("Result found: " + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				Document doc = se.getDocument(hits[i].doc);
				System.out.println(doc.get("id") + " " + doc.get("text") + " (" + hits[i].score + ")");

			}
			System.out.println("performSearch done");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
