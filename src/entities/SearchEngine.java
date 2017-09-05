package entities;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;

public class SearchEngine {
	private IndexSearcher searcher = null;
	private QueryParser parser = null;

	public SearchEngine(boolean default_engine) throws IOException {
		searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get("./DataSet/Index/"))));

		if (!default_engine) {
			searcher.setSimilarity(createCustomeSimiliarity());
		}

		parser = new QueryParser("content", new StandardAnalyzer());
	}

	public TopDocs performSearch(String queryString, int n) throws IOException, ParseException {
		Query query = parser.parse(queryString);
		return searcher.search(query, n);
	}

	public Document getDocument(int docId) throws IOException {
		return searcher.doc(docId);
	}

	private Similarity createCustomeSimiliarity() {

		Similarity sim = new Similarity() {

			@Override
			public long computeNorm(FieldInvertState state) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public SimWeight computeWeight(CollectionStatistics collectionStats, TermStatistics... termStats) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public SimScorer simScorer(SimWeight weight, LeafReaderContext context) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

		};

		return null;
	}
}
