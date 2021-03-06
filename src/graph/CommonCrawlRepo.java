package graph;


import progettosii.CommonCrawlClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;


/**
 * Created by Andrea on 11/06/2017.
 */
public class CommonCrawlRepo extends GraphRepository {

    CommonCrawlClient commonCrawlClient;

    public CommonCrawlRepo(boolean focusOnSinglePage, String seedUrl, String suffix) throws IOException {
        super(focusOnSinglePage, seedUrl, suffix);
        commonCrawlClient = new CommonCrawlClient();
    }

    @Override
    public NodePage getNodePageRoot() {
        return new NodePage(this.seed, this);
    }

    @Override
    public String getContentPage(String id) throws UnsupportedEncodingException, SQLException {

        String body = EMPTY;

        try {
            body = commonCrawlClient.getContentUrl(id);

        }catch(Exception e){
            System.out.println("Error: there was a problem getting the page " + id);
            //e.printStackTrace();
            badPages++;
        }
        finally {

            if (body == null) {
                System.out.println("Content not found in the common crawl api for the page " + id);
                badPages++;
                return EMPTY;
            }

            return body;
        }
    }

}
