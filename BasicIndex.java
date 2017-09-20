import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class BasicIndex implements BaseIndex {

	@Override
	public PostingList readPosting(FileChannel fc) {
		/*
		 * TODO: Your code here
		 *       Read and return the postings list from the given file.
		 */
		 ByteBuffer buf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE * 2);
	        try {
				if (fc.read(buf) == -1) return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        buf.flip();
	        int termId = buf.getInt();
	        int docFreq = buf.getInt();

	        List<Integer> docIds = new ArrayList<Integer>(docFreq);
	        buf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE * docFreq);
	        try {
				fc.read(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        buf.flip();
	        for (int i = 0; i < docFreq; i++) {
	            docIds.add(buf.getInt());
	        }

	        PostingList p = new PostingList(termId, docIds);
	        return p;
	}

	@Override
	public void writePosting(FileChannel fc, PostingList p) {
		/*
		 * TODO: Your code here
		 *       Write the given postings list to the given file.
		 */
		ByteBuffer buf = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE * (p.getList().size() + 2));
        buf.putInt(p.getTermId());
        buf.putInt(p.getList().size());
        for (int docId : p.getList()) {
            buf.putInt(docId);
        }
        buf.flip();
        try {
			fc.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

